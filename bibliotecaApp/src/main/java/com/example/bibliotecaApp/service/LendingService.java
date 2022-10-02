package com.example.bibliotecaApp.service;

import com.example.bibliotecaApp.entity.*;
import com.example.bibliotecaApp.exception.BadRequestException;
import com.example.bibliotecaApp.repository.BookRepository;
import com.example.bibliotecaApp.repository.LendingRepository;
import com.example.bibliotecaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Service
public class LendingService {

    @Autowired
            private LendingRepository lendingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    public Page<LendingDTO> getLendings(Boolean pending, Integer numberPage, Integer pageSize, String dni){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "USUARIO" ));

        if (isUser) {
           dni = userRepository.getUserByUSername(userDetails.getUsername()).getDni();
        }
        HashMap<String,Object> map = new HashMap<>();

        if (pending != null)
            map.put("pending",pending);
        if (numberPage!= null && pageSize != null){
            map.put("pageSize",pageSize);
            map.put("pageOffset",pageSize*numberPage+1);
        }
        if (dni!=null){
            map.put("dni",dni);
        }
         Page<LendingDTO> lendingDTOS = lendingRepository.getLending(map);
        lendingDTOS.getContent().forEach(lendingDTO ->{
            Integer sanctionDay;
            if (lendingDTO.getDateReturn() == null){
                sanctionDay = lendingDTO.getReturnEstimateDate().after(new Date())? 0:calculateDays(lendingDTO.getReturnEstimateDate(),new Date());

            } else {
                 sanctionDay = lendingDTO.getReturnEstimateDate().after(lendingDTO.getDateReturn())? 0:calculateDays(lendingDTO.getReturnEstimateDate(),lendingDTO.getDateReturn());
            }

            lendingDTO.setSanctions(sanctionDay);
            lendingDTO.setSancMoney(lendingDTO.getSanctions()*5);

        });
        return lendingDTOS;
    }

    private int calculateDays (Date firstDate, Date twoDay){
        int milisecondsByDay = 86400000;
        return (int) ((firstDate.getTime()-twoDay.getTime()) / milisecondsByDay);
    }

    public void createLending( LendingDtoRequest lendingDTORequest){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "ADMINISTRADOR" ));
        UserApp userApp;
        if (isAdmin){
            userApp = userRepository.findById(lendingDTORequest.getUserId()).orElseThrow(()-> new BadRequestException("no se encontro el usuario"));
        } else {
        userApp = userRepository.getUserByUSername(userDetails.getUsername());
        }

        Book book = bookRepository.findById(lendingDTORequest.getBookId()).orElseThrow(()-> new BadRequestException("No se encontro el libro con ese id"));
        if (lendingRepository.existsLendingByUserApp_IdAndBook_IdAndDateReturnIsNull(userApp.getId(),book.getId())) {
            throw  new BadRequestException("Ya tiene un prestamo pendiente con el mismo libro ");
        }

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 15);

        Lending lending = new Lending();
        lending.setBook(book);
        lending.setUser(userApp);
        lending.setDateOut(new Date());
        lending.setReturnEstimateDate(c.getTime());
        lendingRepository.save(lending);

        bookService.updateAvailable(book);
    }

    public LendingDTO  returnLending (Long id){
        Lending lending = lendingRepository.findById(id).orElseThrow(()-> new BadRequestException("no se encontro el prestamo"));

        if (lending.getDateReturn()!= null) {
            throw  new BadRequestException("El libro ya fue devuelto");
        }

        lending.setDateReturn(new Date());
        lendingRepository.save(lending);

        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setId(lending.getId());
        lendingDTO.setUserAppId(lending.getUser().getId());
        lendingDTO.setBookId(lending.getBook().getId());
        lendingDTO.setDateOut(lending.getDateOut());
        lendingDTO.setDateReturn(lending.getDateReturn());
        lendingDTO.setReturnEstimateDate(lending.getReturnEstimateDate());

        Integer sanctionDay = lendingDTO.getReturnEstimateDate().after(lendingDTO.getDateReturn())? 0:calculateDays(lendingDTO.getReturnEstimateDate(),lendingDTO.getDateReturn());
        lendingDTO.setSanctions(sanctionDay);
        lendingDTO.setSancMoney(lendingDTO.getSanctions()*5);

        bookService.updateAvailable(lending.getBook());
        return lendingDTO;
    }





}

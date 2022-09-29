/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliotecaApp.service;


import com.example.bibliotecaApp.entity.UserApp;
import com.example.bibliotecaApp.entity.UserCredential;
import com.example.bibliotecaApp.enums.ROLE;
import com.example.bibliotecaApp.exception.BadRequestException;
import com.example.bibliotecaApp.exception.InvalidDataException;
import com.example.bibliotecaApp.repository.UserCredentialRepository;
import com.example.bibliotecaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
;

import javax.transaction.Transactional;

/**
 *
 * @author Mariela
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
    
@Autowired
UserCredentialRepository userCredentialRepository;

@Autowired
    UserRepository userRepository;
@Autowired
 private BCryptPasswordEncoder encoder;


 
    public void createUser (UserApp userApp, BindingResult bindingResult){
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "ADMINISTRADOR" ));


        if(bindingResult.hasErrors()){
            throw new InvalidDataException(bindingResult);
        }

        if(existsUsername(userApp.getUserCredential().getUsername())) {
            throw new DuplicateKeyException("Ya existe usuario con email: " + userApp.getUserCredential().getUsername());
        }
        if (!isAdmin){
            userApp.getUserCredential().setRole(ROLE.USUARIO);
        }
        userApp.getUserCredential().setPassword(encoder.encode(userApp.getUserCredential().getPassword()));
        userRepository.save(userApp);
        
    }
    
    public boolean existsUsername(String mail){
        return userCredentialRepository.existsByUsername(mail);

    }

    
    public UserCredential findByUsername(String mail){
        return userCredentialRepository.findByUsername(mail);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential user= userCredentialRepository.findByUsername(username);
        
        if (user==null) {
            throw new UsernameNotFoundException("No hay usuario con ese nombre");
        }
               
       return user;
    }

    public Page<UserApp> getUsers(String dni, String  name, String lastname, Integer pageSize, Integer numberPage){
        Pageable pageable = null;

        if (pageSize!=null && numberPage != null) {
            pageable = PageRequest.of(numberPage,pageSize);
        }
        return userRepository.getUserBy(name,lastname,dni,pageable);

    }

    public void deleteUser(Long id){
        if (userRepository.existLendingByUserId(id)){
            throw new BadRequestException("No se puede eliminar el usuario porque tiene prestamos pendientes");
        }
        userRepository.deleteUserCredentialId(id);
        userRepository.deleteUserId(id);
    }

    public void updateUser (Long id , UserApp userApp){
        UserApp oldUserApp = userRepository.findById(id).orElseThrow( ()-> new BadRequestException("Usuario inexistente"));
        UserCredential userCredential = userCredentialRepository.findById(oldUserApp.getUserCredential().getId()).orElse(null);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "ADMINISTRADOR" ));

        if (!isAdmin && !userCredential.getUsername().equals(userDetails.getUsername())){
            throw new BadRequestException("No tiene permiso para realizar esta accion");
        }

        oldUserApp.setAddress(userApp.getAddress());
        oldUserApp.setDni(userApp.getDni());
        oldUserApp.setLastname(userApp.getLastname());
        oldUserApp.setName(userApp.getName());
        oldUserApp.setTel(userApp.getTel());

        userRepository.save(oldUserApp);

    }

    public UserApp getUserDetail(Long id){
        return userRepository.findById(id).orElseThrow(()-> new BadRequestException("No se econtro el usuario con el id " + id));
    }
}

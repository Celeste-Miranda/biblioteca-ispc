
package com.example.bibliotecaApp.service;

import com.example.bibliotecaApp.entity.Book;
import com.example.bibliotecaApp.exception.BadRequestException;
import com.example.bibliotecaApp.exception.InvalidDataException;
import com.example.bibliotecaApp.repository.BookRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBookByTitleLike (String title)
    {
        return bookRepository.searchByTitleLike(title);

    }

    public Book getBookById (Long id){
        return bookRepository.searchById(id);
    }

    public Long createBook(Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidDataException(bindingResult);
        }

        if (book.getEjemplares()< book.getStock()){
            throw new BadRequestException("La cantidad de ejemplares no debe ser menos a la cantidad de stock");
        }
       book.setAvailable(book.getStock());
        bookRepository.save(book);
        return book.getId();
    }

    private void uploadFile (Long idBook, MultipartFile file ) throws IOException {

        Book oldBook = bookRepository.findById(idBook).orElseThrow(
                ()-> new BadRequestException("No se encontro el libro id :" + idBook)
        );

        if(file.isEmpty()){
            throw new BadRequestException("La imagen no puede ser vacia");
        }
        if (file.getContentType().equals(MediaType.IMAGE_JPEG) || file.getContentType().equals(MediaType.IMAGE_PNG) ){
            throw new BadRequestException("Formato invalido");
        }
        String filePath = "src/main/resources/static/image/"+idBook;
        file.transferTo(new File(filePath));

        oldBook.setImg(filePath);
        bookRepository.save(oldBook);
    }

    public void updateBook(Long id, Book book, BindingResult bindingResult){
        Book oldBook = bookRepository.findById(id).orElseThrow(
                ()-> new BadRequestException("No se encontro el libro id :" + id)
        );
        if(bindingResult.hasErrors()){
            throw new InvalidDataException(bindingResult);
        }
        if (book.getEjemplares()< book.getStock()){
            throw new BadRequestException("La cantidad de ejemplares no debe ser menos a la cantidad de stock");
        }

        oldBook.setEjemplares(book.getEjemplares());
        oldBook.setStock(book.getStock());
        oldBook.setDate(book.getDate());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setCategory(book.getCategory());
        oldBook.setDescription(book.getDescription());
        oldBook.setLang(book.getLang());
        oldBook.setPages(book.getPages());
        oldBook.setTitle(book.getTitle());

        bookRepository.save(oldBook);
        updateAvailable(oldBook);
    }

    public void updateAvailable(Book oldBook){
        oldBook.setAvailable(bookRepository.getAvailableById(oldBook.getId()));
        bookRepository.save(oldBook);
    }

    public void  deleteBook(Long id){

        if (bookRepository.existLendingByBookId(id)){
            throw new BadRequestException("No se puede eliminar este libro porque tiene prestamos pendientes");
        }
        bookRepository.deleteLendingByBookId(id);
        bookRepository.deleteBookId(id);
    }

}


package com.example.bibliotecaApp.controller;

import com.example.bibliotecaApp.entity.Book;
import com.example.bibliotecaApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/books")

public class BookController {
    @Autowired
    private BookService bookService;
    
    @GetMapping()
    public ResponseEntity<Book[]> getBook (@RequestParam (value = "title") String title ){
        return new ResponseEntity(bookService.getBookByTitleLike(title),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Book> getBookById (@RequestParam (value = "id") Long id){
        return new ResponseEntity(bookService.getBookById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<Long> createBook (@Valid @RequestBody Book book, BindingResult bindingResult){

        return new ResponseEntity<>(bookService.createBook(book,bindingResult),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> updateBook (@PathParam (value = "id") Long id ,
                                            @Valid @RequestBody Book book, BindingResult bindingResult){
        bookService.updateBook(id,book,bindingResult);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteBook (@PathParam (value = "id") Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

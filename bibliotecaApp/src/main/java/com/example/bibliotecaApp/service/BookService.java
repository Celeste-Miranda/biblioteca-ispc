
package com.example.bibliotecaApp.service;

import com.example.bibliotecaApp.entity.Book;
import com.example.bibliotecaApp.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBookByTitleLike (String title){
        return bookRepository.searchByTitleLike(title);
    }

    public Book getBookById (String id){
        return bookRepository.searchById(Integer.parseInt(id));
    }

}

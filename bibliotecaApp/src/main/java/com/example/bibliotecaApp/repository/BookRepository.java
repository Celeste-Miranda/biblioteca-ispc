/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliotecaApp.repository;

import com.example.bibliotecaApp.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("SELECT m FROM Book m WHERE m.title LIKE %:title%")
    List<Book> searchByTitleLike(@Param("title") String title);

    @Query("SELECT m FROM Book m WHERE m.id = :id")
    Book searchById(@Param("id") Integer id);}

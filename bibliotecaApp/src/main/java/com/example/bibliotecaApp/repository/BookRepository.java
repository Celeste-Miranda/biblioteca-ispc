/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliotecaApp.repository;

import com.example.bibliotecaApp.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("SELECT m FROM Book m WHERE m.title LIKE %:title%")
    List<Book> searchByTitleLike(@Param("title") String title);

    @Query("SELECT m FROM Book m WHERE m.title LIKE %:title% AND m.")
    List<Book> searchByTitleLikeAndPending(@Param("title") String title);

    @Query("SELECT m FROM Book m WHERE m.id = :id")
    Book searchById(@Param("id") Long id);

    @Query(value = "SELECT ( b.stock  -  count(l.id)) as habilitados FROM books b " +
            "left join lendings l ON l.book_id = b.id " +
            "WHERE b.id = :idBook AND l.date_return IS NULL  ; ", nativeQuery = true)
    Integer getAvailableById(@Param("idBook") Long idBook);

    @Query(value = "SELECT CASE WHEN EXISTS (" +
            "    SELECT * " +
            "    FROM lengings " +
            "    WHERE book_id = :bookId " +
            "     AND date_return IS NOT NULL)" +
            "THEN CAST(1 AS BIT)" +
            "ELSE CAST(0 AS BIT) END; " , nativeQuery = true)
    Boolean existLendingByBookId (@Param("bookId") Long bookId);

    @Transactional
    @Modifying
    @Query (value = " DELETE FROM lendings WHERE book_id = : bookId ; ", nativeQuery = true)
    void deleteLendingByBookId(@Param("bookId") Long id);

    @Transactional
    @Modifying
    @Query (value = " DELETE FROM books WHERE id = : bookId ; ", nativeQuery = true)
    void deleteBookId(@Param("bookId") Long id);
}
package com.example.bibliotecaApp.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "lendings" )
public class Lending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "user_id")
    private UserApp userApp;
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "book_id")
    private Book book;
    private Date dateOut;
    private Date dateReturn;
    private Date returnEstimateDate;

    public Lending() {
    }

    public Lending(Long id, UserApp userApp, Book book) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 15);

        this.id = id;
        this.userApp = userApp;
        this.book = book;
        this.dateOut = new Date();
        this.returnEstimateDate = calendar.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserApp getUser() {
        return userApp;
    }

    public void setUser(UserApp userApp) {
        this.userApp = userApp;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Date getReturnEstimateDate() {
        return returnEstimateDate;
    }

    public void setReturnEstimateDate(Date returnEstimateDate) {
        this.returnEstimateDate = returnEstimateDate;
    }
}

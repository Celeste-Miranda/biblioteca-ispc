package com.example.bibliotecaApp.entity;


import java.util.Date;

public class LendingDTO {

    private Long id;
    private UserApp userApp;
    private Book book;
    private Date dateOut;
    private Date dateReturn;
    private Date returnEstimateDate;
    private Integer sanctions;
    private Integer sancMoney;

    public LendingDTO() {
    }

    public LendingDTO(Long id, UserApp userApp, Book book, Date dateOut, Date dateReturn, Date returnEstimateDate) {
        this.id = id;
        this.userApp = userApp;
        this.book = book;
        this.dateOut = dateOut;
        this.dateReturn = dateReturn;
        this.returnEstimateDate = returnEstimateDate;
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

    public Integer getSanctions() {
        return sanctions;
    }

    public void setSanctions(Integer sanctions) {
        this.sanctions = sanctions;
    }

    public Integer getSancMoney() {
        return sancMoney;
    }

    public void setSancMoney(Integer sancMoney) {
        this.sancMoney = sancMoney;
    }
}

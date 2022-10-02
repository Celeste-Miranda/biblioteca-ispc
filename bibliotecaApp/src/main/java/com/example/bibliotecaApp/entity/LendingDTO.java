package com.example.bibliotecaApp.entity;


import java.util.Date;

public class LendingDTO {

    private Long id;
    private Long userAppId;
    private Long bookId;
    private Date dateOut;
    private Date dateReturn;
    private Date returnEstimateDate;
    private Integer sanctions;
    private Integer sancMoney;

    public LendingDTO() {
    }

    public LendingDTO(Long id, Long userAppId, Long bookId, Date dateOut, Date dateReturn, Date returnEstimateDate, Integer sanctions, Integer sancMoney) {
        this.id = id;
        this.userAppId = userAppId;
        this.bookId = bookId;
        this.dateOut = dateOut;
        this.dateReturn = dateReturn;
        this.returnEstimateDate = returnEstimateDate;
        this.sanctions = sanctions;
        this.sancMoney = sancMoney;
    }

    public Long getUserAppId() {
        return userAppId;
    }

    public void setUserAppId(Long userAppId) {
        this.userAppId = userAppId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

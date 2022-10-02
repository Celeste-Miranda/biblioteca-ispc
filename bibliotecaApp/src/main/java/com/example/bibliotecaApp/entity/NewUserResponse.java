package com.example.bibliotecaApp.entity;


public class NewUserResponse {
    private boolean ok;
    private String message;


    public NewUserResponse() {
    }

    public NewUserResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
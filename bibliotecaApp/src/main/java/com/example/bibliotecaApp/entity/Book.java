
package com.example.bibliotecaApp.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String date;
    private String author;
    private Integer available;
    private Integer stock;
    private String category;
    private String description;
    private String ejemplares;
    private String lang;
    private Integer page;
    private String edit;

    public Book() {
    }

    
    public Book(Integer id, String title, String date, String author, Integer available, Integer stock, String category, String description, String copies, String lang, Integer page) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.author = author;
        this.available = available;
        this.stock = stock;
        this.category = category;
        this.description = description;
        this.ejemplares = copies;
        this.lang = lang;
        this.page = page;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCopies() {
        return ejemplares;
    }

    public void setCopies(String copies) {
        this.ejemplares = copies;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }
 
    
    
    
}

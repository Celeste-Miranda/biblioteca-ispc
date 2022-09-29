
package com.example.bibliotecaApp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "El campo titulo no puede ser vario o nulo")
    private String title;
    @NotNull(message = "El campo fecha de publicacion no puede ser vario o nulo")
    private String date;
    private Date createdAt;
    @NotBlank(message = "El campo Autor no puede ser vario o nulo")
    private String author;
    private Integer available;
    @NotNull(message ="El campo stock no puede ser vario o nulo" )
    private Integer stock;
    @NotBlank(message = "El campo Categoria no puede ser vario o nulo")
    private String category;
    private String description;
    @NotNull(message ="El campo ejemplares no puede ser vario o nulo" )
    private Integer ejemplares;
    @NotBlank(message = "El campo idioma no puede ser vario o nulo")
    private String lang;
    @NotNull(message ="El campo stock no puede ser vario o nulo" )
    private Integer pages;
    @NotBlank(message = "El campo Edicion no puede ser vario o nulo")
    private String edit;
    private String img;

    public Book() {
    }

    


    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }
}

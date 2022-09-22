package com.example.bibliotecaApp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "el campo name no puede estar vario o nulo")
    private String name;
    @NotBlank (message = "el campo name no puede estar vario o nulo")
    private String lastname;
    @NotBlank (message = "el campo name no puede estar vario o nulo")
    private String address;
    @NotBlank (message = "el campo name no puede estar vario o nulo")
    private String tel;
    private Integer sanctions;
    private Integer sancMoney;

    @NotNull
    @OneToOne (cascade = CascadeType.ALL)
    private UserCredential userCredential;

    public User() {
    }

    public User(Long id, String name, String lastname, String address, String tel, Integer sanctions, Integer sancMoney, UserCredential userCredential) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.tel = tel;
        this.sanctions = sanctions;
        this.sancMoney = sancMoney;
        this.userCredential = userCredential;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliotecaApp.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Mariela
 */
@Data
public class UserLogin {
    
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

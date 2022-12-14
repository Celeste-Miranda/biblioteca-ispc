/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliotecaApp.security.jwt;

import lombok.Data;

/**
 *
 * @author Mariela
 */
@Data
public class JwtDto {
     private String token;
    private String bearer = "Bearer";
    private String username;
    private String role;

    public JwtDto(String jwt, String username, String role) {
        this.token=jwt;
        this.username=username;
        this.role = role;
    }
}

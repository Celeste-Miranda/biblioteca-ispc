/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bibliotecaApp.service;


import com.example.bibliotecaApp.entity.User;
import com.example.bibliotecaApp.entity.UserCredential;
import com.example.bibliotecaApp.enums.ROLE;
import com.example.bibliotecaApp.exception.InvalidDataException;
import com.example.bibliotecaApp.repository.UserCredentialRepository;
import com.example.bibliotecaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
;

import javax.transaction.Transactional;

/**
 *
 * @author Mariela
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
    
@Autowired
UserCredentialRepository userCredentialRepository;

@Autowired
    UserRepository userRepository;
@Autowired
 private BCryptPasswordEncoder encoder;


 
    public void createUser (User user, BindingResult bindingResult){
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "ADMINISTRADOR" ));


        if(bindingResult.hasErrors()){
            throw new InvalidDataException(bindingResult);
        }

        if(existsUsername(user.getUserCredential().getUsername())) {
            throw new DuplicateKeyException("Ya existe usuario con email: " + user.getUserCredential().getUsername());
        }
        if (!isAdmin){
            user.getUserCredential().setRole(ROLE.USUARIO);
        }
        user.getUserCredential().setPassword(encoder.encode(user.getUserCredential().getPassword()));
        userRepository.save(user);
        
    }
    
    public boolean existsUsername(String mail){
        return userCredentialRepository.existsByUsername(mail);

    }

    
    public UserCredential findByUsername(String mail){
        return userCredentialRepository.findByUsername(mail);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential user= userCredentialRepository.findByUsername(username);
        
        if (user==null) {
            throw new UsernameNotFoundException("No hay usuario con ese nombre");
        }
               
       return user;
    }
    
    
}

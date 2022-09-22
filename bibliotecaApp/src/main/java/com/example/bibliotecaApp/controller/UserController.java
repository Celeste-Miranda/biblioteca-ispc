package com.example.bibliotecaApp.controller;

import com.example.bibliotecaApp.entity.User;
import com.example.bibliotecaApp.entity.UserCredential;
import com.example.bibliotecaApp.exception.InvalidDataException;
import com.example.bibliotecaApp.security.dto.UserLogin;
import com.example.bibliotecaApp.security.jwt.JwtDto;
import com.example.bibliotecaApp.security.jwt.JwtProvider;
import com.example.bibliotecaApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;



    @PostMapping ("/register")
    public ResponseEntity<?> nuevo(@Valid @RequestBody User user, BindingResult bindingResult){

        userService.createUser(user,bindingResult);

        return new ResponseEntity("usuario guardado con éxito", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin userCredential, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            throw new InvalidDataException(bindingResult);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUsername(), userCredential.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PostMapping ("/registerAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> newUSer (@Valid @RequestBody User user, BindingResult bindingResult){

        userService.createUser(user,bindingResult);

        return new ResponseEntity("usuario guardado con éxito", HttpStatus.CREATED);
    }
}

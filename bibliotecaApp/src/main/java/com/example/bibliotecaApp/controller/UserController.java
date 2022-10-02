package com.example.bibliotecaApp.controller;

import com.example.bibliotecaApp.entity.NewUserResponse;
import com.example.bibliotecaApp.entity.UserApp;
import com.example.bibliotecaApp.exception.InvalidDataException;
import com.example.bibliotecaApp.security.dto.UserLogin;
import com.example.bibliotecaApp.security.jwt.JwtDto;
import com.example.bibliotecaApp.security.jwt.JwtProvider;
import com.example.bibliotecaApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> nuevo(@Valid @RequestBody UserApp userApp, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            NewUserResponse newUserResponse = new NewUserResponse(false, "Error en el registro, uno o mas campos presentan errores");

            return new ResponseEntity(newUserResponse, HttpStatus.BAD_REQUEST);

        }

        userService.createUser(userApp,bindingResult);
        NewUserResponse newUserResponse = new NewUserResponse(true, "Usuario creado correctamente");

        return new ResponseEntity(newUserResponse, HttpStatus.CREATED);
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
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(),userDetails.getAuthorities().iterator().next().toString());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PostMapping ("/registerAdmin")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> newUSer (@Valid @RequestBody UserApp userApp, BindingResult bindingResult){

        userService.createUser(userApp,bindingResult);

        return new ResponseEntity("usuario guardado con éxito", HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getUSers (@RequestParam (value = "pageSize") Integer pageSize,
                                       @RequestParam (value = "numberPage") Integer numberPage,
                                       @RequestParam (value= "dni") String dni,
                                       @RequestParam (value = "name") String name,
                                       @RequestParam (value = "lastname") String lastname
                                       ){

         return new ResponseEntity(userService.getUsers(dni,name,lastname,pageSize,numberPage), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> deleteUser (@PathVariable(value = "id") Long id){

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser (@PathVariable(value = "id") Long id,
                                         @RequestBody UserApp userApp){

        userService.updateUser(id, userApp);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getUserDetail (@PathVariable(value = "id") Long id){

        return new ResponseEntity<>(userService.getUserDetail(id),HttpStatus.OK);
    }
    @GetMapping("valid")
    public ResponseEntity<?> getUservalid (HttpServletRequest request){
        String token = request.getHeader("Authorization");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtDto jwtDto = new JwtDto(token,userDetails.getUsername(),userDetails.getAuthorities().iterator().next().toString());
        return new ResponseEntity<>(jwtDto,HttpStatus.OK);
    }

}

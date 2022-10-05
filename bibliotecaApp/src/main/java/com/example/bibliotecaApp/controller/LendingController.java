package com.example.bibliotecaApp.controller;

import com.example.bibliotecaApp.entity.LendingDtoRequest;
import com.example.bibliotecaApp.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @GetMapping("")
    public ResponseEntity<?> getAllLending (@RequestParam (value = "pending", required = false) Boolean pending,
                                            @RequestParam (value = "numberPage",required = false) Integer numberPage,
                                            @RequestParam (value = "pageSize",required = false) Integer pageSize,
                                            @RequestParam(value = "dni",required = false) String dni){
        return new ResponseEntity<>(lendingService.getLendings(pending,numberPage,pageSize,dni), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createLending (@RequestBody LendingDtoRequest lendingDTORequest){
        lendingService.createLending(lendingDTORequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createLending (@PathVariable ("id") Long id){
        return new ResponseEntity<>(lendingService.returnLending(id),HttpStatus.OK);
    }
}

package com.example.bibliotecaApp.entity;

import lombok.Data;

@Data
public class LendingDtoRequest {

    private Long userId;
    private Long bookId;
}

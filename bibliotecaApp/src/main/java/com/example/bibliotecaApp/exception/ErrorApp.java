
package com.example.bibliotecaApp.exception;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ErrorApp {
     private int status;
  
  private String message;
  
  private Date timestamp;
  
  List<String> errors;

    public ErrorApp() {
    }
  
  public ErrorApp(String message) {
    this.message = message;
  }

}



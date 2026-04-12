package com.uade.tpo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserGenericException.class)
    public ResponseEntity<String> handleUserException(UserGenericException ex) {
        return ResponseEntity
                .badRequest() // 400
                .body(ex.getMessage());
    }
}
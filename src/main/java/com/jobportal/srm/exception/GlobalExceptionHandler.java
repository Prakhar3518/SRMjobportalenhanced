package com.jobportal.srm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice // Makes this class handle exceptions globally
public class GlobalExceptionHandler {

    // Handles all RuntimeExceptions thrown in application
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity
                .badRequest() // Returns HTTP 400
                .body(ex.getMessage()); // Sends exception message as response body
    }
}
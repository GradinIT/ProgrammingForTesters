package se.jensen.web.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.jensen.dao.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice { 

    private final String SERVER_ERROR = "Internal Server Error, retry or contact system admin";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(SERVER_ERROR);
    }
} 
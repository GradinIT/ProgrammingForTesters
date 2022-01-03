package se.jocke.common.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.jocke.common.aspects.annotation.TimeAndLogg;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice { 
    private final String SERVER_ERROR = "Internal Server Error, retry or contact system admin";
    @ExceptionHandler(EntityNotFoundException.class)
    @TimeAndLogg
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @TimeAndLogg
    public ResponseEntity handleNullPointerException(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
    }
    @ExceptionHandler(EntityAlreadyInStorageException.class)
    @TimeAndLogg
    public ResponseEntity handleEntityAlreadyInStorageException(EntityAlreadyInStorageException e) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
    }
} 
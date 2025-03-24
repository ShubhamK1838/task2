package com.zestindia.t2.exception.controller;

import com.zestindia.t2.exception.custom.CategoryNotFoundException;
import com.zestindia.t2.exception.custom.OrderNotFoundException;
import com.zestindia.t2.exception.custom.ProductNotFoundException;
import com.zestindia.t2.exception.custom.UserAlreadyExistsException;
import com.zestindia.t2.exception.response.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.Date;

//@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDeniedExceptionHandler(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionResponse.builder()
                        .message("Access Denied: " + exception.getMessage())
                        .statusCode(HttpStatus.FORBIDDEN)
                        .timestamp(Date.from(Instant.now()))
                        .build());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> usernameNotFoundExceptionHandler(UsernameNotFoundException exception) {
        return builtNotFoundException(exception);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundExceptionHandler(CategoryNotFoundException categoryNotFoundException) {
        return builtNotFoundException(categoryNotFoundException);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundExceptionHandler(ProductNotFoundException exception) {
        return builtNotFoundException(exception);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> orderNotFoundExceptionHandler(OrderNotFoundException orderNotFoundException) {
        return builtNotFoundException(orderNotFoundException);
    }

    private ResponseEntity<ExceptionResponse> builtNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND)
                        .timestamp(new Date())
                        .build());
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            UserAlreadyExistsException.class,
            SQLIntegrityConstraintViolationException.class
    })
    public ResponseEntity<ExceptionResponse> constraintViolationExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .statusCode(HttpStatus.NOT_ACCEPTABLE)
                        .timestamp(new Date())
                        .build());

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> genericExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .statusCode(HttpStatus.NOT_ACCEPTABLE)
                        .timestamp(new Date())
                        .build());

    }

}

package com.zestindia.t2.exception;

import com.zestindia.t2.exception.custom.CategoryNotFoundException;
import com.zestindia.t2.exception.custom.ProductNotFoundException;
import com.zestindia.t2.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
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

    private ResponseEntity<ExceptionResponse> builtNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND)
                        .timestamp(new Date())
                        .build());
    }
}

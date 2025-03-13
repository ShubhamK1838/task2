package com.zestindia.t2.exception;

import com.zestindia.t2.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
public class SecurityExceptionHandlerController {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionResponse> autheorizationExceptionHandler(AuthorizationDeniedException exception) {
        return
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder()
                        .message(exception.getMessage()).statusCode(HttpStatus.UNAUTHORIZED).timestamp(Date.from(Instant.now())).build());
    }
}

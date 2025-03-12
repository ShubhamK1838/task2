package com.zestindia.t2.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse  {
    private String message;
    private Date timestamp=Date.from(Instant.now());
    private HttpStatus statusCode;

    public ExceptionResponse(Exception  exception, HttpStatus httpStatus) {
        message=exception.getMessage();
        this.statusCode=httpStatus;
        timestamp=Date.from(Instant.now());
    }
}

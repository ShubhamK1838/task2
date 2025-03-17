package com.zestindia.t2.exception.custom;

import lombok.ToString;

@ToString
public class JwtTokenExpirationException extends RuntimeException {
    public JwtTokenExpirationException(String message) {
        super(message);
    }
    public JwtTokenExpirationException( ) {
        super("JWT Token Expired");
    }
}

package com.zestindia.t2.exception.custom;

public class UserAlreadyExistsException extends  RuntimeException {

    public UserAlreadyExistsException(String message)
    {
        super(message);
    }

}

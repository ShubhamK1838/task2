package com.zestindia.t2.exception.custom;

public class CategoryNotFoundException extends  RuntimeException {

    public CategoryNotFoundException(String message )
    {
        super(message);
    }
    public CategoryNotFoundException()
    {
        super("Category Not Found");
    }
}

package com.app.store.exceptions;

public class ProductNotFoundException extends RuntimeException{
    private final ExceptionResponse response;

    public ProductNotFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}

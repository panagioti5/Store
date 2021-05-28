package com.app.store.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    private final ExceptionResponse response;

    public CustomerNotFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}

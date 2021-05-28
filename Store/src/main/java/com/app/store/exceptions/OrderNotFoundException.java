package com.app.store.exceptions;

public class OrderNotFoundException extends RuntimeException{
    private final ExceptionResponse response;

    public OrderNotFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}

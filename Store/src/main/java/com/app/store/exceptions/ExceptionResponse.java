package com.app.store.exceptions;

import java.util.Date;

public class ExceptionResponse {
    private String message;
    private String errorCode;
    private Date date;

    public ExceptionResponse(String message, String errorCode, Date date) {
        this.message = message;
        this.errorCode = errorCode;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
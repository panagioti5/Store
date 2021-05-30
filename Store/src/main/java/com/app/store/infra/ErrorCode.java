package com.app.store.infra;

public enum ErrorCode {
    CUSTOMER_NOT_FOUND("25"),
    ORDER_NOT_FOUND("26"),
    PRODUCT_NOT_FOUND("27"),
    GENERIC_ERROR("50");

    public final String code;

    private ErrorCode(String code) {
        this.code = code;
    }
}



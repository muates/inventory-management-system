package com.muates.inventorymanagementsystem.exception;

public class CustomException extends RuntimeException {

    private final int statusCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.statusCode = errorCode.getCode();
    }

    public int getStatusCode() {
        return statusCode;
    }
}

package com.paymentflow.payment.exception;

public class DataNotFoundException extends Exception{
    private final String errorCode;
    public DataNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() { return errorCode; }
}

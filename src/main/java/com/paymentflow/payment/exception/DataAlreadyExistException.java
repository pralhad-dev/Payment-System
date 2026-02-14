package com.paymentflow.payment.exception;


public class DataAlreadyExistException extends RuntimeException {
    private final String errorCode;
    public DataAlreadyExistException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() { return errorCode; }
}

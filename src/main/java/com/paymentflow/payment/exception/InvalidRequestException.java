package com.paymentflow.payment.exception;

public class InvalidRequestException extends RuntimeException {
    private final String errorCode;
    public InvalidRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() { return errorCode; }
}

package com.paymentflow.payment.exception;

public class InsufficientBalanceException extends RuntimeException{
    private final String errorCode;
    public InsufficientBalanceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() { return errorCode; }
}

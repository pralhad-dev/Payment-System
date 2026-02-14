package com.paymentflow.payment.exception;

import com.paymentflow.payment.Enum.ResponseStatus;
import com.paymentflow.payment.dto.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleDataExist(
            DataAlreadyExistException ex) {

        GlobalApiResponse<?> response = new GlobalApiResponse<>();
        response.setResponseMsg(ex.getMessage());
        response.setStatus(ResponseStatus.FAILURE);
        response.setResponseCode("409");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleInvalidRequest(InvalidRequestException ex) {

        GlobalApiResponse<?> response = new GlobalApiResponse<>();
        response.setResponseMsg(ex.getMessage());
        response.setStatus(ResponseStatus.FAILURE);
        response.setResponseCode("400");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleDataNotFound(
            DataNotFoundException ex) {

        GlobalApiResponse<?> response = new GlobalApiResponse<>();
        response.setResponseMsg(ex.getMessage());
        response.setStatus(ResponseStatus.FAILURE);
        response.setResponseCode("404");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

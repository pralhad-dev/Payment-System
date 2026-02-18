package com.paymentflow.payment.dto;

public class CustomStatus {

    public static final String DATA_ALREADY_EXITS = "Data Already EXits";
    public static final String DATA_ALREADY_EXITS_CODE = "409";
    public static final String DATA_NOT_FOUND = "Data not found";
    public static final String DATA_NOT_FOUND_CODE = "404";
    public static final String INVALID_REQUEST_DATA = "invalid request data";
    public static final String INVALID_REQUEST_DATA_CODE = "400";
    public static final String CREATED_CODE = "201";

    public static final String DATA_NOT_FOUND_MSG = "Record not found";
    public static final String SUCCESS_CODE = "200";
    public static final String RETRIEVE_SUCCESS_MSG = "Data retrieve successfully";
    public static final String INTERNAL_EXCEPTION_CODE = "500";
    public static final String INTERNAL_EXCEPTION_MSG = "Internal Server Error!";
    public static final String BAD_REQUEST_CODE = "400";
    public static final String USER_ID_MANDATORY_MSG = "User id is mandatory.";
    public static final String DATA_UPDATE_SUCCESS_MSG = "Data updated successfully.";
    public static final String DATA_ALREADY_EXISTED = "Data Already Existed";
    public static final String DATA_CONFLICT_CODE = "409";
    public static final String ACTIVE = "ACTIVE";
    public static final String DATA_SAVED = "Data Saved Successfully";
    public static final String DATA_SAVED_STATUS_CODE = "201";
    public static final String REQUEST_BODY_EMPTY = "Request body must not be null or empty.";
    public static final String CONFLICT_CODE = "409";
    public static final String FAILED_TO_UPDATE = "Failed to update. No data found for the given Id";
    public static final String DATA_SAVE_SUCCESS_MSG = "Data saved successfully.";
    public static final String DATA_DELETED_MSG = "Data Deleted Successfully";
    public static final String BALANCE_ADDED = "Balance added Successfully";
    public static final String BALANCE_WITHDRAW = "Balance withdraw Successfully";
    public static final String TRANSFER_SUCCESS_MSG = "Amount Transfer Successfully";
    public static final String REVERSE_TXN= "Only SUCCESS transactions can be reversed";
    public static final String INSUFFICIENT_FUNDS = "Insufficient Funds";
    public static final String FAILED_TXN = "Only FAILED transactions can be retried";
    public static final String MAX_RETRY = "Maximum retry attempts reached";


}

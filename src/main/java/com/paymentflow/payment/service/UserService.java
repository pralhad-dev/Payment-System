package com.paymentflow.payment.service;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.UpdateUserRequest;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    GlobalApiResponse createUser(CreateUserRequest request);

    GlobalApiResponse<UserResponse> fetchUserById(Long id) throws DataNotFoundException;


    GlobalApiResponse<List<UserResponse>> fetchAllUserList();

    GlobalApiResponse updateUser(Long id, UpdateUserRequest request) throws DataNotFoundException;

    GlobalApiResponse deleteUser(Long id) throws DataNotFoundException;

    GlobalApiResponse addBalance(Long id, BigDecimal amount) throws DataNotFoundException;

    GlobalApiResponse withdrawMoney(Long id, BigDecimal amount) throws DataNotFoundException;
}

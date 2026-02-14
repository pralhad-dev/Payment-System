package com.paymentflow.payment.service;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.exception.DataNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    GlobalApiResponse createUser(CreateUserRequest request);

    GlobalApiResponse<UserResponse> fetchUserById(Long id) throws DataNotFoundException;


    GlobalApiResponse<List<UserResponse>> fetchAllUserList();
}

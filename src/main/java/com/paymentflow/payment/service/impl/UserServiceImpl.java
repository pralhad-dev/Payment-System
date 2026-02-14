package com.paymentflow.payment.service.impl;

import com.paymentflow.payment.Enum.ResponseStatus;
import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.CustomStatus;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.entity.User;
import com.paymentflow.payment.exception.DataAlreadyExistException;
import com.paymentflow.payment.exception.DataNotFoundException;
import com.paymentflow.payment.exception.InvalidRequestException;
import com.paymentflow.payment.mapper.UserMapper;
import com.paymentflow.payment.repository.UserRepository;
import com.paymentflow.payment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public GlobalApiResponse createUser(CreateUserRequest request) {
        GlobalApiResponse response = new GlobalApiResponse<>();
        log.info("Creating new user with email: {}", request.getEmail());
        boolean exists = userRepository.existsByEmail(request.getEmail());

        if (exists) {
            throw new DataAlreadyExistException(
                    CustomStatus.DATA_ALREADY_EXITS,
                    CustomStatus.DATA_ALREADY_EXITS_CODE
            );
        }


        User entity = userMapper.userRequestMapToEntity(request);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(entity);
        log.info("User created successfully with ID: {}", savedUser.getId());

        response.setResponseMsg(CustomStatus.DATA_SAVE_SUCCESS_MSG);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseData(savedUser);
        response.setResponseCode(CustomStatus.CREATED_CODE);

        return response;

    }


    @Override
    @Transactional(readOnly = true)
    public GlobalApiResponse<UserResponse> fetchUserById(Long id) throws DataNotFoundException {
        GlobalApiResponse<UserResponse> response = new GlobalApiResponse<>();
        if (id == null && id <=0) {
            throw new InvalidRequestException(CustomStatus.INVALID_REQUEST_DATA,CustomStatus.INVALID_REQUEST_DATA_CODE);
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        CustomStatus.DATA_NOT_FOUND,
                        CustomStatus.DATA_NOT_FOUND_CODE
                ));
        UserResponse  data = userMapper.mapToUserResponse(user);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseData(data);
        response.setResponseMsg(CustomStatus.RETRIEVE_SUCCESS_MSG);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);

        return response;

    }

}

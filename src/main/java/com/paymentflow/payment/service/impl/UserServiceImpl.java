package com.paymentflow.payment.service.impl;

import com.paymentflow.payment.Enum.ResponseStatus;
import com.paymentflow.payment.Enum.UserStatus;
import com.paymentflow.payment.dto.*;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
        if (id == null && id <= 0) {
            throw new InvalidRequestException(CustomStatus.INVALID_REQUEST_DATA, CustomStatus.INVALID_REQUEST_DATA_CODE);
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        CustomStatus.DATA_NOT_FOUND,
                        CustomStatus.DATA_NOT_FOUND_CODE
                ));
        UserResponse data = userMapper.mapToUserResponse(user);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseData(data);
        response.setResponseMsg(CustomStatus.RETRIEVE_SUCCESS_MSG);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);

        return response;

    }

    @Override
    public GlobalApiResponse<List<UserResponse>> fetchAllUserList() {
        GlobalApiResponse<List<UserResponse>> response = new GlobalApiResponse<>();
        List<User> user = userRepository.findAll();

        List<UserResponse> userResponseList = userMapper.mapEntityToUserResponse(user);

        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseData(userResponseList);
        response.setResponseMsg(CustomStatus.RETRIEVE_SUCCESS_MSG);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        return response;
    }

    @Override
    public GlobalApiResponse updateUser(Long id, UpdateUserRequest request) throws DataNotFoundException {
        GlobalApiResponse response = new GlobalApiResponse<>();

        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                CustomStatus.DATA_NOT_FOUND,
                CustomStatus.DATA_NOT_FOUND_CODE
        ));

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            user.setName(request.getFirstName());
        }

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            //check phone number already present or taken by another user
            if (!request.getPhone().equals(user.getPhone()) && userRepository.existByPhone(request.getPhone())) {
                throw new DataAlreadyExistException(CustomStatus.DATA_ALREADY_EXITS, CustomStatus.DATA_ALREADY_EXITS_CODE);
            }
            user.setPhone(user.getPhone());
        }
        user.setUpdatedAt(LocalDateTime.now());
        User updateUser = userRepository.save(user);

        response.setResponseMsg(CustomStatus.DATA_UPDATE_SUCCESS_MSG);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseData(updateUser);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        return response;
    }

    @Override
    public GlobalApiResponse deleteUser(Long id) throws DataNotFoundException {
        GlobalApiResponse response = new GlobalApiResponse();

        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                        CustomStatus.DATA_NOT_FOUND,
                        CustomStatus.DATA_NOT_FOUND_CODE
                )
        );
        user.setStatus(String.valueOf(UserStatus.DELETE));
        user.setDeletedAt(LocalDateTime.now());

        userRepository.save(user);
        log.info("User status changed to DELETED for ID: {}", id);

        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        response.setResponseData(user);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseMsg(CustomStatus.DATA_DELETED_MSG);

        return response;
    }

    @Override
    public GlobalApiResponse addBalance(Long id, BigDecimal amount) throws DataNotFoundException {
        GlobalApiResponse response = new GlobalApiResponse();


        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                CustomStatus.DATA_NOT_FOUND,
                CustomStatus.DATA_NOT_FOUND_CODE
        ));

        user.setBalance(amount);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        log.info("Balance added successfully");

        response.setResponseData(user);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseMsg(CustomStatus.BALANCE_ADDED);

        return response;
    }

    @Override
    public GlobalApiResponse withdrawMoney(Long id, BigDecimal amount) throws DataNotFoundException {
        GlobalApiResponse response = new GlobalApiResponse();

        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                CustomStatus.DATA_NOT_FOUND,
                CustomStatus.DATA_NOT_FOUND_CODE
        ));

        user.setBalance(user.getBalance().subtract(amount));
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        response.setResponseMsg(CustomStatus.BALANCE_WITHDRAW);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResponseCode(CustomStatus.SUCCESS_CODE);
        response.setResponseData(user);

        return response;

    }

}

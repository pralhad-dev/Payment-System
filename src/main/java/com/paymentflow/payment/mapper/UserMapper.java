package com.paymentflow.payment.mapper;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {
    public User userRequestMapToEntity(CreateUserRequest request) {

        User entity = new User();
        entity.setName(request.getFirstName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        return entity;
    }

    public UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getName());
        response.setPhone(user.getPhone());
        return response;
    }

    public List<UserResponse> mapEntityToUserResponse(List<User> user) {
        List<UserResponse> responseList = new ArrayList<>();

        for (User userList: user) {
            UserResponse response = new UserResponse();

            response.setFirstName(userList.getName());
            response.setId(userList.getId());
            response.setEmail(userList.getEmail());
            response.setPhone(userList.getPhone());
            response.setCreatedAt(userList.getCreatedAt());
            response.setUpdatedAt(userList.getUpdatedAt());

            responseList.add(response);
        }
        return responseList;
    }
}

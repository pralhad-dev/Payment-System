package com.paymentflow.payment.mapper;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.entity.User;
import org.springframework.stereotype.Component;

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
}

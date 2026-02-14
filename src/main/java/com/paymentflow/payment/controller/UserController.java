package com.paymentflow.payment.controller;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.exception.DataNotFoundException;
import com.paymentflow.payment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "create-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "fetch-user-by-Id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalApiResponse<UserResponse>> fetchUserById(@PathVariable Long id) throws DataNotFoundException {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatus.OK);
    }

    /// fetch all user dta, update user api
}

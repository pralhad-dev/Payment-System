package com.paymentflow.payment.controller;

import com.paymentflow.payment.dto.CreateUserRequest;
import com.paymentflow.payment.dto.GlobalApiResponse;
import com.paymentflow.payment.dto.UserResponse;
import com.paymentflow.payment.exception.DataNotFoundException;
import com.paymentflow.payment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/v1")
@Tag(name = "User APIs", description = "Operations related to users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "create users", description = "create user and return user details with Id")
    @PostMapping(value = "create-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch user by ID", description = "Returns a single user by its ID")
    @GetMapping(value = "fetch-user-by-Id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalApiResponse<UserResponse>> fetchUserById(@PathVariable Long id) throws DataNotFoundException {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Fetch all users", description = "Returns list of all users")
    @GetMapping(value = "fetch-all-users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalApiResponse<List<UserResponse>>> fetchAllUserList() {

        return new ResponseEntity<>(userService.fetchAllUserList(),HttpStatus.OK);
    }

}

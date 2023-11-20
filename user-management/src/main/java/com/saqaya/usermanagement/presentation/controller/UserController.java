package com.saqaya.usermanagement.presentation.controller;

import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saqaya.usermanagement.business.Services.UserService;
import com.saqaya.usermanagement.presentation.dto.request.UserRegistrationRequestDTO;
import com.saqaya.usermanagement.presentation.dto.response.UserRegistrationResponseDTO;
import com.saqaya.usermanagement.presentation.dto.response.UserResponseByIdDTO;

// UserController is a REST controller responsible for handling HTTP requests related to users.
// It adheres to the Single Responsibility Principle (SRP) as its only responsibility is to handle these requests.
@RestController
public class UserController {

    // UserService is used to handle operations related to users.
    private final UserService userService;

    // Constructor-based dependency injection with autowiring.
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // This method handles POST requests to create a new user.
    // It receives a UserRegistrationRequestDTO object from the request body.
    // It uses the UserService to save the user and returns the created user's details and JWT in the response.
    @PostMapping("/user")
    public ResponseEntity<UserRegistrationResponseDTO> createUser(@RequestBody UserRegistrationRequestDTO user)
            throws NoSuchAlgorithmException {
        UserRegistrationResponseDTO createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // This method handles GET requests to fetch a user by ID.
    // It receives the user ID from the path variable.
    // It uses the UserService to fetch the user and returns the user's details in the response.
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseByIdDTO> fetchUser(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws Exception {
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring("Bearer ".length());
        }
        UserResponseByIdDTO fetchedUser = userService.getUser(id,accessToken);
        return ResponseEntity.ok(fetchedUser);
    }
}


package com.saqaya.usermanagement.Mapper;

import com.saqaya.usermanagement.persistence.entity.User;
import com.saqaya.usermanagement.presentation.dto.request.UserRegistrationRequestDTO;

// UserMapper is a utility class responsible for converting between User entity and DTOs.
// It adheres to the Single Responsibility Principle (SRP) as its only responsibility is to handle these conversions.
public class UserMapper {

    // This method converts from UserRegistrationRequestDTO to User entity.
    // It's used when a new user registers and we need to save their details in the database.
    public static User mapUserRegistrationRequestDTOToUser(UserRegistrationRequestDTO userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .marketingConsent(userRequest.isMarketingConsent())
                .build();
    }

 
}



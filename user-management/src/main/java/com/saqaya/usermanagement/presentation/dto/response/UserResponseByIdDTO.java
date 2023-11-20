package com.saqaya.usermanagement.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserResponseByIdDTO is a DTO that represents the data sent in the response when fetching a user by ID.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseByIdDTO {
    private String id; // Unique identifier for the user
    private String firstName; // First name of the user
    private String lastName; // Last name of the user
    private String email;
    private boolean marketingConsent; // Flag to indicate whether the user has given marketing consent
}
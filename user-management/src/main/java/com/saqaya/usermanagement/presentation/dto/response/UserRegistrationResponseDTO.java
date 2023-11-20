package com.saqaya.usermanagement.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserRegistrationResponseDTO is a DTO that represents the data sent in the response after a new user registers.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponseDTO {
    private String id; // Unique identifier for the user
    private String token; // JWT for the user
}

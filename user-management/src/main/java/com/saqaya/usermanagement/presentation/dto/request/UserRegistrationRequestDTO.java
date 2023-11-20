package com.saqaya.usermanagement.presentation.dto.request;
import lombok.*;

// UserRegistrationRequestDTO is a Data Transfer Object (DTO) that represents the data received when a new user registers.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDTO {
    private String firstName; // First name of the user
    private String lastName; // Last name of the user
    private String email; // Email of the user
    private boolean marketingConsent; // Flag to indicate whether the user has given marketing consent
}
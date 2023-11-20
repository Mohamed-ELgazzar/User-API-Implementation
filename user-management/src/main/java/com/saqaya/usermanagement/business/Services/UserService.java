package com.saqaya.usermanagement.business.Services;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saqaya.usermanagement.Mapper.UserMapper;
import com.saqaya.usermanagement.exception.InvalidTokenException;
import com.saqaya.usermanagement.exception.UserNotFoundException;
import com.saqaya.usermanagement.persistence.dao.UserRepository;
import com.saqaya.usermanagement.persistence.entity.User;
import com.saqaya.usermanagement.presentation.dto.request.UserRegistrationRequestDTO;
import com.saqaya.usermanagement.presentation.dto.response.UserRegistrationResponseDTO;
import com.saqaya.usermanagement.presentation.dto.response.UserResponseByIdDTO;
import com.saqaya.usermanagement.security.JwtUtil;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/*UserService is a service class responsible for handling user-related operations.
 It implements UserDetailsService which is a core interface in Spring Security framework, 
    used to retrieve the user’s authentication and authorization information.*/
@Service
public class UserService implements UserDetailsService {

    // UserRepository is used to interact with the database.
    private final UserRepository userRepository;

    // JwtUtil is used for operations related to JWT like generation, validation,
    // etc.
    private final JwtUtil jwtUtil;

    // IdGenerationService is used to generate unique IDs for users.
    private final IdGenerationService idGenerationService;

    // Constructor-based dependency injection with autowiring.
    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil, IdGenerationService idGenerationService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.idGenerationService = idGenerationService;
    }

    // This method is used to register a new user.
    public UserRegistrationResponseDTO saveUser(UserRegistrationRequestDTO requestUserDTO)
            throws NoSuchAlgorithmException {
        // Convert DTO to entity
        User user = UserMapper.mapUserRegistrationRequestDTOToUser(requestUserDTO);

        // Generate unique ID for the user
        user.setId(idGenerationService.generateId(user.getEmail()));

        // Save the user to the database
        userRepository.save(user);

        // Generate JWT for the user
        var jwt = jwtUtil.generateToken(user);

        // Return the response with user ID and JWT
        return UserRegistrationResponseDTO.builder()
                .id(user.getId())
                .token(jwt)
                .build();
    }

    // This method is used to get user details by ID.
    public UserResponseByIdDTO getUser(String id, String accessToken)
            throws InvalidTokenException, UserNotFoundException {
        // Extract the email from the token
        String emailFromToken = jwtUtil.extractUsername(accessToken);

        // Fetch the user from the database
        Optional<User> optionalUser = userRepository.findById(id);

        // If user is present, check if the email matches the email from the token
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getEmail().equals(emailFromToken)) {
                throw new InvalidTokenException("Invalid access token");
            }

            // If marketingConsent is false, omit the email
            if (!user.isMarketingConsent()) {
                return UserResponseByIdDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName()).lastName(user.getLastName())
                        .marketingConsent(user.isMarketingConsent()).build();
            }

            return UserResponseByIdDTO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail())
                    .marketingConsent(user.isMarketingConsent()).build();
        } else {
            // If user is not found, throw an exception
            throw new UserNotFoundException("User not found");
        }
    }

    // This method is used by Spring Security to load user by username (in this
    // case, email).
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), "", new ArrayList<>());
    }
}

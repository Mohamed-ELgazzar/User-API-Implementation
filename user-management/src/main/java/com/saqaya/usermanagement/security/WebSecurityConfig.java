package com.saqaya.usermanagement.security;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.saqaya.usermanagement.business.Services.UserService;

import lombok.RequiredArgsConstructor;
// WebSecurityConfig is a configuration class that sets up web security for the application.
// It adheres to the Single Responsibility Principle (SRP) as its only responsibility is to configure web security.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;
    private final UserService userService;

    // This method configures the security filter chain.
    // It sets up the security rules for HTTP requests and configures JWT authentication.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/user/**").permitAll()
                    .anyRequest().authenticated())
                    .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                    .authenticationProvider(authenticationProvider()).addFilterBefore(
                            jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }

    // This method provides the AuthenticationProvider bean.
    // It sets up the DaoAuthenticationProvider with the UserService.
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        return authProvider;
    }

      // This method provides the AuthenticationManager bean.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}

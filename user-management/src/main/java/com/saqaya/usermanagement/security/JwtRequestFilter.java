package com.saqaya.usermanagement.security;


import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.saqaya.usermanagement.business.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

// JwtRequestFilter is a filter that runs once per request.
// It extends OncePerRequestFilter from Spring Framework to ensure a single execution per request dispatch.
// It handles JWT authentication for the system.
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    // UserService is used to load user details.
    private final UserService userService;

    // JwtUtil is used for operations related to JWT like extraction of username, validation, etc.
    private final JwtUtil jwtUtil;

    // This method is called for every request to check if the request has a valid JWT.
    // If it has a valid JWT, it sets the authentication in the context to specify that the current user is authenticated.
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Check if the Authorization header is valid
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract JWT and username from the Authorization header
        jwt = authHeader.split(" ")[1].trim();
        userEmail = jwtUtil.extractUsername(jwt);

        // If the JWT is valid and the user is not already authenticated, authenticate the user
        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(userEmail);
            if (jwtUtil.isTokenValid(jwt,userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }        

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}

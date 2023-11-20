package com.saqaya.usermanagement.persistence.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// The User class represents the user entity in the system.
// It implements UserDetails which is a core interface in Spring Security framework, used to retrieve the user’s authentication and authorization information.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    // Unique identifier for the user
    private String id;

    // First name of the user
    private String firstName;

    // Last name of the user
    private String lastName;

    // Email of the user, used as the username in this case
    private String email;

    // Flag to indicate whether the user has given marketing consent
    private boolean marketingConsent;

    // Returns the username, which is the email in this case
    @Override
    public String getUsername() {
        return email;
    }

    // Indicates whether the user account has expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indicates whether the user account is locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indicates whether the user's credentials (password) has expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indicates whether the user is enabled or disabled
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Returns the authorities granted to the user
    // In this case, all users are granted the "USER" authority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    // Returns the password, which is not used in this case
    @Override
    public String getPassword() {
        return null;
    }
}

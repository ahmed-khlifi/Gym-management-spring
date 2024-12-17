package com.gym.gym.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.gym.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImp implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public UserDetailsImp(Long id, String username, String email, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Build method to create UserDetailsImp from User entity
    public static UserDetailsImp build(User user) {
        // Convert role to GrantedAuthority (no stream needed since it's a single role)
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());

        // Return a new UserDetailsImp with the user entity's properties
        return new UserDetailsImp(
                user.getId(),
                user.getFullName(),  // Assuming getFullName() returns the full name of the user
                user.getEmail(),
                user.getPassword(),
                List.of(authority));  // Single authority for the user role
    }

    // Override methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Adjust if necessary (e.g., based on user's account status)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Adjust if necessary (e.g., based on user's account status)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Adjust if necessary (e.g., based on password expiration policy)
    }

    @Override
    public boolean isEnabled() {
        return true;  // Adjust if necessary (e.g., based on user's account activation status)
    }

    // Getter for ID (optional, depending on your requirements)
    public Long getId() {
        return id;
    }
}

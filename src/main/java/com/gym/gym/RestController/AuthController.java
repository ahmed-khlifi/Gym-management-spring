package com.gym.gym.RestController;

import com.gym.gym.Jwt.JwtUtils;

import com.gym.gym.model.Role;
import com.gym.gym.model.User;
import com.gym.gym.service.UserDetailsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject the PasswordEncoder here

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Log incoming login request for debugging
            logger.debug("Login attempt with username: {}", loginRequest.getUsername());

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Set authentication context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token (use the username from the authentication object)
            String jwt = jwtUtils.generateJwtToken(authentication.getName());

            // Log the success of the JWT token generation
            logger.debug("JWT token generated successfully for user: {}", loginRequest.getUsername());

            // Return the JWT in the response
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (AuthenticationException e) {
            // Log authentication failure
            logger.error("Authentication failed for username: {}", loginRequest.getUsername(), e);

            // Return an error response in case of failure
            return ResponseEntity.status(401).body("Authentication failed: Invalid username or password.");
        } catch (Exception e) {
            // Log any unexpected exceptions
            logger.error("Unexpected error during login for username: {}", loginRequest.getUsername(), e);

            // Return a generic error response
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Check if the username exists
        if (userDetailsService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        // Create a new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encode the password
        user.setEmail(registerRequest.getEmail());  // Optional fields
        user.setNom(registerRequest.getNom());
        user.setPrenom(registerRequest.getPrenom());
        user.setRole(Role.USER);  // Default role

        // Save the user in the database
        userDetailsService.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}

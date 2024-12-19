package com.gym.gym.Jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${adem.app.jwtSecret}")
    private String jwtSecret;

    @Value("${adem.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Generates JWT token from the username.
     *
     * @param username The username to include in the JWT.
     * @return the generated JWT token.
     */
    public String generateJwtToken(String username) {
        // Log the creation of the token
        logger.debug("Generating JWT token for username: {}", username);

        return Jwts.builder()
                .setSubject(username) // Set the subject (username)
                .setIssuedAt(new Date()) // Set the issued date
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Set expiration time
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Sign the token using the secret
                .compact(); // Generate the JWT compact representation
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token.
     * @return the extracted username.
     */
    public String getUserNameFromJwtToken(String token) {
        // Log the process of extracting username
        logger.debug("Extracting username from JWT token.");

        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token) // Parse the JWT and get the body
                    .getBody()
                    .getSubject(); // Return the subject (username)
        } catch (Exception e) {
            logger.error("Error extracting username from token: {}", e.getMessage());
            return null; // In case of error, return null
        }
    }

    /**
     * Validates the JWT token.
     *
     * @param authToken The JWT token to validate.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            // Log the validation attempt
            logger.debug("Validating JWT token.");

            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken); // Parse the token

            return true; // If no exception is thrown, the token is valid
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        // If any exception is caught, return false indicating invalid token
        return false;
    }
}

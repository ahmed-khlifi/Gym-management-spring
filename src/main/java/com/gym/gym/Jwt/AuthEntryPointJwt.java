package com.gym.gym.Jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Composant responsable de gérer les erreurs d'accès non autorisé.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Méthode appelée lorsqu'un utilisateur tente d'accéder à une ressource protégée sans authentification.
     *
     * @param request       La requête HTTP.
     * @param response      La réponse HTTP.
     * @param authException Exception levée lors de l'échec d'authentification.
     * @throws IOException      Si une erreur d'entrée/sortie survient.
     * @throws ServletException Si une erreur liée au servlet survient.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Check if the response is already committed before calling sendError
        if (!response.isCommitted()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        } else {
            // If response is already committed, log the error or take appropriate action
            // Optionally, you can log the error
            System.err.println("Response is already committed. Cannot send error.");
        }
    }
}

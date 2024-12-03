// com/example/app/security/JwtAuthenticationEntryPoint.java
package com.example.app.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Méthode appelée lorsqu'un utilisateur non authentifié tente d'accéder à une ressource sécurisée
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // Envoyer une réponse d'erreur 401 Unauthorized
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erreur : Non autorisé");
    }
}

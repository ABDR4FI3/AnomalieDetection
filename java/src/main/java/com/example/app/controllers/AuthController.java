// com/example/app/controllers/AuthController.java
package com.example.app.controllers;

import com.example.app.dtos.JwtResponse;
import com.example.app.dtos.LoginRequest;
import com.example.app.entities.User;
import com.example.app.security.JwtUtils;
import com.example.app.services.UserService;
import com.example.app.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        // Vérifier si l'email existe déjà
        if (userService.existsByEmail(user.getEmail())) {
            return "Email déjà utilisé !";
        }

        // Hasher le mot de passe
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));

        // Enregistrer l'utilisateur
        userService.saveUser(user);

        return "Utilisateur enregistré avec succès !";
    }
    // Endpoint pour l'authentification
    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Authentifier l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getMotDePasse()));

        // Si l'authentification est réussie, définir l'authentification dans le contexte
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Générer le token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Obtenir les détails de l'utilisateur
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Retourner la réponse avec le token JWT
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getAuthorities());
    }
}

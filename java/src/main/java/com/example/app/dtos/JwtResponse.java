// com/example/app/dtos/JwtResponse.java
package com.example.app.dtos;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, Long id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

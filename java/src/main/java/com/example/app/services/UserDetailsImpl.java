// com/example/app/services/UserDetailsImpl.java
package com.example.app.services;

import com.example.app.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String nom;
    private String email;

    @JsonIgnore
    private String motDePasse;

    private Collection<? extends GrantedAuthority> authorities;

    // Constructeur
    public UserDetailsImpl(Long id, String nom, String email, String motDePasse, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserDetailsImpl(
                user.getId(),
                user.getNom(),
                user.getEmail(),
                user.getMotDePasse(),
                authorities
        );
    }

    // Implémentations des méthodes de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Autres méthodes

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Ajout de la méthode getEmail()
    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}

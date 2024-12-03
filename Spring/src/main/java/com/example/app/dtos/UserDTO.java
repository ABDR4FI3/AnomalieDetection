package com.example.app.dtos;

public class UserDTO {
    private Long id;
    private String nom;
    private String email;
    private String role;
    // Pas de mot de passe pour des raisons de sécurité

    // Constructeurs
    public UserDTO() {}

    public UserDTO(Long id, String nom, String email, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

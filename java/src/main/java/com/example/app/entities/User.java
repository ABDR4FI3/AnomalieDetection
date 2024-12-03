// com/example/app/entities/User.java
package com.example.app.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true)
    private String email;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    // Relation avec ObjetSuivi
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<ObjetSuivi> objetsSuivis;

    // Constructeurs
    public User() {}

    public User(String nom, String email, String motDePasse, RoleEnum role) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Set<ObjetSuivi> getObjetsSuivis() {
        return objetsSuivis;
    }

    public void setObjetsSuivis(Set<ObjetSuivi> objetsSuivis) {
        this.objetsSuivis = objetsSuivis;
    }
}

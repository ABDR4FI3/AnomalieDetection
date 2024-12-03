package com.example.app.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "objets_suivis")
public class ObjetSuivi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomObjet;
    private String typeObjet;

    // Relation avec Position
    @OneToMany(mappedBy = "objetSuivi", cascade = CascadeType.ALL)
    private List<Position> positions;

    // Relation avec User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User utilisateur;

    // Constructeurs
    public ObjetSuivi() {}

    public ObjetSuivi(String nomObjet, String typeObjet, User utilisateur) {
        this.nomObjet = nomObjet;
        this.typeObjet = typeObjet;
        this.utilisateur = utilisateur;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomObjet() {
        return nomObjet;
    }

    public void setNomObjet(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    public String getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }
}

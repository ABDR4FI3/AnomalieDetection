// com/example/app/entities/Anomalie.java
package com.example.app.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomalies")
public class Anomalie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeAnomalie;
    private String description;
    private LocalDateTime dateDetection;

    @Enumerated(EnumType.STRING)
    private StatutAnomalie statut;

    // Relation avec ObjetSuivi
    @ManyToOne
    @JoinColumn(name = "objet_suivi_id")
    private ObjetSuivi objetSuivi;

    // Constructeurs
    public Anomalie() {}

    public Anomalie(String typeAnomalie, String description, LocalDateTime dateDetection, StatutAnomalie statut, ObjetSuivi objetSuivi) {
        this.typeAnomalie = typeAnomalie;
        this.description = description;
        this.dateDetection = dateDetection;
        this.statut = statut;
        this.objetSuivi = objetSuivi;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeAnomalie() {
        return typeAnomalie;
    }

    public void setTypeAnomalie(String typeAnomalie) {
        this.typeAnomalie = typeAnomalie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDetection() {
        return dateDetection;
    }

    public void setDateDetection(LocalDateTime dateDetection) {
        this.dateDetection = dateDetection;
    }

    public StatutAnomalie getStatut() {
        return statut;
    }

    public void setStatut(StatutAnomalie statut) {
        this.statut = statut;
    }

    public ObjetSuivi getObjetSuivi() {
        return objetSuivi;
    }

    public void setObjetSuivi(ObjetSuivi objetSuivi) {
        this.objetSuivi = objetSuivi;
    }
}

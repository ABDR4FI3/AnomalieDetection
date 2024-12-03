package com.example.app.dtos;

import java.time.LocalDateTime;

public class AnomalieDTO {
    private Long id;
    private String typeAnomalie;
    private String description;
    private LocalDateTime dateDetection;
    private String statut;
    private Long objetSuiviId;

    // Constructeurs
    public AnomalieDTO() {}

    public AnomalieDTO(Long id, String typeAnomalie, String description, LocalDateTime dateDetection, String statut, Long objetSuiviId) {
        this.id = id;
        this.typeAnomalie = typeAnomalie;
        this.description = description;
        this.dateDetection = dateDetection;
        this.statut = statut;
        this.objetSuiviId = objetSuiviId;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getObjetSuiviId() {
        return objetSuiviId;
    }

    public void setObjetSuiviId(Long objetSuiviId) {
        this.objetSuiviId = objetSuiviId;
    }
}

// com/example/app/entities/Position.java
package com.example.app.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;

    // Relation avec ObjetSuivi
    @ManyToOne
    @JoinColumn(name = "objet_suivi_id")
    private ObjetSuivi objetSuivi;

    // Constructeurs
    public Position() {}

    public Position(Double latitude, Double longitude, LocalDateTime timestamp, ObjetSuivi objetSuivi) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.objetSuivi = objetSuivi;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ObjetSuivi getObjetSuivi() {
        return objetSuivi;
    }

    public void setObjetSuivi(ObjetSuivi objetSuivi) {
        this.objetSuivi = objetSuivi;
    }
}

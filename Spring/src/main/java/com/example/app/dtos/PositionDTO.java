package com.example.app.dtos;

import java.time.LocalDateTime;

public class PositionDTO {
    private Long id;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
    private Long objetSuiviId;

    // Constructeurs
    public PositionDTO() {}

    public PositionDTO(Long id, Double latitude, Double longitude, LocalDateTime timestamp, Long objetSuiviId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.objetSuiviId = objetSuiviId;
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

    public Long getObjetSuiviId() {
        return objetSuiviId;
    }

    public void setObjetSuiviId(Long objetSuiviId) {
        this.objetSuiviId = objetSuiviId;
    }
}

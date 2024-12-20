package org.example.demo_location.Entity;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Position}
 */
@Value
@Data
public class PositionDto implements Serializable {
    double latitude;
    double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
package com.example.app.repositories;

import com.example.app.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByObjetSuiviId(Long objetSuiviId);
}

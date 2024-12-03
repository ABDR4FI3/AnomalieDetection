package com.example.app.repositories;

import com.example.app.entities.Anomalie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomalieRepository extends JpaRepository<Anomalie, Long> {
    List<Anomalie> findByObjetSuiviId(Long objetSuiviId);
}

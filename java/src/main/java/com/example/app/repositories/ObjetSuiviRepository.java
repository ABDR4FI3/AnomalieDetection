package com.example.app.repositories;

import com.example.app.entities.ObjetSuivi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetSuiviRepository extends JpaRepository<ObjetSuivi, Long> {
    List<ObjetSuivi> findByUtilisateurId(Long utilisateurId);
}

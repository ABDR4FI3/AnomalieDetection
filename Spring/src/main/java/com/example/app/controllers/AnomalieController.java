package com.example.app.controllers;

import com.example.app.dtos.AnomalieDTO;
import com.example.app.services.AnomalieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalieController {

    @Autowired
    private AnomalieService anomalieService;

    // Enregistrer une nouvelle anomalie
    @PostMapping
    public AnomalieDTO saveAnomalie(@RequestBody AnomalieDTO anomalieDTO) {
        return anomalieService.saveAnomalie(anomalieDTO);
    }

    // Obtenir toutes les anomalies
    @GetMapping
    public List<AnomalieDTO> getAllAnomalies() {
        return anomalieService.getAllAnomalies();
    }

    // Mettre à jour le statut d'une anomalie
    @PutMapping("/{id}/statut")
    public AnomalieDTO updateAnomalieStatut(@PathVariable Long id, @RequestBody String statut) {
        return anomalieService.updateAnomalieStatut(id, statut);
    }
}

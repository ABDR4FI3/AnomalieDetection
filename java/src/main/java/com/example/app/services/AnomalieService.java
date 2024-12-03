package com.example.app.services;

import com.example.app.dtos.AnomalieDTO;
import com.example.app.entities.Anomalie;
import com.example.app.entities.StatutAnomalie;
import com.example.app.entities.ObjetSuivi;
import com.example.app.repositories.AnomalieRepository;
import com.example.app.repositories.ObjetSuiviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnomalieService {

    @Autowired
    private AnomalieRepository anomalieRepository;

    @Autowired
    private ObjetSuiviRepository objetSuiviRepository;

    // Enregistrer une anomalie
    public AnomalieDTO saveAnomalie(AnomalieDTO anomalieDTO) {
        Anomalie anomalie = new Anomalie();
        anomalie.setTypeAnomalie(anomalieDTO.getTypeAnomalie());
        anomalie.setDescription(anomalieDTO.getDescription());
        anomalie.setDateDetection(anomalieDTO.getDateDetection());
        anomalie.setStatut(StatutAnomalie.valueOf(anomalieDTO.getStatut()));

        Optional<ObjetSuivi> objetOpt = objetSuiviRepository.findById(anomalieDTO.getObjetSuiviId());
        if (objetOpt.isPresent()) {
            anomalie.setObjetSuivi(objetOpt.get());
        } else {
            // Gérer le cas où l'objet suivi n'existe pas
            return null;
        }

        anomalieRepository.save(anomalie);
        anomalieDTO.setId(anomalie.getId());
        return anomalieDTO;
    }

    // Obtenir toutes les anomalies
    public List<AnomalieDTO> getAllAnomalies() {
        List<Anomalie> anomalies = anomalieRepository.findAll();
        return anomalies.stream()
                .map(ano -> new AnomalieDTO(ano.getId(), ano.getTypeAnomalie(), ano.getDescription(), ano.getDateDetection(), ano.getStatut().name(), ano.getObjetSuivi().getId()))
                .collect(Collectors.toList());
    }

    // Mettre à jour le statut d'une anomalie
    public AnomalieDTO updateAnomalieStatut(Long id, String statut) {
        Optional<Anomalie> anomalieOpt = anomalieRepository.findById(id);
        if (anomalieOpt.isPresent()) {
            Anomalie anomalie = anomalieOpt.get();
            anomalie.setStatut(StatutAnomalie.valueOf(statut));
            anomalieRepository.save(anomalie);
            return new AnomalieDTO(anomalie.getId(), anomalie.getTypeAnomalie(), anomalie.getDescription(), anomalie.getDateDetection(), anomalie.getStatut().name(), anomalie.getObjetSuivi().getId());
        }
        return null;
    }
}

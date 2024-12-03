package com.example.app.services;

import com.example.app.dtos.PositionDTO;
import com.example.app.entities.Position;
import com.example.app.entities.ObjetSuivi;
import com.example.app.repositories.PositionRepository;
import com.example.app.repositories.ObjetSuiviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ObjetSuiviRepository objetSuiviRepository;

    // Ajouter une nouvelle position
    public PositionDTO addPosition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setLatitude(positionDTO.getLatitude());
        position.setLongitude(positionDTO.getLongitude());
        position.setTimestamp(positionDTO.getTimestamp());

        Optional<ObjetSuivi> objetOpt = objetSuiviRepository.findById(positionDTO.getObjetSuiviId());
        if (objetOpt.isPresent()) {
            position.setObjetSuivi(objetOpt.get());
        } else {
            // Gérer le cas où l'objet suivi n'existe pas
            return null;
        }

        positionRepository.save(position);
        positionDTO.setId(position.getId());
        return positionDTO;
    }

    // Obtenir les positions d'un objet suivi
    public List<PositionDTO> getPositionsByObjetSuivi(Long objetSuiviId) {
        List<Position> positions = positionRepository.findByObjetSuiviId(objetSuiviId);
        return positions.stream()
                .map(pos -> new PositionDTO(pos.getId(), pos.getLatitude(), pos.getLongitude(), pos.getTimestamp(), pos.getObjetSuivi().getId()))
                .collect(Collectors.toList());
    }
}

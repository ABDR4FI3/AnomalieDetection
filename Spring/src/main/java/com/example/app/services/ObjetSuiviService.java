package com.example.app.services;

import com.example.app.dtos.ObjetSuiviDTO;
import com.example.app.entities.ObjetSuivi;
import com.example.app.entities.User;
import com.example.app.repositories.ObjetSuiviRepository;
import com.example.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObjetSuiviService {

    @Autowired
    private ObjetSuiviRepository objetSuiviRepository;

    @Autowired
    private UserRepository userRepository;

    // Ajouter un nouvel objet suivi
    public ObjetSuiviDTO addObjetSuivi(ObjetSuiviDTO objetSuiviDTO) {
        ObjetSuivi objetSuivi = new ObjetSuivi();
        objetSuivi.setNomObjet(objetSuiviDTO.getNomObjet());
        objetSuivi.setTypeObjet(objetSuiviDTO.getTypeObjet());

        Optional<User> userOpt = userRepository.findById(objetSuiviDTO.getUtilisateurId());
        if (userOpt.isPresent()) {
            objetSuivi.setUtilisateur(userOpt.get());
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
            return null;
        }

        objetSuiviRepository.save(objetSuivi);
        objetSuiviDTO.setId(objetSuivi.getId());
        return objetSuiviDTO;
    }

    // Obtenir un objet suivi par ID
    public ObjetSuiviDTO getObjetSuivi(Long id) {
        Optional<ObjetSuivi> objetOpt = objetSuiviRepository.findById(id);
        if (objetOpt.isPresent()) {
            ObjetSuivi objetSuivi = objetOpt.get();
            return new ObjetSuiviDTO(objetSuivi.getId(), objetSuivi.getNomObjet(), objetSuivi.getTypeObjet(), objetSuivi.getUtilisateur().getId());
        }
        return null;
    }

    // Mettre à jour un objet suivi
    public ObjetSuiviDTO updateObjetSuivi(Long id, ObjetSuiviDTO objetSuiviDTO) {
        Optional<ObjetSuivi> objetOpt = objetSuiviRepository.findById(id);
        if (objetOpt.isPresent()) {
            ObjetSuivi objetSuivi = objetOpt.get();
            objetSuivi.setNomObjet(objetSuiviDTO.getNomObjet());
            objetSuivi.setTypeObjet(objetSuiviDTO.getTypeObjet());
            objetSuiviRepository.save(objetSuivi);
            return objetSuiviDTO;
        }
        return null;
    }

    // Supprimer un objet suivi
    public boolean deleteObjetSuivi(Long id) {
        if (objetSuiviRepository.existsById(id)) {
            objetSuiviRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

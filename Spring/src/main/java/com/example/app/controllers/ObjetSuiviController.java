package com.example.app.controllers;

import com.example.app.dtos.ObjetSuiviDTO;
import com.example.app.services.ObjetSuiviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/objets-suivis")
public class ObjetSuiviController {

    @Autowired
    private ObjetSuiviService objetSuiviService;

    // Ajouter un nouvel objet suivi
    @PostMapping
    public ObjetSuiviDTO addObjetSuivi(@RequestBody ObjetSuiviDTO objetSuiviDTO) {
        return objetSuiviService.addObjetSuivi(objetSuiviDTO);
    }

    // Obtenir un objet suivi par ID
    @GetMapping("/{id}")
    public ObjetSuiviDTO getObjetSuivi(@PathVariable Long id) {
        return objetSuiviService.getObjetSuivi(id);
    }

    // Mettre à jour un objet suivi
    @PutMapping("/{id}")
    public ObjetSuiviDTO updateObjetSuivi(@PathVariable Long id, @RequestBody ObjetSuiviDTO objetSuiviDTO) {
        return objetSuiviService.updateObjetSuivi(id, objetSuiviDTO);
    }

    // Supprimer un objet suivi
    @DeleteMapping("/{id}")
    public void deleteObjetSuivi(@PathVariable Long id) {
        objetSuiviService.deleteObjetSuivi(id);
    }
}

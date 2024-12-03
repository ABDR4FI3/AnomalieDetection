package com.example.app.controllers;

import com.example.app.dtos.PositionDTO;
import com.example.app.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    // Ajouter une nouvelle position
    @PostMapping
    public PositionDTO addPosition(@RequestBody PositionDTO positionDTO) {
        return positionService.addPosition(positionDTO);
    }

    // Obtenir les positions d'un objet suivi
    @GetMapping("/objet-suivi/{id}")
    public List<PositionDTO> getPositionsByObjetSuivi(@PathVariable Long id) {
        return positionService.getPositionsByObjetSuivi(id);
    }
}

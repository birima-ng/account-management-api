package com.atom.artaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.atom.artaccount.dto.ReclamationDTO;
import com.atom.artaccount.model.Reclamation;
import com.atom.artaccount.service.ReclamationService;

@RestController
@CrossOrigin
public class FluxController {
	
    @Autowired
    private ReclamationService reclamationService;


    @PostMapping("/api/flux")
    public ResponseEntity<String> recevoirFlux(@RequestBody ReclamationDTO flux) {

        System.out.println("Nom : " + flux.getNom());
        System.out.println("Prénom : " + flux.getPrenom());
        reclamationService.createReclamation(new Reclamation(flux));
        return ResponseEntity.ok("Flux Meta reçu avec succès");
    }
    
    

   
}

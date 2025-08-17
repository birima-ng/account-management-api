package com.atom.artaccount.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atom.artaccount.model.Indicateur;
import com.atom.artaccount.service.IndicateurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class IndicateurController {
    @Autowired
    private IndicateurService indicateurService;

	 
	 @GetMapping("/api/indicateur-page")
	 public Page<Indicateur> getIndicateurPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return indicateurService.getAll(pageable);
	 }
	 
    @GetMapping("/api/indicateur")
    public List<Indicateur> getAllIndicateurs() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(indicateurService.getAllIndicateurs());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return indicateurService.getAllIndicateurs();
    }

    @GetMapping("/api/indicateur/{id}")
    public ResponseEntity<Indicateur> getIndicateurById(@PathVariable String id) {
        Optional<Indicateur> indicateur = indicateurService.getIndicateurById(id);
        if (indicateur.isPresent()) {
            return ResponseEntity.ok(indicateur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/indicateur")
    public Indicateur createIndicateur(@RequestBody Indicateur indicateur) {
    	if(indicateurService.existsByCode(indicateur.getCode())) {
    		return null;
    	}
        return indicateurService.createIndicateur(indicateur);
    }

    @PutMapping("/api/indicateur/{id}")
    public ResponseEntity<Indicateur> updateIndicateur(@PathVariable String id, @RequestBody Indicateur indicateurDetails) {

    	Indicateur updatedIndicateur = indicateurService.updateIndicateur(id, indicateurDetails);
    	
        return ResponseEntity.ok(updatedIndicateur);
    }

    @DeleteMapping("/api/indicateur/{id}")
    public ResponseEntity<Void> deleteIndicateur(@PathVariable String id) {
        indicateurService.deleteIndicateur(id);
        return ResponseEntity.noContent().build();
    }
    
}

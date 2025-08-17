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
import com.atom.artaccount.model.FrequenceCollecte;
import com.atom.artaccount.service.FrequenceCollecteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class FrequenceCollecteController {
    @Autowired
    private FrequenceCollecteService frequencecollecteService;

	 
	 @GetMapping("/api/frequence-collecte-page")
	 public Page<FrequenceCollecte> getFrequenceCollectePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return frequencecollecteService.getAll(pageable);
	 }
	 
    @GetMapping("/api/frequence-collecte")
    public List<FrequenceCollecte> getAllFrequenceCollectes() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(frequencecollecteService.getAllFrequenceCollectes());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return frequencecollecteService.getAllFrequenceCollectes();
    }

    @GetMapping("/api/frequence-collecte/{id}")
    public ResponseEntity<FrequenceCollecte> getFrequenceCollecteById(@PathVariable String id) {
        Optional<FrequenceCollecte> frequencecollecte = frequencecollecteService.getFrequenceCollecteById(id);
        if (frequencecollecte.isPresent()) {
            return ResponseEntity.ok(frequencecollecte.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/frequence-collecte")
    public FrequenceCollecte createFrequenceCollecte(@RequestBody FrequenceCollecte frequencecollecte) {
    	if(frequencecollecteService.existsByLibelle(frequencecollecte.getLibelle())) {
    		return null;
    	}
        return frequencecollecteService.createFrequenceCollecte(frequencecollecte);
    }

    @PutMapping("/api/frequence-collecte/{id}")
    public ResponseEntity<FrequenceCollecte> updateFrequenceCollecte(@PathVariable String id, @RequestBody FrequenceCollecte frequencecollecteDetails) {

    	FrequenceCollecte updatedFrequenceCollecte = frequencecollecteService.updateFrequenceCollecte(id, frequencecollecteDetails);
    	
        return ResponseEntity.ok(updatedFrequenceCollecte);
    }

    @DeleteMapping("/api/frequence-collecte/{id}")
    public ResponseEntity<Void> deleteFrequenceCollecte(@PathVariable String id) {
        frequencecollecteService.deleteFrequenceCollecte(id);
        return ResponseEntity.noContent().build();
    }
    
}

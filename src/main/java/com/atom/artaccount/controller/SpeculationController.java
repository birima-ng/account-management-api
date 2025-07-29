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

import com.atom.artaccount.model.Speculation;
import com.atom.artaccount.service.SpeculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class SpeculationController {
    @Autowired
    private SpeculationService speculationService;

	 @GetMapping("/api/speculation-page")
	 public Page<Speculation> getSpeculationPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return speculationService.getAll(pageable);
	 }
	 
    @GetMapping("/api/speculation")
    public List<Speculation> getAllSpeculations() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(speculationService.getAllSpeculations());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return speculationService.getAllSpeculations();
    }

    @GetMapping("/api/speculation/{id}")
    public ResponseEntity<Speculation> getSpeculationById(@PathVariable String id) {
        Optional<Speculation> speculation = speculationService.getSpeculationById(id);
        if (speculation.isPresent()) {
            return ResponseEntity.ok(speculation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/speculation")
    public Speculation createSpeculation(@RequestBody Speculation speculation) {
    	if(speculationService.existsByCode(speculation.getCode())) {
    		return null;
    	}
        return speculationService.createSpeculation(speculation);
    }

    @PutMapping("/api/speculation/{id}")
    public ResponseEntity<Speculation> updateSpeculation(@PathVariable String id, @RequestBody Speculation speculationDetails) {
    	boolean result =speculationService.existsByCodeOrLibelle(speculationDetails.getCode(), speculationDetails.getLibelle());
    	if(result)
    		return null;
    	
    	Speculation updatedSpeculation = speculationService.updateSpeculation(id, speculationDetails);
    	
        return ResponseEntity.ok(updatedSpeculation);
    }

    @DeleteMapping("/api/speculation/{id}")
    public ResponseEntity<Void> deleteSpeculation(@PathVariable String id) {
        speculationService.deleteSpeculation(id);
        return ResponseEntity.noContent().build();
    }
    
}

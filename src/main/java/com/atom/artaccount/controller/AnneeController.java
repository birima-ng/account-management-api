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
import com.atom.artaccount.model.Annee;
import com.atom.artaccount.service.AnneeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class AnneeController {
    @Autowired
    private AnneeService anneeService;

	 
	 @GetMapping("/api/annee-page")
	 public Page<Annee> getAnneePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return anneeService.getAll(pageable);
	 }
	 
    @GetMapping("/api/annee")
    public List<Annee> getAllAnnees() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(anneeService.getAllAnnees());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return anneeService.getAllAnnees();
    }

    @GetMapping("/api/annee/{id}")
    public ResponseEntity<Annee> getAnneeById(@PathVariable String id) {
        Optional<Annee> annee = anneeService.getAnneeById(id);
        if (annee.isPresent()) {
            return ResponseEntity.ok(annee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/annee")
    public Annee createAnnee(@RequestBody Annee annee) {
    	if(anneeService.existsByLibelle(annee.getLibelle())) {
    		return null;
    	}
        return anneeService.createAnnee(annee);
    }

    @PutMapping("/api/annee/{id}")
    public ResponseEntity<Annee> updateAnnee(@PathVariable String id, @RequestBody Annee anneeDetails) {

    	Annee updatedAnnee = anneeService.updateAnnee(id, anneeDetails);
    	
        return ResponseEntity.ok(updatedAnnee);
    }

    @DeleteMapping("/api/annee/{id}")
    public ResponseEntity<Void> deleteAnnee(@PathVariable String id) {
        anneeService.deleteAnnee(id);
        return ResponseEntity.noContent().build();
    }
    
}

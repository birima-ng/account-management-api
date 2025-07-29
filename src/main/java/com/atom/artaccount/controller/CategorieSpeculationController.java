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

import com.atom.artaccount.model.CategorieSpeculation;
import com.atom.artaccount.service.CategorieSpeculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class CategorieSpeculationController {
    @Autowired
    private CategorieSpeculationService categoriespeculationService;

	 
	 @GetMapping("/api/categorie-speculation-page")
	 public Page<CategorieSpeculation> getCategorieSpeculationPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return categoriespeculationService.getAll(pageable);
	 }
	 
    @GetMapping("/api/categorie-speculation")
    public List<CategorieSpeculation> getAllCategorieSpeculations() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(categoriespeculationService.getAllCategorieSpeculations());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return categoriespeculationService.getAllCategorieSpeculations();
    }

    @GetMapping("/api/categorie-speculation/{id}")
    public ResponseEntity<CategorieSpeculation> getCategorieSpeculationById(@PathVariable String id) {
        Optional<CategorieSpeculation> categoriespeculation = categoriespeculationService.getCategorieSpeculationById(id);
        if (categoriespeculation.isPresent()) {
            return ResponseEntity.ok(categoriespeculation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/categorie-speculation")
    public CategorieSpeculation createCategorieSpeculation(@RequestBody CategorieSpeculation categoriespeculation) {
    	if(categoriespeculationService.existsByCode(categoriespeculation.getCode())) {
    		return null;
    	}
        return categoriespeculationService.createCategorieSpeculation(categoriespeculation);
    }

    @PutMapping("/api/categorie-speculation/{id}")
    public ResponseEntity<CategorieSpeculation> updateCategorieSpeculation(@PathVariable String id, @RequestBody CategorieSpeculation categoriespeculationDetails) {
    	boolean result =categoriespeculationService.existsByCodeOrLibelle(categoriespeculationDetails.getCode(), categoriespeculationDetails.getLibelle());
    	if(result)
    		return null;
    	
    	CategorieSpeculation updatedCategorieSpeculation = categoriespeculationService.updateCategorieSpeculation(id, categoriespeculationDetails);
    	
        return ResponseEntity.ok(updatedCategorieSpeculation);
    }

    @DeleteMapping("/api/categorie-speculation/{id}")
    public ResponseEntity<Void> deleteCategorieSpeculation(@PathVariable String id) {
        categoriespeculationService.deleteCategorieSpeculation(id);
        return ResponseEntity.noContent().build();
    }
    
}

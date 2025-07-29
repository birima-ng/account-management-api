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

import com.atom.artaccount.model.Systeme;
import com.atom.artaccount.service.SystemeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class SystemeController {
    @Autowired
    private SystemeService systemeService;

	 @GetMapping("/api/systeme-page")
	 public Page<Systeme> getSystemePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return systemeService.getAll(pageable);
	 }
	 
    @GetMapping("/api/systeme")
    public List<Systeme> getAllSystemes() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(systemeService.getAllSystemes());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return systemeService.getAllSystemes();
    }

    @GetMapping("/api/systeme/{id}")
    public ResponseEntity<Systeme> getSystemeById(@PathVariable String id) {
        Optional<Systeme> systeme = systemeService.getSystemeById(id);
        if (systeme.isPresent()) {
            return ResponseEntity.ok(systeme.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/systeme")
    public Systeme createSysteme(@RequestBody Systeme systeme) {
    	if(systemeService.existsByCode(systeme.getCode())) {
    		return null;
    	}
        return systemeService.createSysteme(systeme);
    }

    @PutMapping("/api/systeme/{id}")
    public ResponseEntity<Systeme> updateSysteme(@PathVariable String id, @RequestBody Systeme systemeDetails) {
    	boolean result =systemeService.existsByCodeOrLibelle(systemeDetails.getCode(), systemeDetails.getLibelle());
    	if(result)
    		return null;
    	
    	Systeme updatedSysteme = systemeService.updateSysteme(id, systemeDetails);
    	
        return ResponseEntity.ok(updatedSysteme);
    }

    @DeleteMapping("/api/systeme/{id}")
    public ResponseEntity<Void> deleteSysteme(@PathVariable String id) {
        systemeService.deleteSysteme(id);
        return ResponseEntity.noContent().build();
    }
    
}

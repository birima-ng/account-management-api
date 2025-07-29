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

import com.atom.artaccount.model.Decoupage1;
import com.atom.artaccount.service.Decoupage1Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class Decoupage1Controller {
    @Autowired
    private Decoupage1Service decoupage1Service;

	 @GetMapping("/api/decoupage1-page")
	 public Page<Decoupage1> getDecoupage1Pages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return decoupage1Service.getAll(pageable);
	 }
	 
    @GetMapping("/api/decoupage1")
    public List<Decoupage1> getAllDecoupage1s() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(decoupage1Service.getAllDecoupage1s());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return decoupage1Service.getAllDecoupage1s();
    }

    @GetMapping("/api/decoupage1/{id}")
    public ResponseEntity<Decoupage1> getDecoupage1ById(@PathVariable String id) {
        Optional<Decoupage1> decoupage1 = decoupage1Service.getDecoupage1ById(id);
        if (decoupage1.isPresent()) {
            return ResponseEntity.ok(decoupage1.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/decoupage1")
    public Decoupage1 createDecoupage1(@RequestBody Decoupage1 decoupage1) {
    	if(decoupage1Service.existsByCode(decoupage1.getCode())) {
    		return null;
    	}
        return decoupage1Service.createDecoupage1(decoupage1);
    }

    @PutMapping("/api/decoupage1/{id}")
    public ResponseEntity<Decoupage1> updateDecoupage1(@PathVariable String id, @RequestBody Decoupage1 decoupage1Details) {
    	boolean result =decoupage1Service.existsByCodeOrLibelle(decoupage1Details.getCode(), decoupage1Details.getLibelle());
    	if(result)
    		return null;
    	
    	Decoupage1 updatedDecoupage1 = decoupage1Service.updateDecoupage1(id, decoupage1Details);
    	
        return ResponseEntity.ok(updatedDecoupage1);
    }

    @DeleteMapping("/api/decoupage1/{id}")
    public ResponseEntity<Void> deleteDecoupage1(@PathVariable String id) {
        decoupage1Service.deleteDecoupage1(id);
        return ResponseEntity.noContent().build();
    }
    
}

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

import com.atom.artaccount.model.Decoupage2;
import com.atom.artaccount.service.Decoupage2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class Decoupage2Controller {
    @Autowired
    private Decoupage2Service decoupage2Service;

	 @GetMapping("/api/decoupage2-page")
	 public Page<Decoupage2> getDecoupage2Pages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return decoupage2Service.getAll(pageable);
	 }
	 
    @GetMapping("/api/decoupage2")
    public List<Decoupage2> getAllDecoupage2s() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(decoupage2Service.getAllDecoupage2s());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return decoupage2Service.getAllDecoupage2s();
    }

    @GetMapping("/api/decoupage2/{id}")
    public ResponseEntity<Decoupage2> getDecoupage2ById(@PathVariable String id) {
        Optional<Decoupage2> decoupage2 = decoupage2Service.getDecoupage2ById(id);
        if (decoupage2.isPresent()) {
            return ResponseEntity.ok(decoupage2.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/decoupage2")
    public Decoupage2 createDecoupage2(@RequestBody Decoupage2 decoupage2) {
    	if(decoupage2Service.existsByCode(decoupage2.getCode())) {
    		return null;
    	}
        return decoupage2Service.createDecoupage2(decoupage2);
    }

    @PutMapping("/api/decoupage2/{id}")
    public ResponseEntity<Decoupage2> updateDecoupage2(@PathVariable String id, @RequestBody Decoupage2 decoupage2Details) {
    	boolean result =decoupage2Service.existsByCodeOrLibelle(decoupage2Details.getCode(), decoupage2Details.getLibelle());
    	if(result)
    		return null;
    	
    	Decoupage2 updatedDecoupage2 = decoupage2Service.updateDecoupage2(id, decoupage2Details);
    	
        return ResponseEntity.ok(updatedDecoupage2);
    }

    @DeleteMapping("/api/decoupage2/{id}")
    public ResponseEntity<Void> deleteDecoupage2(@PathVariable String id) {
        decoupage2Service.deleteDecoupage2(id);
        return ResponseEntity.noContent().build();
    }
    
}

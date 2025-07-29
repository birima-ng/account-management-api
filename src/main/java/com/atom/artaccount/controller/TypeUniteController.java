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

import com.atom.artaccount.model.TypeUnite;
import com.atom.artaccount.service.TypeUniteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class TypeUniteController {
    @Autowired
    private TypeUniteService typeuniteService;

	 
	 @GetMapping("/api/type-unite-page")
	 public Page<TypeUnite> getTypeUnitePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return typeuniteService.getAll(pageable);
	 }
	 
    @GetMapping("/api/type-unite")
    public List<TypeUnite> getAllTypeUnites() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(typeuniteService.getAllTypeUnites());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return typeuniteService.getAllTypeUnites();
    }

    @GetMapping("/api/type-unite/{id}")
    public ResponseEntity<TypeUnite> getTypeUniteById(@PathVariable String id) {
        Optional<TypeUnite> typeunite = typeuniteService.getTypeUniteById(id);
        if (typeunite.isPresent()) {
            return ResponseEntity.ok(typeunite.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/type-unite")
    public TypeUnite createTypeUnite(@RequestBody TypeUnite typeunite) {
    	if(typeuniteService.existsByLibelle(typeunite.getLibelle())) {
    		return null;
    	}
        return typeuniteService.createTypeUnite(typeunite);
    }

    @PutMapping("/api/type-unite/{id}")
    public ResponseEntity<TypeUnite> updateTypeUnite(@PathVariable String id, @RequestBody TypeUnite typeuniteDetails) {

    	TypeUnite updatedTypeUnite = typeuniteService.updateTypeUnite(id, typeuniteDetails);
    	
        return ResponseEntity.ok(updatedTypeUnite);
    }

    @DeleteMapping("/api/type-unite/{id}")
    public ResponseEntity<Void> deleteTypeUnite(@PathVariable String id) {
        typeuniteService.deleteTypeUnite(id);
        return ResponseEntity.noContent().build();
    }
    
}

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

import com.atom.artaccount.model.CampagneAgricol;
import com.atom.artaccount.service.CampagneAgricolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class CampagneAgricolController {
    @Autowired
    private CampagneAgricolService campagneagricolService;

	 
	 @GetMapping("/api/campagne-agricol-page")
	 public Page<CampagneAgricol> getCampagneAgricolPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return campagneagricolService.getAll(pageable);
	 }
	 
    @GetMapping("/api/campagne-agricol")
    public List<CampagneAgricol> getAllCampagneAgricols() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(campagneagricolService.getAllCampagneAgricols());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return campagneagricolService.getAllCampagneAgricols();
    }

    @GetMapping("/api/campagne-agricol/{id}")
    public ResponseEntity<CampagneAgricol> getCampagneAgricolById(@PathVariable String id) {
        Optional<CampagneAgricol> campagneagricol = campagneagricolService.getCampagneAgricolById(id);
        if (campagneagricol.isPresent()) {
            return ResponseEntity.ok(campagneagricol.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/campagne-agricol")
    public CampagneAgricol createCampagneAgricol(@RequestBody CampagneAgricol campagneagricol) {
    	if(campagneagricolService.existsByCode(campagneagricol.getCode())) {
    		return null;
    	}
        return campagneagricolService.createCampagneAgricol(campagneagricol);
    }

    @PutMapping("/api/campagne-agricol/{id}")
    public ResponseEntity<CampagneAgricol> updateCampagneAgricol(@PathVariable String id, @RequestBody CampagneAgricol campagneagricolDetails) {
    	boolean result =campagneagricolService.existsByCodeOrLibelle(campagneagricolDetails.getCode(), campagneagricolDetails.getLibelle());
    	if(result)
    		return null;
    	
    	CampagneAgricol updatedCampagneAgricol = campagneagricolService.updateCampagneAgricol(id, campagneagricolDetails);
    	
        return ResponseEntity.ok(updatedCampagneAgricol);
    }

    @DeleteMapping("/api/campagne-agricol/{id}")
    public ResponseEntity<Void> deleteCampagneAgricol(@PathVariable String id) {
        campagneagricolService.deleteCampagneAgricol(id);
        return ResponseEntity.noContent().build();
    }
    
}

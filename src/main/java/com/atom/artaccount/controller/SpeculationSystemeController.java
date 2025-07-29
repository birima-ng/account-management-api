package com.atom.artaccount.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.model.SpeculationSysteme;
import com.atom.artaccount.service.SpeculationSystemeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class SpeculationSystemeController {
    @Autowired
    private SpeculationSystemeService speculationsystemeService;

	 @RequestMapping(value="/api/speculation-systeme1", method=RequestMethod.GET)
	    public MappingJacksonValue listAcheminement() {
		    Iterable<SpeculationSysteme> acheminements = speculationsystemeService.getAllSpeculationSystemes();
	        MappingJacksonValue acheminementsFiltres = new MappingJacksonValue(acheminements);
	        return acheminementsFiltres;
	    }
	 
	 @GetMapping("/api/speculation-systeme-page")
	 public Page<SpeculationSysteme> getSpeculationSystemePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return speculationsystemeService.getAll(pageable);
	 }
	 
    @GetMapping("/api/speculation-systeme")
    public List<SpeculationSysteme> getAllSpeculationSystemes() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(speculationsystemeService.getAllSpeculationSystemes());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return speculationsystemeService.getAllSpeculationSystemes();
    }

    @GetMapping("/api/speculation-systeme/{id}")
    public ResponseEntity<SpeculationSysteme> getSpeculationSystemeById(@PathVariable String id) {
        Optional<SpeculationSysteme> speculationsysteme = speculationsystemeService.getSpeculationSystemeById(id);
        if (speculationsysteme.isPresent()) {
            return ResponseEntity.ok(speculationsysteme.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/speculation-systeme")
    public SpeculationSysteme createSpeculationSysteme(@RequestBody SpeculationSysteme speculationsysteme) {

    	return speculationsystemeService.createSpeculationSysteme(speculationsysteme);
    }

    @PutMapping("/api/speculation-systeme/{id}")
    public ResponseEntity<SpeculationSysteme> updateSpeculationSysteme(@PathVariable String id, @RequestBody SpeculationSysteme speculationsystemeDetails) {
    	SpeculationSysteme updatedSpeculationSysteme = speculationsystemeService.updateSpeculationSysteme(id, speculationsystemeDetails);
        return ResponseEntity.ok(updatedSpeculationSysteme);
    }

    @DeleteMapping("/api/speculation-systeme/{id}")
    public ResponseEntity<Void> deleteSpeculationSysteme(@PathVariable String id) {
        speculationsystemeService.deleteSpeculationSysteme(id);
        return ResponseEntity.noContent().build();
    }
    
}

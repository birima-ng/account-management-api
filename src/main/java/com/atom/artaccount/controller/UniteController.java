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

import com.atom.artaccount.model.Unite;
import com.atom.artaccount.service.UniteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class UniteController {
    @Autowired
    private UniteService uniteService;

	 @RequestMapping(value="/api/unite1", method=RequestMethod.GET)
	    public MappingJacksonValue listAcheminement() {
		    Iterable<Unite> acheminements = uniteService.getAllUnites();
	        MappingJacksonValue acheminementsFiltres = new MappingJacksonValue(acheminements);
	        return acheminementsFiltres;
	    }
	 
	 @GetMapping("/api/unite-page")
	 public Page<Unite> getUnitePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return uniteService.getAll(pageable);
	 }
	 
    @GetMapping("/api/unite")
    public List<Unite> getAllUnites() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(uniteService.getAllUnites());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return uniteService.getAllUnites();
    }

    @GetMapping("/api/unite/{id}")
    public ResponseEntity<Unite> getUniteById(@PathVariable String id) {
        Optional<Unite> unite = uniteService.getUniteById(id);
        if (unite.isPresent()) {
            return ResponseEntity.ok(unite.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/unite")
    public Unite createUnite(@RequestBody Unite unite) {
    	if(uniteService.existsByCode(unite.getCode())) {
    		return null;
    	}
        return uniteService.createUnite(unite);
    }

    @PutMapping("/api/unite/{id}")
    public ResponseEntity<Unite> updateUnite(@PathVariable String id, @RequestBody Unite uniteDetails) {
    	boolean result =uniteService.existsByCodeOrLibelle(uniteDetails.getCode(), uniteDetails.getLibelle());
    	if(result)
    		return null;
    	
    	Unite updatedUnite = uniteService.updateUnite(id, uniteDetails);
    	
        return ResponseEntity.ok(updatedUnite);
    }

    @DeleteMapping("/api/unite/{id}")
    public ResponseEntity<Void> deleteUnite(@PathVariable String id) {
        uniteService.deleteUnite(id);
        return ResponseEntity.noContent().build();
    }
    
}

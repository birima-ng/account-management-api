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
import com.atom.artaccount.model.Structure;
import com.atom.artaccount.service.StructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class StructureController {
    @Autowired
    private StructureService structureService;

 
	 @GetMapping("/api/structure-page")
	 public Page<Structure> getStructurePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return structureService.getAll(pageable);
	 }
	 
    @GetMapping("/api/structure")
    public List<Structure> getAllStructures() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(structureService.getAllStructures());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return structureService.getAllStructures();
    }

    @GetMapping("/api/structure/{id}")
    public ResponseEntity<Structure> getStructureById(@PathVariable String id) {
        Optional<Structure> structure = structureService.getStructureById(id);
        if (structure.isPresent()) {
            return ResponseEntity.ok(structure.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/structure")
    public Structure createStructure(@RequestBody Structure structure) {
    	if(structureService.existsByCode(structure.getCode())) {
    		return null;
    	}
        return structureService.createStructure(structure);
    }

    @PutMapping("/api/structure/{id}")
    public ResponseEntity<Structure> updateStructure(@PathVariable String id, @RequestBody Structure structureDetails) {
    	boolean result =structureService.existsByCodeOrLibelle(structureDetails.getCode(), structureDetails.getLibelle());
    	if(result)
    		return null;
    	
    	Structure updatedStructure = structureService.updateStructure(id, structureDetails);
    	
        return ResponseEntity.ok(updatedStructure);
    }

    @DeleteMapping("/api/structure/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable String id) {
        structureService.deleteStructure(id);
        return ResponseEntity.noContent().build();
    }
    
}

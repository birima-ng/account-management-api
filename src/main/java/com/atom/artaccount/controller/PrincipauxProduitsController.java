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

import com.atom.artaccount.Tools;
import com.atom.artaccount.model.PrincipauxProduits;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.PrincipauxProduitsService;
import com.atom.artaccount.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class PrincipauxProduitsController {
	
    @Autowired
    private PrincipauxProduitsService principauxproduitsService;
    
    @Autowired
    private UserService userService;

	 @GetMapping("/api/principaux-produits-page/notconfigured")
	 public Page<PrincipauxProduits> getPrincipauxProduitsConfiguredFalse(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsService.findBySystemeIdAndPaysIdAndConfigured(user.getSysteme().getId(),  user.getPays().getId(),  false,  pageable);
	 }
	 
	 @GetMapping("/api/principaux-produits-page/isconfigured")
	 public Page<PrincipauxProduits> getPrincipauxProduitsConfiguredTrue(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsService.findBySystemeIdAndPaysIdAndConfigured(user.getSysteme().getId(),  user.getPays().getId(),  false,  pageable);
	 }
	 
	 @GetMapping("/api/principaux-produits-page")
	 public Page<PrincipauxProduits> getPrincipauxProduitsPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsService.findBySystemeIdAndPaysId(user.getSysteme().getId(),  user.getPays().getId(),  pageable);
	 }
	 
    @GetMapping("/api/principaux-produits")
    public List<PrincipauxProduits> getAllPrincipauxProduitss() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(principauxproduitsService.getAllPrincipauxProduitss());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return principauxproduitsService.getAllPrincipauxProduitss();
    }

    @GetMapping("/api/principaux-produits/{id}")
    public ResponseEntity<PrincipauxProduits> getPrincipauxProduitsById(@PathVariable String id) {
        Optional<PrincipauxProduits> principauxproduits = principauxproduitsService.getPrincipauxProduitsById(id);
        if (principauxproduits.isPresent()) {
            return ResponseEntity.ok(principauxproduits.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/principaux-produits")
    public PrincipauxProduits createPrincipauxProduits(@RequestBody PrincipauxProduits principauxproduits) {

    	return principauxproduitsService.createPrincipauxProduits(principauxproduits);
    }

    @PutMapping("/api/principaux-produits/{id}")
    public ResponseEntity<PrincipauxProduits> updatePrincipauxProduits(@PathVariable String id, @RequestBody PrincipauxProduits principauxproduitsDetails) {
    	PrincipauxProduits updatedPrincipauxProduits = principauxproduitsService.updatePrincipauxProduits(id, principauxproduitsDetails);
        return ResponseEntity.ok(updatedPrincipauxProduits);
    }

    @DeleteMapping("/api/principaux-produits/{id}")
    public ResponseEntity<Void> deletePrincipauxProduits(@PathVariable String id) {
        principauxproduitsService.deletePrincipauxProduits(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/api/principaux-produits/configuration")
    public ResponseEntity<Void> updatePrincipauxProduits(@RequestBody List<PrincipauxProduits> principauxproduitss) {
    	System.out.println("############################## log log log ");
    	for(PrincipauxProduits principauxproduits: principauxproduitss) {
    		System.out.println("principauxproduits.getId() "+principauxproduits.getId());
    		principauxproduits.setConfigured(true);
    		principauxproduitsService.updatePrincipauxProduits(principauxproduits.getId(), principauxproduits);
    		
    	}
    	  return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/api/principaux-produits/detache/{id}")
    public ResponseEntity<PrincipauxProduits> updatePrincipauxProduitsDetache(@PathVariable String id, @RequestBody PrincipauxProduits principauxproduitsDetails) {
    	principauxproduitsDetails.setConfigured(false);
    	PrincipauxProduits updatedPrincipauxProduits = principauxproduitsService.updatePrincipauxProduits(id, principauxproduitsDetails);
        return ResponseEntity.ok(updatedPrincipauxProduits);
    }
    
}

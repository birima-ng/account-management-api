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
import com.atom.artaccount.model.PrincipauxProduitsFiche;
import com.atom.artaccount.model.Speculation;
import com.atom.artaccount.model.TypeFicheCollecte;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.PrincipauxProduitsFicheService;
import com.atom.artaccount.service.SpeculationService;
import com.atom.artaccount.service.TypeFicheCollecteService;
import com.atom.artaccount.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class PrincipauxProduitsFicheController {
	
    @Autowired
    private PrincipauxProduitsFicheService principauxproduitsficheService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TypeFicheCollecteService typefichecollecteService;
    
    @Autowired
    private SpeculationService speculationService;
    

	 @GetMapping("/api/principaux-produits-fiche-page/notconfigured")
	 public Page<PrincipauxProduitsFiche> getPrincipauxProduitsFicheConfiguredFalse(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsficheService.findByTypeficheIdAndPaysIdAndConfigured(user.getSysteme().getId(),  user.getPays().getId(),  false,  pageable);
	 }
	 
	 @GetMapping("/api/principaux-produits-fiche-page/isconfigured")
	 public Page<PrincipauxProduitsFiche> getPrincipauxProduitsFicheConfiguredTrue(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsficheService.findByTypeficheIdAndPaysIdAndConfigured(user.getSysteme().getId(),  user.getPays().getId(),  false,  pageable);
	 }
	 
	 @GetMapping("/api/principaux-produits-fiche-page")
	 public Page<PrincipauxProduitsFiche> getPrincipauxProduitsFichePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsficheService.findByTypeficheIdAndPaysId(user.getSysteme().getId(),  user.getPays().getId(),  pageable);
	 }
	 
    @GetMapping("/api/principaux-produits-fiche")
    public List<PrincipauxProduitsFiche> getAllPrincipauxProduitsFiches() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(principauxproduitsficheService.getAllPrincipauxProduitsFiches());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return principauxproduitsficheService.getAllPrincipauxProduitsFiches();
    }

    @GetMapping("/api/principaux-produits-fiche/{id}")
    public ResponseEntity<PrincipauxProduitsFiche> getPrincipauxProduitsFicheById(@PathVariable String id) {
        Optional<PrincipauxProduitsFiche> principauxproduitsfiche = principauxproduitsficheService.getPrincipauxProduitsFicheById(id);
        if (principauxproduitsfiche.isPresent()) {
            return ResponseEntity.ok(principauxproduitsfiche.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/principaux-produits-fiche")
    public PrincipauxProduitsFiche createPrincipauxProduitsFiche(@RequestBody PrincipauxProduitsFiche principauxproduitsfiche) {

    	return principauxproduitsficheService.createPrincipauxProduitsFiche(principauxproduitsfiche);
    }

    @PutMapping("/api/principaux-produits-fiche/{id}")
    public ResponseEntity<PrincipauxProduitsFiche> updatePrincipauxProduitsFiche(@PathVariable String id, @RequestBody PrincipauxProduitsFiche principauxproduitsficheDetails) {
    	PrincipauxProduitsFiche updatedPrincipauxProduitsFiche = principauxproduitsficheService.updatePrincipauxProduitsFiche(id, principauxproduitsficheDetails);
        return ResponseEntity.ok(updatedPrincipauxProduitsFiche);
    }

    @DeleteMapping("/api/principaux-produits-fiche/{id}")
    public ResponseEntity<Void> deletePrincipauxProduitsFiche(@PathVariable String id) {
        principauxproduitsficheService.deletePrincipauxProduitsFiche(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/api/principaux-produits-fiche/configuration")
    public ResponseEntity<Void> updatePrincipauxProduitsFiche(@RequestBody List<PrincipauxProduitsFiche> principauxproduitsfiches) {
    	System.out.println("############################## log log log ");
    	for(PrincipauxProduitsFiche principauxproduitsfiche: principauxproduitsfiches) {
    		System.out.println("principauxproduitsfiche.getId() "+principauxproduitsfiche.getId());
    		principauxproduitsfiche.setConfigured(true);
    		principauxproduitsficheService.updatePrincipauxProduitsFiche(principauxproduitsfiche.getId(), principauxproduitsfiche);
    		
    	}
    	  return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/api/principaux-produits-fiche/detache/{id}")
    public ResponseEntity<PrincipauxProduitsFiche> updatePrincipauxProduitsFicheDetache(@PathVariable String id, @RequestBody PrincipauxProduitsFiche principauxproduitsficheDetails) {
    	principauxproduitsficheDetails.setConfigured(false);
    	PrincipauxProduitsFiche updatedPrincipauxProduitsFiche = principauxproduitsficheService.updatePrincipauxProduitsFiche(id, principauxproduitsficheDetails);
        return ResponseEntity.ok(updatedPrincipauxProduitsFiche);
    }
    
    
    @GetMapping("/api/principaux-produits-fiche/{idspeculation}/speculation/{typefiche}/type-fiche-collecte")
    public ResponseEntity<PrincipauxProduitsFiche> getPrincipauxProduitsFicheById(@PathVariable String idspeculation, @PathVariable String typefiche) {
        
    	User user = Tools.getUser(userService);
        Optional<PrincipauxProduitsFiche> ppfiche = principauxproduitsficheService.findBySpeculationIdAndTypeficheIdAndPaysId(idspeculation, typefiche, user.getPays().getId());
        if (ppfiche.isPresent()) {
        	System.out.println("############### yes ");
            return ResponseEntity.ok(ppfiche.get());
        } else {
        	System.out.println("############### no ");
        	PrincipauxProduitsFiche pfiche = new PrincipauxProduitsFiche();
        	pfiche.setConfigured(false);
        	pfiche.setPays(user.getPays());
        	Optional<TypeFicheCollecte> typefichecollecte = typefichecollecteService.getTypeFicheCollecteById(typefiche);
        	if(typefichecollecte.isPresent()) {
        		pfiche.setTypefiche(typefichecollecte.get());
        	}
        	Optional<Speculation> speculation = speculationService.getSpeculationById(idspeculation);
        	if(speculation.isPresent()) {
        		pfiche.setSpeculation(speculation.get());
        	}
        	pfiche = principauxproduitsficheService.createPrincipauxProduitsFiche(pfiche);
            return  ResponseEntity.ok(pfiche);
        }
    }
    
	 @GetMapping("/api/principaux-produits-fiche-page/{typefiche}/type-fiche-collecte")
	 public Page<PrincipauxProduitsFiche> getPrincipauxProduitsFicheConfigured(@PathVariable String typefiche, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return principauxproduitsficheService.findByTypeficheIdAndPaysIdAndConfigured(typefiche,  user.getPays().getId(),  true,  pageable);
	 }
}

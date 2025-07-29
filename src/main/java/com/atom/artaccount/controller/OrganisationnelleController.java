package com.atom.artaccount.controller;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.model.Organisationnelle;
import com.atom.artaccount.service.OrganisationnelleService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class OrganisationnelleController {
	private static final Logger logger = LoggerFactory.getLogger(OrganisationnelleController.class);
	
	@Autowired
    private OrganisationnelleService organisationnelleService;

	 @RequestMapping(value="/api/organisationnelle", method=RequestMethod.GET)
	    public MappingJacksonValue listOrganisationnelle() {
		    Iterable<Organisationnelle> organisationnelles = organisationnelleService.getAllOrganisationnelles();
	        MappingJacksonValue organisationnellesFiltres = new MappingJacksonValue(organisationnelles);
	        return organisationnellesFiltres;
	    }
	 
	    @GetMapping(value="/api/organisationnelle/{id}")
	    public Organisationnelle searchOrganisationnelleById(@PathVariable String id) {
	        return organisationnelleService.getOrganisationnelleById(id);
	    }
	  
		@RequestMapping(value="/api/organisationnelle", method = RequestMethod.POST)
		public ResponseEntity <Organisationnelle> createOrganisationnelle(@RequestBody Organisationnelle organisationnelle) throws URISyntaxException {
		
			logger.info("Start organisationnelle");
			if(organisationnelleService.existsByName(organisationnelle.getName())) {
	    		return null;
	    	}
			organisationnelle.setDatesave();
			organisationnelle.setDateupdate();
			organisationnelleService.saveOrganisationnelle(organisationnelle);
			
		   return ResponseEntity.ok().body(organisationnelle);
		}
		
		@DeleteMapping(value = "/api/organisationnelle/{id}")
		public @ResponseBody Organisationnelle deleteOrganisationnelle(@PathVariable("id") String id) {
			logger.info("Start Organisationnelle");
			Organisationnelle organisationnelle=organisationnelleService.getOrganisationnelleById(id);
			organisationnelleService.deleteOrganisationnelle(organisationnelle);
			return organisationnelle;
		}
		
		   
	    @PutMapping("/api/organisationnelle/{id}")
	    public ResponseEntity<Organisationnelle> updateOrganisationnelle(@PathVariable String id, @RequestBody Organisationnelle organisationnelleDetails) {


	    	Optional<Organisationnelle> Organisationnellesearch=organisationnelleService.findByName(organisationnelleDetails.getName());
	    	System.out.println("################## "+Organisationnellesearch.isPresent());
	    	if(Organisationnellesearch.isPresent()) {
	    		if(!((Organisationnellesearch.get().getId()).equals(id))) {
	    			return null;
	    		}
	    	}
	    	

	    	Organisationnelle updatedOrganisationnelle = organisationnelleService.updateOrganisationnelle(id, organisationnelleDetails);
	    	
	        return ResponseEntity.ok(updatedOrganisationnelle);
	    
	    }

}

package com.atom.artaccount.controller;
import java.net.URISyntaxException;

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
import com.atom.artaccount.model.Commune;
import com.atom.artaccount.service.CommuneService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class CommuneController {
	private static final Logger logger = LoggerFactory.getLogger(CommuneController.class);
	
	@Autowired
    private CommuneService communeService;

	 @RequestMapping(value="/api/commune", method=RequestMethod.GET)
	    public MappingJacksonValue listCommune() {
		    Iterable<Commune> communes = communeService.getAllCommunes();
	        MappingJacksonValue communesFiltres = new MappingJacksonValue(communes);
	        return communesFiltres;
	    }
	 
	    @GetMapping(value="/api/commune/{id}")
	    public Commune searchCommuneById(@PathVariable String id) {
	        return communeService.getCommuneById(id);
	    }
	  
		@RequestMapping(value="/api/commune", method = RequestMethod.POST)
		public ResponseEntity <Commune> createCommune(@RequestBody Commune commune) throws URISyntaxException {
		
			logger.info("Start commune");
			commune.setDatesave();
			commune.setDateupdate();
			communeService.saveCommune(commune);
			
		   return ResponseEntity.ok().body(commune);
		}
		
		@DeleteMapping(value = "/api/commune/{id}")
		public @ResponseBody Commune deleteCommune(@PathVariable("id") String id) {
			logger.info("Start Commune");
			Commune commune=communeService.getCommuneById(id);
			communeService.deleteCommune(commune);
			return commune;
		}
		
		@RequestMapping(value="/api/commune/{id}/departement", method=RequestMethod.GET)
		public MappingJacksonValue listCommuneByDecoupage2(@PathVariable("id") String id) {
			    Iterable<Commune> communes = communeService.getCommunesByDepartementId(id);
		        MappingJacksonValue decoupage2sFiltres = new MappingJacksonValue(communes);
		        return decoupage2sFiltres;
		}
		
	    @PutMapping("/api/commune/{id}")
	    public ResponseEntity<Commune> updateCommune(@PathVariable String id, @RequestBody Commune commune) {
	    	Commune updatedJourSemaine = communeService.updateCommune(id, commune);
	        return ResponseEntity.ok(updatedJourSemaine);
	    }
}

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

import com.atom.artaccount.model.ParametreGeneraux;
import com.atom.artaccount.service.ParametreGenerauxService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class ParametreGenerauxController {
	private static final Logger logger = LoggerFactory.getLogger(ParametreGenerauxController.class);
	
	@Autowired
    private ParametreGenerauxService parametregenerauxService;

	 @RequestMapping(value="/api/parametre-generaux", method=RequestMethod.GET)
	    public MappingJacksonValue listParametreGeneraux() {
		    Iterable<ParametreGeneraux> parametregenerauxs = parametregenerauxService.getAllParametreGenerauxs();
	        MappingJacksonValue parametregenerauxsFiltres = new MappingJacksonValue(parametregenerauxs);
	        return parametregenerauxsFiltres;
	    }
	 
	    @GetMapping(value="/api/parametre-generaux/{id}")
	    public ParametreGeneraux searchParametreGenerauxById(@PathVariable String id) {
	        return parametregenerauxService.getParametreGenerauxById(id);
	    }
	  
		@RequestMapping(value="/api/parametre-generaux", method = RequestMethod.POST)
		public ResponseEntity <ParametreGeneraux> createParametreGeneraux(@RequestBody ParametreGeneraux parametregeneraux) throws URISyntaxException {
		
			logger.info("Start parametregeneraux");
			if(parametregenerauxService.existsByLibelleOrCode(parametregeneraux.getLibelle(), parametregeneraux.getCode())) {
	    		return null;
	    	}
			parametregeneraux.setDatesave();
			parametregeneraux.setDateupdate();
			parametregenerauxService.saveParametreGeneraux(parametregeneraux);
			
		   return ResponseEntity.ok().body(parametregeneraux);
		}
		
		@DeleteMapping(value = "/api/parametre-generaux/{id}")
		public @ResponseBody ParametreGeneraux deleteParametreGeneraux(@PathVariable("id") String id) {
			logger.info("Start ParametreGeneraux");
			ParametreGeneraux parametregeneraux=parametregenerauxService.getParametreGenerauxById(id);
			parametregenerauxService.deleteParametreGeneraux(parametregeneraux);
			return parametregeneraux;
		}

		   
	    @PutMapping("/api/parametre-generaux/{id}")
	    public ResponseEntity<ParametreGeneraux> updateParametreGeneraux(@PathVariable String id, @RequestBody ParametreGeneraux parametregenerauxDetails) {

	    	Optional<ParametreGeneraux> ParametreGenerauxsearch=parametregenerauxService.findByLibelle(parametregenerauxDetails.getLibelle());
	    	System.out.println("################## "+ParametreGenerauxsearch.isPresent());
	    	if(ParametreGenerauxsearch.isPresent()) {
	    		if(!((ParametreGenerauxsearch.get().getId()).equals(id))) {
	    			return null;
	    		}
	    	}
	    	
	    	 ParametreGenerauxsearch=parametregenerauxService.findByCode(parametregenerauxDetails.getCode());
	    	if(ParametreGenerauxsearch.isPresent()) {
	    		if(!((ParametreGenerauxsearch.get().getId()).equals(id))) {
	    			return null;
	    		}
	    	}
	    	
	    	ParametreGeneraux updatedParametreGeneraux = parametregenerauxService.updateParametreGeneraux(id, parametregenerauxDetails);
	    	
	        return ResponseEntity.ok(updatedParametreGeneraux);
	    
	    
	    
	    }
}

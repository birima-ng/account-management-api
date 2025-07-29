package com.atom.artaccount.controller;
import java.net.URISyntaxException;
import java.util.List;

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

import com.atom.artaccount.model.Departement;
import com.atom.artaccount.service.DepartementService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class DepartementController {
	private static final Logger logger = LoggerFactory.getLogger(DepartementController.class);
	
	@Autowired
    private DepartementService departementService;

	 @RequestMapping(value="/api/departement", method=RequestMethod.GET)
	    public MappingJacksonValue listDecoupage2() {
		    Iterable<Departement> departements = departementService.getAllDepartements();
	        MappingJacksonValue departementsFiltres = new MappingJacksonValue(departements);
	        return departementsFiltres;
	    }
	 
	    @GetMapping(value="/api/departement/{id}")
	    public Departement searchDecoupage2ById(@PathVariable String id) {
	        return departementService.getDepartementById(id);
	    }
	  
		@RequestMapping(value="/api/departement", method = RequestMethod.POST)
		public ResponseEntity <Departement> createDecoupage2(@RequestBody Departement departement) throws URISyntaxException {
		
			logger.info("Start departement");
			departement.setDatesave();
			departement.setDateupdate();
			departementService.saveDepartement(departement);
			
		   return ResponseEntity.ok().body(departement);
		}
		
		@DeleteMapping(value = "/api/departement/{id}")
		public @ResponseBody Departement deleteDecoupage2(@PathVariable("id") String id) {
			logger.info("Start Decoupage2");
			Departement departement=departementService.getDepartementById(id);
			departementService.deleteDepartement(departement);
			return departement;
		}
		

		 @RequestMapping(value="/api/departement/{id}/region", method=RequestMethod.GET)
		    public List<Departement> listDecoupage2ByDecoupage1(@PathVariable("id") String id) {
			    List<Departement> departements = departementService.getDepartementsByRegionId(id);
		       // MappingJacksonValue departementsFiltres = new MappingJacksonValue(departements);
		       // return new ResponseEntity<>(departements, HttpStatus.OK);
			    return departements;
		    }
		 
		    @PutMapping("/api/departement/{id}")
		    public ResponseEntity<Departement> updateDepartement(@PathVariable String id, @RequestBody Departement departement) {
		    	Departement updatedJourSemaine = departementService.updateDepartement(id, departement);
		        return ResponseEntity.ok(updatedJourSemaine);
		    }
		 
}

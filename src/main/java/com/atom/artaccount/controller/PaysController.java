package com.atom.artaccount.controller;
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

import com.atom.artaccount.model.Pays;
import com.atom.artaccount.service.PaysService;

@CrossOrigin
@RestController
public class PaysController {
	private static final Logger logger = LoggerFactory.getLogger(PaysController.class);
	@Autowired
    private PaysService paysService;
	
	 @RequestMapping(value="/api/pays", method=RequestMethod.GET)
	    public MappingJacksonValue listPays() {
		    Iterable<Pays> payss = paysService.getAllPayss();
	        MappingJacksonValue payssFiltres = new MappingJacksonValue(payss);
	        return payssFiltres;
	    }
	 
	    @GetMapping(value="/api/pays/{id}")
	    public Pays searchPaysById(@PathVariable String id) {
	        return paysService.getPaysById(id);
	    }
	    
		@RequestMapping(value="/api/pays", method = RequestMethod.POST)
		public @ResponseBody Pays createPays(@RequestBody Pays pays) {
			logger.info("Start pays");
			pays.setDatesave();
			pays.setDateupdate();
			paysService.savePays(pays);
			return pays;
		}
		
		@DeleteMapping(value = "/api/pays/{id}")
		public @ResponseBody Pays deletePays(@PathVariable("id") String id) {
			logger.info("Start deletepays.");
			Pays pays=paysService.getPaysById(id);
			paysService.deletePays(pays);
			return pays;
		}
		
	    @PutMapping("/api/pays/{id}")
	    public ResponseEntity<Pays> updatePays(@PathVariable String id, @RequestBody Pays pays) {
	    	Pays updatedJourSemaine = paysService.updatePays(id, pays);
	        return ResponseEntity.ok(updatedJourSemaine);
	    }
}

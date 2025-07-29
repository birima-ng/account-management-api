package com.atom.artaccount.controller;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import com.atom.artaccount.dto.RegionDTO;
import com.atom.artaccount.model.Departement;
import com.atom.artaccount.model.Region;
import com.atom.artaccount.service.DepartementService;
import com.atom.artaccount.service.RegionService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class RegionController {
	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
	
	@Autowired
    private RegionService regionService;
	
	//@Autowired
    //private UserService userService;
	
	@Autowired
    private DepartementService departementService;

	 @RequestMapping(value="/api/region", method=RequestMethod.GET)
	    public MappingJacksonValue listRegion() {
		    Iterable<Region> regions = regionService.getAllRegions();
	        MappingJacksonValue regionsFiltres = new MappingJacksonValue(regions);
	        return regionsFiltres;
	    }
	 
	    @GetMapping(value="/api/region/{id}")
	    public Region searchRegionById(@PathVariable String id) {
	        return regionService.getRegionById(id);
	    }
	  
		@RequestMapping(value="/api/region", method = RequestMethod.POST)
		public ResponseEntity <Region> createRegion(@RequestBody Region region) throws URISyntaxException {
		
			logger.info("Start region");
			region.setDatesave();
			region.setDateupdate();
			regionService.saveRegion(region);
			
		   return ResponseEntity.ok().body(region);
		}
		
		@DeleteMapping(value = "/api/region/{id}")
		public @ResponseBody Region deleteRegion(@PathVariable("id") String id) {
			logger.info("Start Region");
			Region region=regionService.getRegionById(id);
			regionService.deleteRegion(region);
			return region;
		}

		 @RequestMapping(value="/api/region/{id}/pays", method=RequestMethod.GET)
		    public MappingJacksonValue listRegionByCountry(@PathVariable("id") String id) {
			    Iterable<Region> regions = regionService.getRegionsByPaysId(id);
		        MappingJacksonValue regionsFiltres = new MappingJacksonValue(regions);
		        return regionsFiltres;
		    }
		 
		    @PutMapping("/api/region/{id}")
		    public ResponseEntity<Region> updateRegion(@PathVariable String id, @RequestBody Region region) {
		    	Region updatedJourSemaine = regionService.updateRegion(id, region);
		        return ResponseEntity.ok(updatedJourSemaine);
		    }
		    
		    
			 @GetMapping(value="/api/region/departement")
			    public List<RegionDTO> listRegionDto() {
				 List<RegionDTO>  regionDTOs = new ArrayList<>();
				 List<Region>  regions = new ArrayList<>();
				 //String username = Tools.getUsername();
				// User user = userService.getUserByUsername(username);
				 regions =regionService.getAllRegions();
				 
				/* if(user!=null&&user.getRegion()==null)
				     regions =regionService.getAllRegions();
				 else
					 regions =regionService.findByIdRegionList(user.getRegion().getId());
				 */
				 for(Region region:regions) {
					
					 RegionDTO regionDTO = new RegionDTO();
					 regionDTO.setId(region.getId());
					 regionDTO.setNom(region.getNom());
					 List<Departement> departements = new ArrayList<>();
					  departements = departementService.getDepartementsByRegionId(region.getId());
					/* if(user!=null&&user.getDepartement()==null)
					     departements = departementService.getDepartementsByRegionId(region.getId());
					 else
						 departements = departementService.findByRegionDepartement(user.getDepartement().getId());
					 */
					 regionDTO.setDepartements(departements);
					 regionDTOs.add(regionDTO);
					 
				 }
			        return regionDTOs;
			    }
			 
}

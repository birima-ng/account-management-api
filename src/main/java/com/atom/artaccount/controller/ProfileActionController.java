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

import com.atom.artaccount.model.ProfileAction;
import com.atom.artaccount.service.ProfileActionService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class ProfileActionController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileActionController.class);
	
	@Autowired
    private ProfileActionService profileactionService;

	 @RequestMapping(value="/api/profil-action", method=RequestMethod.GET)
	    public MappingJacksonValue listProfileAction() {
		    Iterable<ProfileAction> profileactions = profileactionService.getAllProfileActions();
	        MappingJacksonValue profileactionsFiltres = new MappingJacksonValue(profileactions);
	        return profileactionsFiltres;
	    }
	 
	    @GetMapping(value="/api/profil-action/{id}")
	    public ProfileAction searchProfileActionById(@PathVariable String id) {
	        return profileactionService.getProfileActionById(id);
	    }
	  
		@RequestMapping(value="/api/profil-action", method = RequestMethod.POST)
		public ResponseEntity <ProfileAction> createProfileAction(@RequestBody ProfileAction profileaction) throws URISyntaxException {
		
			logger.info("Start profileaction");
			profileaction.setDatesave();
			profileaction.setDateupdate();
			profileactionService.saveProfileAction(profileaction);
			
		   return ResponseEntity.ok().body(profileaction);
		}
		
		 @DeleteMapping(value = "/api/profil-action/{id}")
		  public @ResponseBody ProfileAction deleteProfileAction(@PathVariable("id") String id) {
			logger.info("Start ProfileAction");
			ProfileAction profileaction=profileactionService.getProfileActionById(id);
			profileactionService.deleteProfileAction(profileaction);
		 	return profileaction;
		 }
		

		   @GetMapping(value="/api/profil-action/{id}/action")
		    public List<ProfileAction> listProfileActionByAction(@PathVariable("id") String id) {
			    List<ProfileAction> profileactions = profileactionService.getProfileActionsByActionId(id);
		        return profileactions;
		    }
		   
		   @GetMapping(value="/api/profil-action/{id}/profil")
		    public List<ProfileAction> listProfileActionByProfile(@PathVariable("id") String id) {
			    List<ProfileAction> profileactions = profileactionService.getProfileActionsByProfileId(id);
		        return profileactions;
		    }
		   
		    @PutMapping("/api/profil-action/{id}")
		    public ResponseEntity<ProfileAction> updateProfileAction(@PathVariable String id, @RequestBody ProfileAction profileaction) {
		    	ProfileAction updatedJourSemaine = profileactionService.updateProfileAction(id, profileaction);
		        return ResponseEntity.ok(updatedJourSemaine);
		    }
		    
		    @GetMapping("/api/profil-action/{id}/allowed")
		    public ResponseEntity<ProfileAction> updateProfileActionAllowed(@PathVariable String id) {
		    	ProfileAction updatedJourSemaine = profileactionService.getProfileActionById(id);
		    	updatedJourSemaine.setAllowed(!updatedJourSemaine.isAllowed());
		    	profileactionService.saveProfileAction(updatedJourSemaine);
		        return ResponseEntity.ok(updatedJourSemaine);
		    }
		    
		    @GetMapping("/api/profil-action/{profil}/profil/{action}/action/allowed")
		    public ResponseEntity <Boolean> existsByProfileIdAndActionIdAndAllowed(@PathVariable String profileId, @PathVariable String actionId) {
		    	boolean result = profileactionService.existsByProfileIdAndActionIdAndAllowed(profileId, actionId, true);
		    	return ResponseEntity.ok(result);
		    }
		    
		    @GetMapping("/api/profil-action/{profil}/profil/allowed")
		    public List<String> findAllByProfileIdAndAllowed(@PathVariable String profil){
		    	return  profileactionService.findAllByProfileIdAndAllowed(profil, true);
		    }
}

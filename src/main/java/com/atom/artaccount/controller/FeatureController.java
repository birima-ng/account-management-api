package com.atom.artaccount.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.dto.FeatureDTO;
import com.atom.artaccount.dto.FeatureProfileActionDTO;
import com.atom.artaccount.model.Action;
import com.atom.artaccount.model.Feature;
import com.atom.artaccount.model.Profile;
import com.atom.artaccount.model.ProfileAction;
import com.atom.artaccount.service.ActionService;
import com.atom.artaccount.service.FeatureService;
import com.atom.artaccount.service.ProfileActionService;
import com.atom.artaccount.service.ProfileService;

@RestController
@CrossOrigin
public class FeatureController {
    @Autowired
    private FeatureService featureService;
    
    @Autowired
    private ActionService actionService;
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private ProfileActionService profileactionService;

    @GetMapping("/api/feature")
    public List<Feature> getAllFeatures() {
        return featureService.getAllFeatures();
    }

    @GetMapping("/api/feature/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable String id) {
        Optional<Feature> feature = featureService.getFeatureById(id);
        if (feature.isPresent()) {
            return ResponseEntity.ok(feature.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/feature")
    public Feature createFeature(@RequestBody Feature feature) {
    	if(featureService.existsByNomOrCode(feature.getNom(), feature.getCode())) {
    		return null;
    	}
        return featureService.createFeature(feature);
    }

    @PutMapping("/api/feature/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable String id, @RequestBody Feature featureDetails) {
    	Optional<Feature> featuresearch=featureService.findByNom(featureDetails.getNom());
    	System.out.println("################## "+featuresearch.isPresent());
    	if(featuresearch.isPresent()) {
    		if(!((featuresearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	 featuresearch=featureService.findByCode(featureDetails.getCode());
    	if(featuresearch.isPresent()) {
    		if(!((featuresearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	Feature updatedFeature = featureService.updateFeature(id, featureDetails);
    	
        return ResponseEntity.ok(updatedFeature);
    }

    @DeleteMapping("/api/feature/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable String id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
    
	   @GetMapping(value="/api/feature/{id}/module")
	    public List<Feature> listFeatureByModule(@PathVariable("id") String id) {
		    List<Feature> features = featureService.getFeaturesByModuleId(id);
	        //MappingJacksonValue featuresFiltres = new MappingJacksonValue(features);
	        return features;
	    }
	   
	   
	   @GetMapping("/api/feature/{id}/dto")
	    public ResponseEntity<FeatureDTO> getFeatureByIdDto(@PathVariable String id) {
	        FeatureDTO featureDTO = featureService.getFeatureWithActions(id);
	        return ResponseEntity.ok(featureDTO);
	    }

	    @GetMapping("/api/feature/dto")
	    public ResponseEntity<List<FeatureDTO>> getAllFeaturesDto() {
	        List<FeatureDTO> featureDTOs = featureService.getAllFeaturesWithActions();
	        return ResponseEntity.ok(featureDTOs);
	    }
	    
	    @GetMapping("/api/feature/dto/{id}/module")
	    public ResponseEntity<List<FeatureDTO>> getAllFeaturesDto(@PathVariable String id) {
	        List<FeatureDTO> featureDTOs = featureService.getAllFeaturesWithActions(id);
	        return ResponseEntity.ok(featureDTOs);
	    }
	    
	    @GetMapping("/api/feature-profil-action/dto/{id}/module/{profil}/profil")
	    public ResponseEntity<List<FeatureProfileActionDTO>> getAllFeatureProfilActionsDto(@PathVariable String id, @PathVariable String profil) {
	    	Optional<Profile> profilObject = profileService.getProfileById(profil);
	    	List<Action> actions =actionService.findByFeatureModuleId(id);
	    	for(Action action:actions) {
	    		boolean exist =profileactionService.existsByProfileIdAndActionId(profil, action.getId());
	    		if(!exist) {
	    		ProfileAction profileaction = new ProfileAction();
	    		profileaction.setAction(action);
	    		profileaction.setAllowed(false);
	    		if(profilObject.isPresent())
	    		profileaction.setProfile(profilObject.get());
	    		profileaction.setDatesave();
	    		profileaction.setDateupdate();
	    		profileactionService.saveProfileAction(profileaction);
	    		
	    		}
	    	System.out.println("######################################## "+action.getNom()+" profil "+profil);
	    	}
	    	List<FeatureProfileActionDTO> featureDTOs = featureService.getAllFeaturesWithProfilActionModuleIDs(id,profil);
	        
	        System.out.println("######################################## "+featureDTOs.isEmpty()+" profil "+profil);
	        return ResponseEntity.ok(featureDTOs);
	    }
}

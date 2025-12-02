package com.atom.artaccount.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.atom.artaccount.dto.ActionDTO;
import com.atom.artaccount.dto.FeatureDTO;
import com.atom.artaccount.dto.FeatureProfileActionDTO;
import com.atom.artaccount.dto.ProfileActionDTO;
import com.atom.artaccount.model.Action;
import com.atom.artaccount.model.Feature;
import com.atom.artaccount.model.Module;
import com.atom.artaccount.model.ProfileAction;
import com.atom.artaccount.repository.ActionRepository;
import com.atom.artaccount.repository.FeatureRepository;
import com.atom.artaccount.repository.ModuleRepository;
import com.atom.artaccount.repository.ProfileActionRepository;

@Service
public class FeatureService {
	
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper

    @Autowired
    private FeatureRepository featureRepository;
    
    @Autowired
    private ActionRepository actionRepository;
    
    @Autowired
    private ProfileActionRepository profileactionRepository;
    
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Optional<Feature> getFeatureById(String id) {
        return featureRepository.findById(id);
    }

    public Feature createFeature(Feature feature) {
    	feature.setDatesave();
    	feature.setDateupdate();
        return featureRepository.save(feature);
    }


    public Feature updateFeature(String id, Feature featureDetails) {
    	Optional<Feature> optionalUser = featureRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Feature existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(featureDetails, existingUser);
            existingUser.setDateupdate();
            return featureRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }

    public void deleteFeature(String id) {
        featureRepository.deleteById(id);
    }
    
    public boolean existsByNomOrCode(String nom, String code) {
    	return featureRepository.existsByNomOrCode(nom, code);
    }
    
    public Optional<Feature> findByNomOrCode(String nom, String code){
    	return featureRepository.findByNomOrCode(nom, code);
    }
    
    public Optional<Feature> findByNom(String nom){
    	return featureRepository.findByNom(nom);
    }
    
    public Optional<Feature> findByCode(String code){
    	return featureRepository.findByCode( code);
    }
    
    
	public List<Feature> findByModuleActive(String moduleId, String profilId){
		return featureRepository.findByModuleActive(moduleId, profilId);
	}
    
    public List<Feature> getFeaturesByModuleId(String moduleId) {
        Optional<Module> module = moduleRepository.findById(moduleId);
        return module.map(value -> featureRepository.findByModuleId(moduleId)).orElse(null);
    }
    
    public FeatureDTO getFeatureWithActions(String featureId) {
        Feature feature = featureRepository.findById(featureId)
              .orElseThrow(() -> new ConfigDataResourceNotFoundException(null));

        List<Action> actions = actionRepository.findByFeatureId(featureId);

        FeatureDTO featureDTO = new FeatureDTO();
        featureDTO.setId(feature.getId());
        featureDTO.setNom(feature.getNom());

        List<ActionDTO> actionDTOs = actions.stream()
                .map(action -> {
                    ActionDTO actionDTO = new ActionDTO();
                    actionDTO.setId(action.getId());
                    actionDTO.setNom(action.getNom());
                    return actionDTO;
                })
                .collect(Collectors.toList());

        featureDTO.setActions(actionDTOs);

        return featureDTO;
    }

    public List<FeatureDTO> getAllFeaturesWithActions() {
        List<Feature> features = featureRepository.findAll();

        return features.stream().map(feature -> {
            List<Action> actions = actionRepository.findByFeatureId(feature.getId());

            FeatureDTO featureDTO = new FeatureDTO();
            featureDTO.setId(feature.getId());
            featureDTO.setNom(feature.getNom());

            List<ActionDTO> actionDTOs = actions.stream()
                    .map(action -> {
                        ActionDTO actionDTO = new ActionDTO();
                        actionDTO.setId(action.getId());
                        actionDTO.setNom(action.getNom());
                        return actionDTO;
                    })
                    .collect(Collectors.toList());

            featureDTO.setActions(actionDTOs);

            return featureDTO;
        }).collect(Collectors.toList());
    }
    
    public List<FeatureDTO> getAllFeaturesWithActions(String moduleId) {
        List<Feature> features = featureRepository.findByModuleId(moduleId);

        return features.stream().map(feature -> {
            List<Action> actions = actionRepository.findByFeatureId(feature.getId());

            FeatureDTO featureDTO = new FeatureDTO();
            featureDTO.setId(feature.getId());
            featureDTO.setNom(feature.getNom());

            List<ActionDTO> actionDTOs = actions.stream()
                    .map(action -> {
                        ActionDTO actionDTO = new ActionDTO();
                        actionDTO.setId(action.getId());
                        actionDTO.setNom(action.getNom());
                        return actionDTO;
                    })
                    .collect(Collectors.toList());

            featureDTO.setActions(actionDTOs);

            return featureDTO;
        }).collect(Collectors.toList());
    }
    

    public List<FeatureProfileActionDTO> getAllFeaturesWithProfilActions() {
    	List<FeatureProfileActionDTO> listedtos = new ArrayList<FeatureProfileActionDTO>();
        List<Feature> features = featureRepository.findAll();

        for(Feature f: features) {
        	FeatureProfileActionDTO  listedto = new FeatureProfileActionDTO();
        	listedto.setId(f.getId());
        	listedto.setNom(f.getNom());
        	List<ProfileAction> profileActions =profileactionRepository.findByActionFeatureId(f.getId());
        	List<ProfileActionDTO> profileactionDTOs = new ArrayList<ProfileActionDTO>();
        	for(ProfileAction profileAction: profileActions) {
        		
        		ProfileActionDTO profileactionDTO = new ProfileActionDTO();
        		profileactionDTO.setAllowed(profileAction.isAllowed());
        		profileactionDTO.setId(profileAction.getId());
        		profileactionDTO.setNom(profileAction.getAction().getNom());
        		profileactionDTOs.add(profileactionDTO);
        	} 
        	listedto.setProfilactions(profileactionDTOs);
        	listedtos.add(listedto);
        }
        
        return listedtos;
    }
    

    public List<FeatureProfileActionDTO> getAllFeaturesWithProfilActionModuleIDs(String moduleId) {
    	List<FeatureProfileActionDTO> listedtos = new ArrayList<FeatureProfileActionDTO>();
        List<Feature> features = featureRepository.findByModuleId(moduleId);

        for(Feature f: features) {
        	FeatureProfileActionDTO  listedto = new FeatureProfileActionDTO();
        	listedto.setId(f.getId());
        	listedto.setNom(f.getNom());
        	List<ProfileAction> profileActions =profileactionRepository.findByActionFeatureId(f.getId());
        	List<ProfileActionDTO> profileactionDTOs = new ArrayList<ProfileActionDTO>();
        	for(ProfileAction profileAction: profileActions) {
        		
        		ProfileActionDTO profileactionDTO = new ProfileActionDTO();
        		profileactionDTO.setAllowed(profileAction.isAllowed());
        		profileactionDTO.setId(profileAction.getId());
        		profileactionDTO.setNom(profileAction.getAction().getNom());
        		profileactionDTOs.add(profileactionDTO);
        	} 
        	listedto.setProfilactions(profileactionDTOs);
        	listedtos.add(listedto);
        }
        
        return listedtos;
    }
    
    public List<FeatureProfileActionDTO> getAllFeaturesWithProfilActionModuleIDs(String moduleId, String profilId) {
    	List<FeatureProfileActionDTO> listedtos = new ArrayList<FeatureProfileActionDTO>();
        List<Feature> features = featureRepository.findByModuleId(moduleId);

        for(Feature f: features) {
        	FeatureProfileActionDTO  listedto = new FeatureProfileActionDTO();
        	listedto.setId(f.getId());
        	listedto.setNom(f.getNom());
        	List<ProfileAction> profileActions =profileactionRepository.findByActionFeatureId(f.getId());
        	List<ProfileActionDTO> profileactionDTOs = new ArrayList<ProfileActionDTO>();
        	for(ProfileAction profileAction: profileActions) {
        		if((profileAction.getProfile().getId()).equals(profilId)) {
        		ProfileActionDTO profileactionDTO = new ProfileActionDTO();
        		profileactionDTO.setAllowed(profileAction.isAllowed());
        		profileactionDTO.setId(profileAction.getId());
        		profileactionDTO.setNom(profileAction.getAction().getNom());
        		profileactionDTOs.add(profileactionDTO);
        		}
        	} 
        	listedto.setProfilactions(profileactionDTOs);
        	listedtos.add(listedto);
        }
        
        return listedtos;
    }
    
	public List<Feature> findBySystemeId(String systemeId){
		 return featureRepository.findBySystemeId(systemeId);
	}
}

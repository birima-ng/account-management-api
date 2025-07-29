package com.atom.artaccount.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.dto.FeatureModuleDTO;
import com.atom.artaccount.dto.ModuleDTO;
import com.atom.artaccount.dto.SubFeatureModuleDTO;
import com.atom.artaccount.model.Feature;
import com.atom.artaccount.model.Module;
import com.atom.artaccount.repository.FeatureRepository;
import com.atom.artaccount.repository.ModuleRepository;

@Service
public class ModuleService {
	
    @Autowired
    private FeatureRepository featureRepository;
    
    @Autowired
    private ModuleRepository moduleRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper

    public List<Module> findBySystemeId(String systemeId){
    	return moduleRepository.findBySystemeId(systemeId);
    }
    
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(String id) {
        return moduleRepository.findById(id);
    }

    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public Module updateModule(String id, Module moduleDetails) {
    	Optional<Module> optionalUser = moduleRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Module existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(moduleDetails, existingUser);
            existingUser.setDateupdate();
            return moduleRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    public void deleteModule(String id) {
        moduleRepository.deleteById(id);
    }
    
    public boolean existsByNomOrCode(String nom, String code) {
    	return moduleRepository.existsByNomOrCode(nom, code);
    }
    
    public Optional<Module> findByNomOrCode(String nom, String code){
    	return moduleRepository.findByNomOrCode(nom, code);
    }
    
    public List<Module> findActiveModuleProfil(String profilId){
    	return moduleRepository.findActiveModuleProfil(profilId);
    }
    
    public Optional<Module> findByNom(String nom){
    	return moduleRepository.findByNom(nom);
    }
    
    public Optional<Module> findByCode(String code){
    	return moduleRepository.findByCode( code);
    }
    
    public List<ModuleDTO> getAllModuleFeatures() {
    	
    	List<ModuleDTO> moduleDTOs = new ArrayList<>();
    	
    	List<Module> modules = moduleRepository.findAll();
    	
    	for (Module module: modules) {
    		
    		ModuleDTO moduleDTO =new ModuleDTO();
    		    moduleDTO.setId(module.getId());
    		    moduleDTO.setNom(module.getNom());
    		    
    		    moduleDTO.setPath("");
    		    moduleDTO.setTitle(module.getNom());
    		    moduleDTO.setIcon(module.getIcon());
    		    moduleDTO.setClassName("has-sub");
    		    moduleDTO.setBadge("");
    		    moduleDTO.setBadgeClass("");
    		    moduleDTO.setExternalLink(false);
    		    List<FeatureModuleDTO> featuremoduleDTOs = new ArrayList<>();
    		    List<Feature> features = featureRepository.findByModuleId(module.getId());
    		     
    		        for (Feature feature:features) {
    		        	FeatureModuleDTO featuremoduleDTO = new FeatureModuleDTO();
    		        	featuremoduleDTO.setId(feature.getId());
    		        	featuremoduleDTO.setNom(feature.getNom());
    		        	featuremoduleDTO.setState(feature.getPath());
    		        	
    		        	featuremoduleDTO.setPath(feature.getPath());
    		        	featuremoduleDTO.setTitle(feature.getNom());
    		        	featuremoduleDTO.setIcon("ft-arrow-right submenu-icon");
    		        	featuremoduleDTO.setClassName("");
    		        	featuremoduleDTO.setBadge("");
    		        	featuremoduleDTO.setBadgeClass("");
    		        	featuremoduleDTO.setExternalLink(false);
    	    		    
    		        	featuremoduleDTOs.add(featuremoduleDTO);
    		         }
    		    
    		        moduleDTO.setFeatures(featuremoduleDTOs);
    		        moduleDTOs.add(moduleDTO);
    		    
    	}
    	
    	
        return moduleDTOs;
    }
    
    
    
    public List<ModuleDTO> getAllModuleFeatures(String profilId) {
    	
    	List<ModuleDTO> moduleDTOs = new ArrayList<>();
    	
    	List<Module> modules = moduleRepository.findActiveModuleProfil(profilId);
    	
    	for (Module module: modules) {
    		
    		ModuleDTO moduleDTO =new ModuleDTO();
    		    moduleDTO.setId(module.getId());
    		    moduleDTO.setNom(module.getNom());
    		    
    		    moduleDTO.setPath("");
    		    moduleDTO.setTitle(module.getNom());
    		    moduleDTO.setIcon(module.getIcon());
    		    moduleDTO.setClassName("has-sub");
    		    moduleDTO.setBadge("");
    		    moduleDTO.setBadgeClass("");
    		    moduleDTO.setExternalLink(false);
    		    List<FeatureModuleDTO> featuremoduleDTOs = new ArrayList<>();
    		    List<Feature> features = featureRepository.findByModuleActive(module.getId(),profilId);
    		    
    		     
    		        for (Feature feature:features) {
    		        	FeatureModuleDTO featuremoduleDTO = new FeatureModuleDTO();
    		        	featuremoduleDTO.setId(feature.getId());
    		        	featuremoduleDTO.setNom(feature.getNom());
    		        	featuremoduleDTO.setState(feature.getPath());
    		        	featuremoduleDTO.setPath(feature.getPath());
    		        	featuremoduleDTO.setTitle(feature.getNom());
    		        	featuremoduleDTO.setIcon("ft-arrow-right submenu-icon");
    		        	featuremoduleDTO.setClassName("");
    		        	featuremoduleDTO.setBadge("");
    		        	featuremoduleDTO.setBadgeClass("");
    		        	featuremoduleDTO.setExternalLink(false);
    		        	
    		        	List<SubFeatureModuleDTO> featuremoduleDTOs1 = new ArrayList<>();
    		        	if(feature.isParent()) {
    		        		featuremoduleDTO.setClassName("has-sub");
    		        		 List<Feature> features1 = featureRepository.findByModuleActiveCodeParent(module.getId(),profilId,feature.getId());
    		        		 
    		        		for(Feature feature1: features1) {
    		    		        	SubFeatureModuleDTO featuremoduleDTO1 = new SubFeatureModuleDTO();
    		    		        	featuremoduleDTO1.setId(feature1.getId());
    		    		        	featuremoduleDTO1.setNom(feature1.getNom());
    		    		        	featuremoduleDTO1.setState(feature1.getPath());
    		    		        	featuremoduleDTO1.setPath(feature1.getPath());
    		    		        	featuremoduleDTO1.setTitle(feature1.getNom());
    		    		        	featuremoduleDTO1.setIcon("ft-arrow-right submenu-icon");
    		    		        	featuremoduleDTO1.setClassName("has-sub");
    		    		        	featuremoduleDTO1.setBadge("");
    		    		        	featuremoduleDTO1.setBadgeClass("");
    		    		        	featuremoduleDTO1.setExternalLink(false);
    		    		        	featuremoduleDTOs1.add(featuremoduleDTO1);
    		        		 }
    		        	}
    		        	featuremoduleDTO.setSubfeatures(featuremoduleDTOs1);
    		        	
    		        	featuremoduleDTOs.add(featuremoduleDTO);
    		         }
    		    
    		        moduleDTO.setFeatures(featuremoduleDTOs);
    		        moduleDTOs.add(moduleDTO);
    		    
    	}
    	
    	System.out.println("#############################- moduleDTOs "+moduleDTOs);
    	
        return moduleDTOs;
    }
    
    public String getAllModuleFeatureMenus() {
    	
    	List<ModuleDTO> moduleDTOs = new ArrayList<>();
    	
    	List<Module> modules = moduleRepository.findAll();
    	
    	for (Module module: modules) {
    		
    		ModuleDTO moduleDTO =new ModuleDTO();
    		    moduleDTO.setId(module.getId());
    		    moduleDTO.setNom(module.getNom());
    		    
    		    List<FeatureModuleDTO> featuremoduleDTOs = new ArrayList<>();
    		    List<Feature> features = featureRepository.findByModuleId(module.getId());
    		     
    		        for (Feature feature:features) {
    		        	FeatureModuleDTO featuremoduleDTO = new FeatureModuleDTO();
    		        	featuremoduleDTO.setId(feature.getId());
    		        	featuremoduleDTO.setNom(feature.getNom());
    		        	featuremoduleDTO.setState(feature.getPath());
    		        	featuremoduleDTOs.add(featuremoduleDTO);
    		         }
    		    
    		        moduleDTO.setFeatures(featuremoduleDTOs);
    		        moduleDTOs.add(moduleDTO);
    		    
    	}
    	
    	
        return "[{    \"path\": \"\", \"title\": \"Référentiel\", \"icon\": \"ft-aperture\", \"class\": \"has-sub\", \"badge\": \"\", \"badgeClass\": \"\", \"isExternalLink\": false}]";
    }
}

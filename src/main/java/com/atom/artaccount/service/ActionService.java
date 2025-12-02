package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Action;
import com.atom.artaccount.model.Feature;
import com.atom.artaccount.repository.ActionRepository;
import com.atom.artaccount.repository.FeatureRepository;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;
    
    @Autowired
    private FeatureRepository featureRepository;

    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public Optional<Action> getActionById(String id) {
        return actionRepository.findById(id);
    }

    public Action createAction(Action action) {
    	action.setDatesave();
    	action.setDateupdate();
        return actionRepository.save(action);
    }

    public Action updateAction(String id, Action actionDetails) {
        Action action = actionRepository.findById(id).orElseThrow();
        action.setNom(actionDetails.getNom());
        action.setDateupdate();
        return actionRepository.save(action);
    }

    public List<Action> getActionsByFeatureId(String featureId) {
        Optional<Feature> feature = featureRepository.findById(featureId);
        return feature.map(value -> actionRepository.findByFeatureId(featureId)).orElse(null);
    }
    
    public void deleteAction(String id) {
        actionRepository.deleteById(id);
    }
    
    public boolean existsByNomOrCode(String nom, String code) {
    	return actionRepository.existsByNomOrCode(nom, code);
    }
    
    public boolean existsByCode(String code) {
    	return actionRepository.existsByCode(code);
    }
    
    
    public Optional<Action> findByNomOrCode(String nom, String code){
    	return actionRepository.findByNomOrCode(nom, code);
    }
    
    public Optional<Action> findByNom(String nom){
    	return actionRepository.findByNom(nom);
    }
    
    public Optional<Action> findByCode(String code){
    	return actionRepository.findByCode( code);
    }
    
    public List<Action> findByFeatureModuleId(String moduleId){
    	return actionRepository.findByFeatureModuleId(moduleId);
    }
    
	public List<Action> findByFeatureSystemeId(String systemeId){
		return actionRepository.findByFeatureSystemeId(systemeId);
	}
}

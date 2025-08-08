package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.SpeculationSysteme;
import com.atom.artaccount.repository.SpeculationSystemeRepository;

@Service
public class SpeculationSystemeService {
    @Autowired
    private SpeculationSystemeRepository speculationsystemeRepository;
    
  
    public List<SpeculationSysteme> getAllSpeculationSystemes() {
        return speculationsystemeRepository.findAll();
    }

    public Optional<SpeculationSysteme> getSpeculationSystemeById(String id) {
        return speculationsystemeRepository.findById(id);
    }

    public SpeculationSysteme createSpeculationSysteme(SpeculationSysteme speculationsysteme) {
    	speculationsysteme.setDatesave();
    	speculationsysteme.setDateupdate();
        return speculationsystemeRepository.save(speculationsysteme);
    }

    public SpeculationSysteme updateSpeculationSysteme(String id, SpeculationSysteme speculationsystemeDetails) {
        SpeculationSysteme speculationsysteme = speculationsystemeRepository.findById(id).orElseThrow();
        System.out.println("speculationsysteme "+speculationsysteme.getId());
        speculationsysteme.setConfigured(speculationsystemeDetails.getConfigured());
        speculationsysteme.setDateupdate();
        return speculationsystemeRepository.save(speculationsysteme);
    }

    public void deleteSpeculationSysteme(String id) {
        speculationsystemeRepository.deleteById(id);
    }
    
    public List<SpeculationSysteme> findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(String speculationId, String systemeId, String paysId, Boolean configured){
    	return findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(speculationId, systemeId, paysId, configured);
    	
    }
    public Optional<SpeculationSysteme> findBySpeculationIdAndSystemeIdAndPaysId(String speculationId, String systemeId, String paysId){
    	return speculationsystemeRepository.findBySpeculationIdAndSystemeIdAndPaysId(speculationId, systemeId, paysId);
    }
    
    public Page<SpeculationSysteme> getAll(Pageable pageable ){
    	return speculationsystemeRepository.findAll(pageable);
    }
    
    public Page<SpeculationSysteme> findBySystemeIdAndPaysIdAndConfigured(String systemeId, String paysId, Boolean configured, Pageable pageable){
    	return speculationsystemeRepository.findBySystemeIdAndPaysIdAndConfigured(systemeId,  paysId,  configured,  pageable);
    	
    }

}

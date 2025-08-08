package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.PrincipauxProduits;
import com.atom.artaccount.repository.PrincipauxProduitsRepository;

@Service
public class PrincipauxProduitsService {
    @Autowired
    private PrincipauxProduitsRepository principauxproduitsRepository;
    
  
    public List<PrincipauxProduits> getAllPrincipauxProduitss() {
        return principauxproduitsRepository.findAll();
    }

    public Optional<PrincipauxProduits> getPrincipauxProduitsById(String id) {
        return principauxproduitsRepository.findById(id);
    }

    public PrincipauxProduits createPrincipauxProduits(PrincipauxProduits principauxproduits) {
    	principauxproduits.setDatesave();
    	principauxproduits.setDateupdate();
        return principauxproduitsRepository.save(principauxproduits);
    }

    public PrincipauxProduits updatePrincipauxProduits(String id, PrincipauxProduits principauxproduitsDetails) {
        PrincipauxProduits principauxproduits = principauxproduitsRepository.findById(id).orElseThrow();
        System.out.println("principauxproduits "+principauxproduits.getId());
        principauxproduits.setConfigured(principauxproduitsDetails.getConfigured());
        principauxproduits.setDateupdate();
        return principauxproduitsRepository.save(principauxproduits);
    }

    public void deletePrincipauxProduits(String id) {
        principauxproduitsRepository.deleteById(id);
    }
    
    public List<PrincipauxProduits> findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(String speculationId, String systemeId, String paysId, Boolean configured){
    	return findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(speculationId, systemeId, paysId, configured);
    	
    }
    public Optional<PrincipauxProduits> findBySpeculationIdAndSystemeIdAndPaysId(String speculationId, String systemeId, String paysId){
    	return principauxproduitsRepository.findBySpeculationIdAndSystemeIdAndPaysId(speculationId, systemeId, paysId);
    }
    
    public Page<PrincipauxProduits> getAll(Pageable pageable ){
    	return principauxproduitsRepository.findAll(pageable);
    }
    
    public Page<PrincipauxProduits> findBySystemeIdAndPaysIdAndConfigured(String systemeId, String paysId, Boolean configured, Pageable pageable){
    	return principauxproduitsRepository.findBySystemeIdAndPaysIdAndConfigured(systemeId,  paysId,  configured,  pageable);
    	
    }
    
    public Page<PrincipauxProduits> findBySystemeIdAndPaysId(String systemeId, String paysId, Pageable pageable){
    	return principauxproduitsRepository.findBySystemeIdAndPaysId(systemeId,  paysId,  pageable);
    	
    }

}

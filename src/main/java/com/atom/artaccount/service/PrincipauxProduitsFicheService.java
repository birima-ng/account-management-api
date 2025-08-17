package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.PrincipauxProduitsFiche;
import com.atom.artaccount.repository.PrincipauxProduitsFicheRepository;

@Service
public class PrincipauxProduitsFicheService {
    @Autowired
    private PrincipauxProduitsFicheRepository principauxproduitsRepository;
    
  
    public List<PrincipauxProduitsFiche> getAllPrincipauxProduitsFiches() {
        return principauxproduitsRepository.findAll();
    }

    public Optional<PrincipauxProduitsFiche> getPrincipauxProduitsFicheById(String id) {
        return principauxproduitsRepository.findById(id);
    }

    public PrincipauxProduitsFiche createPrincipauxProduitsFiche(PrincipauxProduitsFiche principauxproduits) {
    	principauxproduits.setDatesave();
    	principauxproduits.setDateupdate();
        return principauxproduitsRepository.save(principauxproduits);
    }

    public PrincipauxProduitsFiche updatePrincipauxProduitsFiche(String id, PrincipauxProduitsFiche principauxproduitsDetails) {
        PrincipauxProduitsFiche principauxproduits = principauxproduitsRepository.findById(id).orElseThrow();
        System.out.println("principauxproduits "+principauxproduits.getId());
        principauxproduits.setConfigured(principauxproduitsDetails.getConfigured());
        principauxproduits.setDateupdate();
        return principauxproduitsRepository.save(principauxproduits);
    }

    public void deletePrincipauxProduitsFiche(String id) {
        principauxproduitsRepository.deleteById(id);
    }
    
    public List<PrincipauxProduitsFiche> findBySpeculationIdAndTypeficheIdAndPaysIdAndConfigured(String speculationId, String typeficheId, String paysId, Boolean configured){
    	return findBySpeculationIdAndTypeficheIdAndPaysIdAndConfigured(speculationId, typeficheId, paysId, configured);
    	
    }
    public Optional<PrincipauxProduitsFiche> findBySpeculationIdAndTypeficheIdAndPaysId(String speculationId, String typeficheId, String paysId){
    	return principauxproduitsRepository.findBySpeculationIdAndTypeficheIdAndPaysId(speculationId, typeficheId, paysId);
    }
    
    public Page<PrincipauxProduitsFiche> getAll(Pageable pageable ){
    	return principauxproduitsRepository.findAll(pageable);
    }
    
    public Page<PrincipauxProduitsFiche> findByTypeficheIdAndPaysIdAndConfigured(String typeficheId, String paysId, Boolean configured, Pageable pageable){
    	return principauxproduitsRepository.findByTypeficheIdAndPaysIdAndConfigured(typeficheId,  paysId,  configured,  pageable);
    	
    }
    
    public Page<PrincipauxProduitsFiche> findByTypeficheIdAndPaysId(String typeficheId, String paysId, Pageable pageable){
    	return principauxproduitsRepository.findByTypeficheIdAndPaysId(typeficheId,  paysId,  pageable);
    	
    }

}

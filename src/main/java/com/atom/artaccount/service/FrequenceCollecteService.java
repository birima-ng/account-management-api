package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.FrequenceCollecte;
import com.atom.artaccount.repository.FrequenceCollecteRepository;

@Service
public class FrequenceCollecteService {
    @Autowired
    private FrequenceCollecteRepository frequencecollecteRepository;
    

    public List<FrequenceCollecte> getAllFrequenceCollectes() {
        return frequencecollecteRepository.findAll();
    }

    public Optional<FrequenceCollecte> getFrequenceCollecteById(String id) {
        return frequencecollecteRepository.findById(id);
    }

    public FrequenceCollecte createFrequenceCollecte(FrequenceCollecte frequencecollecte) {
    	frequencecollecte.setDatesave();
    	frequencecollecte.setDateupdate();
        return frequencecollecteRepository.save(frequencecollecte);
    }

    public FrequenceCollecte updateFrequenceCollecte(String id, FrequenceCollecte frequencecollecteDetails) {
        FrequenceCollecte frequencecollecte = frequencecollecteRepository.findById(id).orElseThrow();
        frequencecollecte.setLibelle(frequencecollecteDetails.getLibelle());
        frequencecollecte.setDateupdate();
        return frequencecollecteRepository.save(frequencecollecte);
    }

    
    public void deleteFrequenceCollecte(String id) {
        frequencecollecteRepository.deleteById(id);
    }
    
    
    public boolean existsByLibelle(String libelle) {
    	return frequencecollecteRepository.existsByLibelle(libelle);
    }

    
    public Optional<FrequenceCollecte> findByLibelle(String libelle){
    	return frequencecollecteRepository.findByLibelle(libelle);
    }
    
    
    public Page<FrequenceCollecte> getAll(Pageable pageable ){
    	return frequencecollecteRepository.findAll(pageable);
    }
}

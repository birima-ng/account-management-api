package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.TypeFicheCollecte;
import com.atom.artaccount.repository.TypeFicheCollecteRepository;

@Service
public class TypeFicheCollecteService {
    @Autowired
    private TypeFicheCollecteRepository typefichecollecteRepository;
    
  
    public List<TypeFicheCollecte> getAllTypeFicheCollectes() {
        return typefichecollecteRepository.findAll();
    }

    public Optional<TypeFicheCollecte> getTypeFicheCollecteById(String id) {
        return typefichecollecteRepository.findById(id);
    }

    public TypeFicheCollecte createTypeFicheCollecte(TypeFicheCollecte typefichecollecte) {
    	typefichecollecte.setDatesave();
    	typefichecollecte.setDateupdate();
        return typefichecollecteRepository.save(typefichecollecte);
    }

    public TypeFicheCollecte updateTypeFicheCollecte(String id, TypeFicheCollecte typefichecollecteDetails) {
        TypeFicheCollecte typefichecollecte = typefichecollecteRepository.findById(id).orElseThrow();
        typefichecollecte.setDateupdate();
        return typefichecollecteRepository.save(typefichecollecte);
    }

    public void deleteTypeFicheCollecte(String id) {
        typefichecollecteRepository.deleteById(id);
    }
    
    public List<TypeFicheCollecte> findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(String speculationId, String systemeId, String paysId, Boolean configured){
    	return findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(speculationId, systemeId, paysId, configured);
    	
    }

    public Page<TypeFicheCollecte> getAll(Pageable pageable ){
    	return typefichecollecteRepository.findAll(pageable);
    }
    
    public Page<TypeFicheCollecte> findBySystemeId(String systemeId, Pageable pageable){
    	return typefichecollecteRepository.findBySystemeId(systemeId, pageable);
    	
    }
    
    public List<TypeFicheCollecte> findBySystemeId(String systemeId){
    	return typefichecollecteRepository.findBySystemeId(systemeId);
    	
    }

}

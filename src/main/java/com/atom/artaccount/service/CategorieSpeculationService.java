package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.CategorieSpeculation;
import com.atom.artaccount.repository.CategorieSpeculationRepository;

@Service
public class CategorieSpeculationService {
    @Autowired
    private CategorieSpeculationRepository categoriespeculationRepository;
    
 
    public List<CategorieSpeculation> getAllCategorieSpeculations() {
        return categoriespeculationRepository.findAll();
    }

    public Optional<CategorieSpeculation> getCategorieSpeculationById(String id) {
        return categoriespeculationRepository.findById(id);
    }

    public CategorieSpeculation createCategorieSpeculation(CategorieSpeculation categoriespeculation) {
    	categoriespeculation.setDatesave();
    	categoriespeculation.setDateupdate();
        return categoriespeculationRepository.save(categoriespeculation);
    }

    public CategorieSpeculation updateCategorieSpeculation(String id, CategorieSpeculation categoriespeculationDetails) {
        CategorieSpeculation categoriespeculation = categoriespeculationRepository.findById(id).orElseThrow();
        categoriespeculation.setLibelle(categoriespeculationDetails.getLibelle());
        categoriespeculation.setDateupdate();
        return categoriespeculationRepository.save(categoriespeculation);
    }

    public void deleteCategorieSpeculation(String id) {
        categoriespeculationRepository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return categoriespeculationRepository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return categoriespeculationRepository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return categoriespeculationRepository.existsByLibelle(libelle);
    }
    
    public Optional<CategorieSpeculation> findByCodeOrLibelle(String code, String libelle){
    	return categoriespeculationRepository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<CategorieSpeculation> findByLibelle(String libelle){
    	return categoriespeculationRepository.findByLibelle(libelle);
    }
    
    public Optional<CategorieSpeculation> findByCode(String code){
    	return categoriespeculationRepository.findByCode( code);
    }
    
    public Page<CategorieSpeculation> getAll(Pageable pageable ){
    	return categoriespeculationRepository.findAll(pageable);
    }
}

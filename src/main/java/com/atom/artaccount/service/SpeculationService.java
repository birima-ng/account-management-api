package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.CategorieSpeculation;
import com.atom.artaccount.model.Speculation;
import com.atom.artaccount.repository.CategorieSpeculationRepository;
import com.atom.artaccount.repository.SpeculationRepository;

@Service
public class SpeculationService {
    @Autowired
    private SpeculationRepository speculationRepository;
    
    @Autowired
    private CategorieSpeculationRepository categoriespeculationRepository;

    public List<Speculation> getAllSpeculations() {
        return speculationRepository.findAll();
    }

    public Optional<Speculation> getSpeculationById(String id) {
        return speculationRepository.findById(id);
    }

    public Speculation createSpeculation(Speculation speculation) {
    	speculation.setDatesave();
    	speculation.setDateupdate();
        return speculationRepository.save(speculation);
    }

    public Speculation updateSpeculation(String id, Speculation speculationDetails) {
        Speculation speculation = speculationRepository.findById(id).orElseThrow();
        speculation.setLibelle(speculationDetails.getLibelle());
        speculation.setDateupdate();
        return speculationRepository.save(speculation);
    }

    public List<Speculation> findByTypeSpeculationId(String categorieId) {
        Optional<CategorieSpeculation> categorie = categoriespeculationRepository.findById(categorieId);
        return categorie.map(value -> speculationRepository.findByCategorieId(categorieId)).orElse(null);
    }
    
    public void deleteSpeculation(String id) {
        speculationRepository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return speculationRepository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return speculationRepository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return speculationRepository.existsByLibelle(libelle);
    }
    
    public Optional<Speculation> findByCodeOrLibelle(String code, String libelle){
    	return speculationRepository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<Speculation> findByLibelle(String libelle){
    	return speculationRepository.findByLibelle(libelle);
    }
    
    public Optional<Speculation> findByCode(String code){
    	return speculationRepository.findByCode( code);
    }
    
    public Page<Speculation> getAll(Pageable pageable ){
    	return speculationRepository.findAll(pageable);
    }
}

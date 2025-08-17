package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Annee;
import com.atom.artaccount.repository.AnneeRepository;

@Service
public class AnneeService {
    @Autowired
    private AnneeRepository anneeRepository;
    

    public List<Annee> getAllAnnees() {
        return anneeRepository.findAll();
    }

    public Optional<Annee> getAnneeById(String id) {
        return anneeRepository.findById(id);
    }

    public Annee createAnnee(Annee annee) {
    	annee.setDatesave();
    	annee.setDateupdate();
        return anneeRepository.save(annee);
    }

    public Annee updateAnnee(String id, Annee anneeDetails) {
        Annee annee = anneeRepository.findById(id).orElseThrow();
        annee.setLibelle(anneeDetails.getLibelle());
        annee.setDateupdate();
        return anneeRepository.save(annee);
    }

    
    public void deleteAnnee(String id) {
        anneeRepository.deleteById(id);
    }
    
    
    public boolean existsByLibelle(int libelle) {
    	return anneeRepository.existsByLibelle(libelle);
    }

    
    public Optional<Annee> findByLibelle(int libelle){
    	return anneeRepository.findByLibelle(libelle);
    }
    
    
    public Page<Annee> getAll(Pageable pageable ){
    	return anneeRepository.findAll(pageable);
    }
}

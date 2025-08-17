package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Indicateur;
import com.atom.artaccount.repository.IndicateurRepository;

@Service
public class IndicateurService {
    @Autowired
    private IndicateurRepository indicateurRepository;
    

    public List<Indicateur> getAllIndicateurs() {
        return indicateurRepository.findAll();
    }

    public Optional<Indicateur> getIndicateurById(String id) {
        return indicateurRepository.findById(id);
    }

    public Indicateur createIndicateur(Indicateur indicateur) {
    	indicateur.setDatesave();
    	indicateur.setDateupdate();
        return indicateurRepository.save(indicateur);
    }

    public Indicateur updateIndicateur(String id, Indicateur indicateurDetails) {
        Indicateur indicateur = indicateurRepository.findById(id).orElseThrow();
        indicateur.setCode(indicateurDetails.getCode());
        indicateur.setDateupdate();
        return indicateurRepository.save(indicateur);
    }

    
    public void deleteIndicateur(String id) {
        indicateurRepository.deleteById(id);
    }
    
    
    public boolean existsByCode(String code) {
    	return indicateurRepository.existsByCode(code);
    }

    
    public Optional<Indicateur> findByCode(String code){
    	return indicateurRepository.findByCode(code);
    }
    
    
    public Page<Indicateur> getAll(Pageable pageable ){
    	return indicateurRepository.findAll(pageable);
    }
}

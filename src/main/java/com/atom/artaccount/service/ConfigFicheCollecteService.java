package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.ConfigFicheCollecte;
import com.atom.artaccount.repository.ConfigFicheCollecteRepository;

@Service
public class ConfigFicheCollecteService {
    @Autowired
    private ConfigFicheCollecteRepository configfichecollecteRepository;
    

    public List<ConfigFicheCollecte> getAllConfigFicheCollectes() {
        return configfichecollecteRepository.findAll();
    }

    public Optional<ConfigFicheCollecte> getConfigFicheCollecteById(String id) {
        return configfichecollecteRepository.findById(id);
    }

    public ConfigFicheCollecte createConfigFicheCollecte(ConfigFicheCollecte configfichecollecte) {
    	configfichecollecte.setDatesave();
    	configfichecollecte.setDateupdate();
        return configfichecollecteRepository.save(configfichecollecte);
    }

    public ConfigFicheCollecte updateConfigFicheCollecte(String id, ConfigFicheCollecte configfichecollecteDetails) {
        ConfigFicheCollecte configfichecollecte = configfichecollecteRepository.findById(id).orElseThrow();
        configfichecollecte.setDateupdate();
        return configfichecollecteRepository.save(configfichecollecte);
    }

    public Page<ConfigFicheCollecte> findByPaysIdAndSystemeIdAndTypeficheId(String paysId, String systemeId, String typefichecollecteId, Pageable pageable) {
        return configfichecollecteRepository.findByPaysIdAndSystemeIdAndTypeficheId(paysId,  systemeId,  typefichecollecteId,  pageable);
    }
    
    public List<ConfigFicheCollecte> findByPaysIdAndSystemeIdAndTypeficheId(String paysId, String systemeId, String typefichecollecteId) {
        return configfichecollecteRepository.findByPaysIdAndSystemeIdAndTypeficheId(paysId,  systemeId,  typefichecollecteId);
    }
    
    public void deleteConfigFicheCollecte(String id) {
        configfichecollecteRepository.deleteById(id);
    }
    
    public Page<ConfigFicheCollecte> getAll(Pageable pageable ){
    	return configfichecollecteRepository.findAll(pageable);
    }
}

package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.CampagneAgricol;
import com.atom.artaccount.repository.CampagneAgricolRepository;

@Service
public class CampagneAgricolService {
    @Autowired
    private CampagneAgricolRepository campagneagricolRepository;
    
 
    public List<CampagneAgricol> getAllCampagneAgricols() {
        return campagneagricolRepository.findAll();
    }

    public Optional<CampagneAgricol> getCampagneAgricolById(String id) {
        return campagneagricolRepository.findById(id);
    }

    public CampagneAgricol createCampagneAgricol(CampagneAgricol campagneagricol) {
    	campagneagricol.setDatesave();
    	campagneagricol.setDateupdate();
        return campagneagricolRepository.save(campagneagricol);
    }

    public CampagneAgricol updateCampagneAgricol(String id, CampagneAgricol campagneagricolDetails) {
        CampagneAgricol campagneagricol = campagneagricolRepository.findById(id).orElseThrow();
        campagneagricol.setLibelle(campagneagricolDetails.getLibelle());
        campagneagricol.setDateupdate();
        return campagneagricolRepository.save(campagneagricol);
    }

    public void deleteCampagneAgricol(String id) {
        campagneagricolRepository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return campagneagricolRepository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return campagneagricolRepository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return campagneagricolRepository.existsByLibelle(libelle);
    }
    
    public Optional<CampagneAgricol> findByCodeOrLibelle(String code, String libelle){
    	return campagneagricolRepository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<CampagneAgricol> findByLibelle(String libelle){
    	return campagneagricolRepository.findByLibelle(libelle);
    }
    
    public Optional<CampagneAgricol> findByCode(String code){
    	return campagneagricolRepository.findByCode( code);
    }
    
    public Page<CampagneAgricol> getAll(Pageable pageable ){
    	return campagneagricolRepository.findAll(pageable);
    }
}

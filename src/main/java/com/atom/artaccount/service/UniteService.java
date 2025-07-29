package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.TypeUnite;
import com.atom.artaccount.model.Unite;
import com.atom.artaccount.repository.TypeUniteRepository;
import com.atom.artaccount.repository.UniteRepository;

@Service
public class UniteService {
    @Autowired
    private UniteRepository uniteRepository;
    
    @Autowired
    private TypeUniteRepository typeuniteRepository;

    public List<Unite> getAllUnites() {
        return uniteRepository.findAll();
    }

    public Optional<Unite> getUniteById(String id) {
        return uniteRepository.findById(id);
    }

    public Unite createUnite(Unite unite) {
    	unite.setDatesave();
    	unite.setDateupdate();
        return uniteRepository.save(unite);
    }

    public Unite updateUnite(String id, Unite uniteDetails) {
        Unite unite = uniteRepository.findById(id).orElseThrow();
        unite.setLibelle(uniteDetails.getLibelle());
        unite.setDateupdate();
        return uniteRepository.save(unite);
    }

    public List<Unite> findByTypeUniteId(String typeuniteId) {
        Optional<TypeUnite> typeunite = typeuniteRepository.findById(typeuniteId);
        return typeunite.map(value -> uniteRepository.findByTypeuniteId(typeuniteId)).orElse(null);
    }
    
    public void deleteUnite(String id) {
        uniteRepository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return uniteRepository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return uniteRepository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return uniteRepository.existsByLibelle(libelle);
    }
    
    public Optional<Unite> findByCodeOrLibelle(String code, String libelle){
    	return uniteRepository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<Unite> findByLibelle(String libelle){
    	return uniteRepository.findByLibelle(libelle);
    }
    
    public Optional<Unite> findByCode(String code){
    	return uniteRepository.findByCode( code);
    }
    
    public Page<Unite> getAll(Pageable pageable ){
    	return uniteRepository.findAll(pageable);
    }
}

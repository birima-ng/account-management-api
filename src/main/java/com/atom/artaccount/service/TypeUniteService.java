package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.TypeUnite;
import com.atom.artaccount.repository.TypeUniteRepository;

@Service
public class TypeUniteService {
    @Autowired
    private TypeUniteRepository typeuniteRepository;
    

    public List<TypeUnite> getAllTypeUnites() {
        return typeuniteRepository.findAll();
    }

    public Optional<TypeUnite> getTypeUniteById(String id) {
        return typeuniteRepository.findById(id);
    }

    public TypeUnite createTypeUnite(TypeUnite typeunite) {
    	typeunite.setDatesave();
    	typeunite.setDateupdate();
        return typeuniteRepository.save(typeunite);
    }

    public TypeUnite updateTypeUnite(String id, TypeUnite typeuniteDetails) {
        TypeUnite typeunite = typeuniteRepository.findById(id).orElseThrow();
        typeunite.setLibelle(typeuniteDetails.getLibelle());
        typeunite.setDateupdate();
        return typeuniteRepository.save(typeunite);
    }

    
    public void deleteTypeUnite(String id) {
        typeuniteRepository.deleteById(id);
    }
    
    
    public boolean existsByLibelle(String libelle) {
    	return typeuniteRepository.existsByLibelle(libelle);
    }

    
    public Optional<TypeUnite> findByLibelle(String libelle){
    	return typeuniteRepository.findByLibelle(libelle);
    }
    
    
    public Page<TypeUnite> getAll(Pageable pageable ){
    	return typeuniteRepository.findAll(pageable);
    }
}

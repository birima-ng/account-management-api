package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Pays;
import com.atom.artaccount.model.Structure;
import com.atom.artaccount.repository.PaysRepository;
import com.atom.artaccount.repository.StructureRepository;

@Service
public class StructureService {
    @Autowired
    private StructureRepository structureRepository;
    
    @Autowired
    private PaysRepository paysRepository;

    public List<Structure> getAllStructures() {
        return structureRepository.findAll();
    }

    public Optional<Structure> getStructureById(String id) {
        return structureRepository.findById(id);
    }

    public Structure createStructure(Structure structure) {
    	structure.setDatesave();
    	structure.setDateupdate();
        return structureRepository.save(structure);
    }

    public Structure updateStructure(String id, Structure structureDetails) {
        Structure structure = structureRepository.findById(id).orElseThrow();
        structure.setLibelle(structureDetails.getLibelle());
        structure.setDateupdate();
        return structureRepository.save(structure);
    }

    public List<Structure> findByPaysId(String featureId) {
        Optional<Pays> pays = paysRepository.findById(featureId);
        return pays.map(value -> structureRepository.findByPaysId(featureId)).orElse(null);
    }
    
    public void deleteStructure(String id) {
        structureRepository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return structureRepository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return structureRepository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return structureRepository.existsByLibelle(libelle);
    }
    
    public Optional<Structure> findByCodeOrLibelle(String code, String libelle){
    	return structureRepository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<Structure> findByLibelle(String libelle){
    	return structureRepository.findByLibelle(libelle);
    }
    
    public Optional<Structure> findByCode(String code){
    	return structureRepository.findByCode( code);
    }
    
    public Page<Structure> getAll(Pageable pageable ){
    	return structureRepository.findAll(pageable);
    }
}

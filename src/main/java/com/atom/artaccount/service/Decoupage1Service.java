package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Decoupage1;
import com.atom.artaccount.model.Pays;
import com.atom.artaccount.repository.Decoupage1Repository;
import com.atom.artaccount.repository.PaysRepository;

@Service
public class Decoupage1Service {
    @Autowired
    private Decoupage1Repository decoupage1Repository;
    
    @Autowired
    private PaysRepository paysRepository;

    public List<Decoupage1> getAllDecoupage1s() {
        return decoupage1Repository.findAll();
    }

    public Optional<Decoupage1> getDecoupage1ById(String id) {
        return decoupage1Repository.findById(id);
    }

    public Decoupage1 createDecoupage1(Decoupage1 decoupage1) {
    	decoupage1.setDatesave();
    	decoupage1.setDateupdate();
        return decoupage1Repository.save(decoupage1);
    }

    public Decoupage1 updateDecoupage1(String id, Decoupage1 decoupage1Details) {
        Decoupage1 decoupage1 = decoupage1Repository.findById(id).orElseThrow();
        decoupage1.setLibelle(decoupage1Details.getLibelle());
        decoupage1.setDateupdate();
        return decoupage1Repository.save(decoupage1);
    }

    public List<Decoupage1> findByTypeDecoupage1Id(String paysId) {
        Optional<Pays> pays = paysRepository.findById(paysId);
        return pays.map(value -> decoupage1Repository.findByPaysId(paysId)).orElse(null);
    }
    
    public void deleteDecoupage1(String id) {
        decoupage1Repository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return decoupage1Repository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return decoupage1Repository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return decoupage1Repository.existsByLibelle(libelle);
    }
    
    public Optional<Decoupage1> findByCodeOrLibelle(String code, String libelle){
    	return decoupage1Repository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<Decoupage1> findByLibelle(String libelle){
    	return decoupage1Repository.findByLibelle(libelle);
    }
    
    public Optional<Decoupage1> findByCode(String code){
    	return decoupage1Repository.findByCode( code);
    }
    
    public Page<Decoupage1> getAll(Pageable pageable ){
    	return decoupage1Repository.findAll(pageable);
    }
}

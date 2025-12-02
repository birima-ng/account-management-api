package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.Decoupage1;
import com.atom.artaccount.model.Decoupage2;
import com.atom.artaccount.repository.Decoupage1Repository;
import com.atom.artaccount.repository.Decoupage2Repository;

@Service
public class Decoupage2Service {
    @Autowired
    private Decoupage2Repository decoupage2Repository;
    
    @Autowired
    private Decoupage1Repository decoupage1Repository;

    public List<Decoupage2> getAllDecoupage2s() {
        return decoupage2Repository.findAll();
    }

    public Optional<Decoupage2> getDecoupage2ById(String id) {
        return decoupage2Repository.findById(id);
    }

    public Decoupage2 createDecoupage2(Decoupage2 decoupage2) {
    	decoupage2.setDatesave();
    	decoupage2.setDateupdate();
        return decoupage2Repository.save(decoupage2);
    }

    public Decoupage2 updateDecoupage2(String id, Decoupage2 decoupage2Details) {
        Decoupage2 decoupage2 = decoupage2Repository.findById(id).orElseThrow();
        decoupage2.setLibelle(decoupage2Details.getLibelle());
        decoupage2.setDateupdate();
        return decoupage2Repository.save(decoupage2);
    }

    public List<Decoupage2> findByTypeDecoupage2Id(String decoupage1Id) {
        Optional<Decoupage1> decoupage1 = decoupage1Repository.findById(decoupage1Id);
        return decoupage1.map(value -> decoupage2Repository.findByDecoupage1Id(decoupage1Id)).orElse(null);
    }
    
    public void deleteDecoupage2(String id) {
        decoupage2Repository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return decoupage2Repository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return decoupage2Repository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return decoupage2Repository.existsByLibelle(libelle);
    }
    
    public Optional<Decoupage2> findByCodeOrLibelle(String code, String libelle){
    	return decoupage2Repository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<Decoupage2> findByLibelle(String libelle){
    	return decoupage2Repository.findByLibelle(libelle);
    }
    
    public Optional<Decoupage2> findByCode(String code){
    	return decoupage2Repository.findByCode( code);
    }
    
    public Page<Decoupage2> getAll(Pageable pageable ){
    	return decoupage2Repository.findAll(pageable);
    }
    
    public 	List<Decoupage2> findByDecoupage1PaysId(String paysId){
    	return decoupage2Repository.findByDecoupage1PaysId(paysId);
    }
    
    public 	Page<Decoupage2> findByDecoupage1PaysSysteme(String paysId, Pageable pageable){
    	return decoupage2Repository.findByDecoupage1PaysId(paysId, pageable);
    }
}

package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atom.artaccount.Tools;
import com.atom.artaccount.model.Systeme;
import com.atom.artaccount.model.User;
import com.atom.artaccount.repository.SystemeRepository;

@Service
public class SystemeService {
    @Autowired
    private SystemeRepository systemeRepository;
    
    @Autowired
    private UserService userService;
    
    public List<Systeme> getAllSystemes() {
        return systemeRepository.findAll();
    }

    public Optional<Systeme> getSystemeById(String id) {
        return systemeRepository.findById(id);
    }

    public Systeme createSysteme(Systeme systeme) {
    	systeme.setDatesave();
    	systeme.setDateupdate();
        return systemeRepository.save(systeme);
    }

    public Systeme updateSysteme(String id, Systeme systemeDetails) {
        Systeme systeme = systemeRepository.findById(id).orElseThrow();
        systeme.setLibelle(systemeDetails.getLibelle());
        systeme.setDateupdate();
        return systemeRepository.save(systeme);
    }

    public void deleteSysteme(String id) {
        systemeRepository.deleteById(id);
    }
    
    public boolean existsByCodeOrLibelle(String code, String libelle) {
    	return systemeRepository.existsByCodeOrLibelle(libelle, code);
    }
    
    public boolean existsByCode(String code) {
    	return systemeRepository.existsByCode(code);
    }
    
    public boolean existsByLibelle(String libelle) {
    	return systemeRepository.existsByLibelle(libelle);
    }
    
    public Optional<Systeme> findByCodeOrLibelle(String code, String libelle){
    	return systemeRepository.findByCodeOrLibelle(code, libelle);
    }
    
    public Optional<Systeme> findByLibelle(String libelle){
    	return systemeRepository.findByLibelle(libelle);
    }
    
    public Optional<Systeme> findByCode(String code){
    	return systemeRepository.findByCode( code);
    }
    
    public Optional<Systeme> findById(String id){
    	return systemeRepository.findById(id);
    }
    
    public Page<Systeme> getAll(Pageable pageable ){
    	return systemeRepository.findAll(pageable);
    }
    
    public Systeme findByIdCurrent(){
   	     User user = Tools.getUser(userService);
   	  Optional<Systeme> systeme = systemeRepository.findById(user.getSysteme().getId());
   	  
   	  if(systeme.isPresent()) {
   		  return systeme.get();
   	  }else {
   		  return null;
   	  }
    }
    
	public Page<Systeme> findById(Pageable pageable){
		User user = Tools.getUser(userService);
		return  systemeRepository.findById( user.getSysteme().getId(), pageable);
	}
}

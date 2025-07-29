package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Commune;
import com.atom.artaccount.model.Departement;
import com.atom.artaccount.repository.CommuneRepository;
import com.atom.artaccount.repository.DepartementRepository;

@Service
public class CommuneService {

    @Autowired
    private CommuneRepository communeRepository;
    
    @Autowired
    private DepartementRepository departementRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper

    public Commune saveCommune(Commune commune) {
        return communeRepository.save(commune);
    }

    public List<Commune> getAllCommunes() {
        return communeRepository.findAll();
    }

    public Commune getCommuneById(String id) {
        return communeRepository.findById(id).orElse(null);
    }
    
    public void deleteCommune(Commune commune) {
         communeRepository.delete(commune);
    }
    
    public List<Commune> getCommunesByDepartementId(String departementId) {
        Optional<Departement> departement = departementRepository.findById(departementId);
        return departement.map(value -> communeRepository.findByDepartementId(departementId)).orElse(null);
    }
    
    public Commune updateCommune(String id, Commune joursemaineDetails) {
    	Optional<Commune> optionalUser = communeRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Commune existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existisngUser
            modelMapper.map(joursemaineDetails, existingUser);
            existingUser.setDateupdate();
            return communeRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    // Other service methods as needed
}
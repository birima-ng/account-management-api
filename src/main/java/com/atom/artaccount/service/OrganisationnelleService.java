package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Organisationnelle;
import com.atom.artaccount.repository.OrganisationnelleRepository;

@Service
public class OrganisationnelleService {

	@Autowired
	private ModelMapper modelMapper; // Injectez le bean ModelMapper

    @Autowired
    private OrganisationnelleRepository OrganisationnelleRepository;

    public Organisationnelle saveOrganisationnelle(Organisationnelle organisationnelle) {
        return OrganisationnelleRepository.save(organisationnelle);
    }

    public List<Organisationnelle> getAllOrganisationnelles() {
        return OrganisationnelleRepository.findAll();
    }

    public Organisationnelle getOrganisationnelleById(String id) {
        return OrganisationnelleRepository.findById(id).orElse(null);
    }
    
    public void deleteOrganisationnelle(Organisationnelle organisationnelle) {
         OrganisationnelleRepository.delete(organisationnelle);
    }

    
    public boolean existsByName(String name) {
    	return OrganisationnelleRepository.existsByName(name);
    }
    
    public Optional<Organisationnelle> findByName(String name){
    	return OrganisationnelleRepository.findByName(name);
    }
    
    public Organisationnelle updateOrganisationnelle(String id, Organisationnelle organisationnelleDetails) {
    	Optional<Organisationnelle> optionalUser = OrganisationnelleRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Organisationnelle existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(organisationnelleDetails, existingUser);
            existingUser.setDateupdate();
            return OrganisationnelleRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    // Other service methods as needed
}
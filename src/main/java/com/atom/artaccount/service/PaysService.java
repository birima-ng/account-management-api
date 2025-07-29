package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Pays;
import com.atom.artaccount.repository.PaysRepository;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper


    public Pays savePays(Pays pays) {
        return paysRepository.save(pays);
    }

    public List<Pays> getAllPayss() {
        return paysRepository.findAll();
    }

    public Pays getPaysById(String id) {
        return paysRepository.findById(id).orElse(null);
    }
    
    public void deletePays(Pays pays) {
         paysRepository.delete(pays);
    }

    public Pays updatePays(String id, Pays paysDetails) {
    	Optional<Pays> optionalUser = paysRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Pays existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(paysDetails, existingUser);
            existingUser.setDateupdate();
            return paysRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    // Other service methods as needed
}
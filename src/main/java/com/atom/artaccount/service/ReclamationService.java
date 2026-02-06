package com.atom.artaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.Reclamation;
import com.atom.artaccount.repository.ReclamationRepository;

@Service
public class ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;
    

    public Reclamation createReclamation(Reclamation reclamation) {
    	reclamation.setDatesave();
    	reclamation.setDateupdate();
        return reclamationRepository.save(reclamation);
    }

   
}

package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Departement;
import com.atom.artaccount.model.Region;
import com.atom.artaccount.repository.DepartementRepository;
import com.atom.artaccount.repository.RegionRepository;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;
    
    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper

    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartementById(String id) {
        return departementRepository.findById(id).orElse(null);
    }
    
	public List<Departement> findByRegionDepartement(String idDepartement){
		return departementRepository.findByRegionDepartement(idDepartement);
	}
	
    public void deleteDepartement(Departement departement) {
         departementRepository.delete(departement);
    }
    
    public List<Departement> getDepartementsByRegionId(String regionId) {
        Optional<Region> region = regionRepository.findById(regionId);
        return region.map(value -> departementRepository.findByRegionId(regionId)).orElse(null);
    }
    
    public Departement updateDepartement(String id, Departement departementDetails) {
    	Optional<Departement> optionalUser = departementRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Departement existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(departementDetails, existingUser);
            existingUser.setDateupdate();
            return departementRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    // Other service methods as needed
}
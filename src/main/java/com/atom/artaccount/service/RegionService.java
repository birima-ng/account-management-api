package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Pays;
import com.atom.artaccount.model.Region;
import com.atom.artaccount.repository.PaysRepository;
import com.atom.artaccount.repository.RegionRepository;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    private PaysRepository paysRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper


	public List<Region> findByIdRegionList(String id){
		return regionRepository.findByIdRegionList(id);
	}
	
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(String id) {
        return regionRepository.findById(id).orElse(null);
    }
    
    public void deleteRegion(Region region) {
         regionRepository.delete(region);
    }
    
    public List<Region> getRegionsByPaysId(String paysId) {
        Optional<Pays> pays = paysRepository.findById(paysId);
        return pays.map(value -> regionRepository.findByPaysId(paysId)).orElse(null);
    }
    
    public Region updateRegion(String id, Region objetDetails) {
    	Optional<Region> optionalUser = regionRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		Region existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(objetDetails, existingUser);
            existingUser.setDateupdate();
            return regionRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    // Other service methods as needed
}
package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.ParametreGeneraux;
import com.atom.artaccount.repository.ParametreGenerauxRepository;

@Service
public class ParametreGenerauxService {

	@Autowired
	private ModelMapper modelMapper; // Injectez le bean ModelMapper

    @Autowired
    private ParametreGenerauxRepository parametregenerauxRepository;


    public ParametreGeneraux saveParametreGeneraux(ParametreGeneraux parametregeneraux) {
        return parametregenerauxRepository.save(parametregeneraux);
    }

    public List<ParametreGeneraux> getAllParametreGenerauxs() {
        return parametregenerauxRepository.findAll();
    }

    public ParametreGeneraux getParametreGenerauxById(String id) {
        return parametregenerauxRepository.findById(id).orElse(null);
    }
    
    public ParametreGeneraux getParametreGenerauxByCode(String id) {
        return parametregenerauxRepository.findByCode(id).orElse(null);
    }
    
    public void deleteParametreGeneraux(ParametreGeneraux parametregeneraux) {
         parametregenerauxRepository.delete(parametregeneraux);
    }
    
    public boolean existsByLibelleOrCode(String libelle, String code) {
    	return parametregenerauxRepository.existsByLibelleOrCode(libelle, code);
    }
    
    public Optional<ParametreGeneraux> findByLibelleOrCode(String libelle, String code){
    	return parametregenerauxRepository.findByLibelleOrCode(libelle, code);
    }
    
    public Optional<ParametreGeneraux> findByLibelle(String libelle){
    	return parametregenerauxRepository.findByLibelle(libelle);
    }
    
    public Optional<ParametreGeneraux> findByCode(String code){
    	return parametregenerauxRepository.findByCode( code);
    }


    public ParametreGeneraux updateParametreGeneraux(String id, ParametreGeneraux parametregenerauxDetails) {
    	Optional<ParametreGeneraux> optionalUser = parametregenerauxRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		ParametreGeneraux existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(parametregenerauxDetails, existingUser);
            existingUser.setDateupdate();
            return parametregenerauxRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    // Other service methods as needed
}
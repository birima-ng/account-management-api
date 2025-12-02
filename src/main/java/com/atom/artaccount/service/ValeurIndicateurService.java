package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.ValeurIndicateur;
import com.atom.artaccount.repository.ValeurIndicateurRepository;

@Service
public class ValeurIndicateurService {
	
    @Autowired
    private ValeurIndicateurRepository valeurindicateurRepository;
    

    public List<ValeurIndicateur> getAllValeurIndicateurs() {
        return valeurindicateurRepository.findAll();
    }

    public Optional<ValeurIndicateur> getValeurIndicateurById(String id) {
        return valeurindicateurRepository.findById(id);
    }

    public ValeurIndicateur createValeurIndicateur(ValeurIndicateur valeurindicateur) {
    	valeurindicateur.setDatesave();
    	valeurindicateur.setDateupdate();
        return valeurindicateurRepository.save(valeurindicateur);
    }

    public ValeurIndicateur updateValeurIndicateur(String id, ValeurIndicateur valeurindicateurDetails) {
        ValeurIndicateur valeurindicateur = valeurindicateurRepository.findById(id).orElseThrow();
        valeurindicateur.setCampagneagricole(valeurindicateurDetails.getCampagneagricole());
        valeurindicateur.setDateupdate();
        return valeurindicateurRepository.save(valeurindicateur);
    }

    public List<ValeurIndicateur> findByIndicateurIdAndPeriodeAndCampagneagricoleIdAndPaysId(String indicateurId, String periode, String campagneagricoleId, String paysId){
    	return valeurindicateurRepository.findByIndicateurIdAndPeriodeAndCampagneagricoleIdAndPaysId(indicateurId ,periode ,campagneagricoleId ,paysId );
    }
    
    public void deleteValeurIndicateur(String id) {
        valeurindicateurRepository.deleteById(id);
    }
    
    
    public Page<ValeurIndicateur> getAll(Pageable pageable ){
    	return valeurindicateurRepository.findAll(pageable);
    }
    
    public List<ValeurIndicateur> findByPeriodeAndIndicateurIdAndRegionIdAndPaysIdAndNiveau(String periode, String indicateurId, String regionId, String paysId, int niveau){
    	return valeurindicateurRepository.findByPeriodeAndIndicateurIdAndRegionIdAndPaysIdAndNiveau(periode, indicateurId, regionId, paysId, niveau);
    }
}

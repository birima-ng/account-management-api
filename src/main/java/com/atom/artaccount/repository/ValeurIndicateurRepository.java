package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.ValeurIndicateur;

public interface ValeurIndicateurRepository extends JpaRepository<ValeurIndicateur, String>, JpaSpecificationExecutor<ValeurIndicateur> {
	
	Optional<ValeurIndicateur> findById(String indicateurId);
	List<ValeurIndicateur> findByIndicateurIdAndPeriodeAndCampagneagricoleIdAndPaysId(String indicateurId, String periode, String campagneagricoleId, String paysId);
	//Optional<ValeurIndicateur> findByPeriodeAndIndicateurIdAndRegionIdAndPaysIdAndNiveau(String periode, String indicateurId, String regionId, String paysId, int niveau);
	List<ValeurIndicateur> findByPeriodeAndIndicateurIdAndRegionIdAndPaysIdAndNiveau(String periode, String indicateurId, String regionId, String paysId, int niveau);
	
	
}
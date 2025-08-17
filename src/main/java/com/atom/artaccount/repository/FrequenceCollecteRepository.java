package com.atom.artaccount.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.FrequenceCollecte;

public interface FrequenceCollecteRepository extends JpaRepository<FrequenceCollecte, String>, JpaSpecificationExecutor<FrequenceCollecte> {
	boolean existsByLibelle(String libelle);
	Optional<FrequenceCollecte> findByLibelle(String libelle);
}
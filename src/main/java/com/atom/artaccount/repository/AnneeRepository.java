package com.atom.artaccount.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.Annee;

public interface AnneeRepository extends JpaRepository<Annee, String>, JpaSpecificationExecutor<Annee> {
	boolean existsByLibelle(int libelle);
	Optional<Annee> findByLibelle(int libelle);
}
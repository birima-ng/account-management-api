package com.atom.artaccount.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.Indicateur;

public interface IndicateurRepository extends JpaRepository<Indicateur, String>, JpaSpecificationExecutor<Indicateur> {
	boolean existsByCode(String code);
	Optional<Indicateur> findByCode(String code);
}
package com.atom.artaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.CampagneAgricol;

public interface CampagneAgricolRepository extends JpaRepository<CampagneAgricol, String>, JpaSpecificationExecutor<CampagneAgricol> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<CampagneAgricol> findByCodeOrLibelle(String code, String libelle);
	Optional<CampagneAgricol> findByLibelle(String nom);
	Optional<CampagneAgricol> findByCode(String code);
}
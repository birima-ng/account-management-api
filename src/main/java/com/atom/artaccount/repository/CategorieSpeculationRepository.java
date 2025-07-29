package com.atom.artaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.CategorieSpeculation;

public interface CategorieSpeculationRepository extends JpaRepository<CategorieSpeculation, String>, JpaSpecificationExecutor<CategorieSpeculation> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<CategorieSpeculation> findByCodeOrLibelle(String code, String libelle);
	Optional<CategorieSpeculation> findByLibelle(String nom);
	Optional<CategorieSpeculation> findByCode(String code);
}
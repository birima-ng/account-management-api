package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Speculation;

public interface SpeculationRepository extends JpaRepository<Speculation, String>, JpaSpecificationExecutor<Speculation> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<Speculation> findByCodeOrLibelle(String code, String libelle);
	Optional<Speculation> findByLibelle(String libelle);
	Optional<Speculation> findByCode(String code);
	List<Speculation> findByCategorieId(String typespeculationId);
}
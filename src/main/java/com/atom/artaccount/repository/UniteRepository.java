package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Unite;

public interface UniteRepository extends JpaRepository<Unite, String>, JpaSpecificationExecutor<Unite> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<Unite> findByCodeOrLibelle(String code, String libelle);
	Optional<Unite> findByLibelle(String libelle);
	Optional<Unite> findByCode(String code);
	List<Unite> findByTypeuniteId(String typeuniteId);
}
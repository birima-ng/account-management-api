package com.atom.artaccount.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Systeme;

public interface SystemeRepository extends JpaRepository<Systeme, String>, JpaSpecificationExecutor<Systeme> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<Systeme> findByCodeOrLibelle(String code, String libelle);
	Optional<Systeme> findByLibelle(String nom);
	Optional<Systeme> findByCode(String code);
	Page<Systeme> findById(String id, Pageable pageable);
	
}
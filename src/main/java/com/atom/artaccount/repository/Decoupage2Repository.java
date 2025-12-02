package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Decoupage2;

public interface Decoupage2Repository extends JpaRepository<Decoupage2, String>, JpaSpecificationExecutor<Decoupage2> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<Decoupage2> findByCodeOrLibelle(String code, String libelle);
	Optional<Decoupage2> findByLibelle(String libelle);
	Optional<Decoupage2> findByCode(String code);
	List<Decoupage2> findByDecoupage1Id(String decoupage1Id);
	List<Decoupage2> findByDecoupage1PaysId(String paysId);
	Page<Decoupage2> findByDecoupage1PaysId(String paysId, Pageable pageable);
}
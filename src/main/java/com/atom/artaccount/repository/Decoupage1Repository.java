package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Decoupage1;

public interface Decoupage1Repository extends JpaRepository<Decoupage1, String>, JpaSpecificationExecutor<Decoupage1> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<Decoupage1> findByCodeOrLibelle(String code, String libelle);
	Optional<Decoupage1> findByLibelle(String libelle);
	Optional<Decoupage1> findByCode(String code);
	List<Decoupage1> findByPaysId(String paysId);
	Page<Decoupage1> findByPaysId(String paysId, Pageable pageable);
}
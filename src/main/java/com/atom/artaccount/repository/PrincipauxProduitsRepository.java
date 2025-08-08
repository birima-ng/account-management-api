package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.PrincipauxProduits;

public interface PrincipauxProduitsRepository extends JpaRepository<PrincipauxProduits, String>, JpaSpecificationExecutor<PrincipauxProduits> {
	boolean existsBySpeculationIdAndSystemeIdAndPaysId(String speculationId, String systemeId, String paysId);
	Optional<PrincipauxProduits> findBySpeculationIdAndSystemeIdAndPaysId(String speculationId, String systemeId, String paysId);
	List<PrincipauxProduits> findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(String speculationId, String systemeId, String paysId, Boolean configured);
	Page<PrincipauxProduits> findBySystemeIdAndPaysIdAndConfigured(String systemeId, String paysId, Boolean configured, Pageable pageable);
	Page<PrincipauxProduits> findBySystemeIdAndPaysId(String systemeId, String paysId, Pageable pageable);
}
package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.PrincipauxProduitsFiche;

public interface PrincipauxProduitsFicheRepository extends JpaRepository<PrincipauxProduitsFiche, String>, JpaSpecificationExecutor<PrincipauxProduitsFiche> {
	boolean existsBySpeculationIdAndTypeficheIdAndPaysId(String speculationId, String typeficheId, String paysId);
	Optional<PrincipauxProduitsFiche> findBySpeculationIdAndTypeficheIdAndPaysId(String speculationId, String typeficheId, String paysId);
	List<PrincipauxProduitsFiche> findBySpeculationIdAndTypeficheIdAndPaysIdAndConfigured(String speculationId, String typeficheId, String paysId, Boolean configured);
	Page<PrincipauxProduitsFiche> findByTypeficheIdAndPaysIdAndConfigured(String typeficheId, String paysId, Boolean configured, Pageable pageable);
	Page<PrincipauxProduitsFiche> findByTypeficheIdAndPaysId(String typeficheId, String paysId, Pageable pageable);
}
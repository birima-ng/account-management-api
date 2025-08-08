package com.atom.artaccount.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.ConfigFicheCollecte;

public interface ConfigFicheCollecteRepository extends JpaRepository<ConfigFicheCollecte, String>, JpaSpecificationExecutor<ConfigFicheCollecte> {
	List<ConfigFicheCollecte> findByPaysIdAndSystemeIdAndTypeficheId(String paysId, String systemeId, String typefichecollecteId);
	Page<ConfigFicheCollecte> findByPaysIdAndSystemeIdAndTypeficheId(String paysId, String systemeId, String typefichecollecteId, Pageable pageable);
}
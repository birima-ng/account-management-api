package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.SpeculationSysteme;

public interface SpeculationSystemeRepository extends JpaRepository<SpeculationSysteme, String>, JpaSpecificationExecutor<SpeculationSysteme> {
	boolean existsBySpeculationIdAndSystemeIdAndPaysId(String speculationId, String systemeId, String paysId);
	Optional<SpeculationSysteme> findBySpeculationIdAndSystemeIdAndPaysId(String speculationId, String systemeId, String paysId);
	List<SpeculationSysteme> findBySpeculationIdAndSystemeIdAndPaysIdAndConfigured(String speculationId, String systemeId, String paysId, Boolean configured);

}
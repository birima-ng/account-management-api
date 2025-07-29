package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Action;

public interface ActionRepository extends JpaRepository<Action, String>, JpaSpecificationExecutor<Action> {
	boolean existsByNomOrCode(String nom, String code);
	boolean existsByCode(String code);
	Optional<Action> findByNomOrCode(String nom, String code);
	Optional<Action> findByNom(String nom);
	Optional<Action> findByCode(String code);
	List<Action> findByFeatureId(String moduleId);
	List<Action> findByFeatureModuleId(String moduleId);
}
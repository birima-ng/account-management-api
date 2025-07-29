package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.atom.artaccount.model.Feature;

public interface FeatureRepository extends JpaRepository<Feature, String>, JpaSpecificationExecutor<Feature> {
	boolean existsByNomOrCode(String nom, String code);
	Optional<Feature> findByNomOrCode(String nom, String code);
	Optional<Feature> findByNom(String nom);
	Optional<Feature> findByCode(String code);
	List<Feature> findByModuleId(String moduleId);
	@Query("SELECT f FROM feature f where (f.id IN (SELECT p.action.feature.id FROM profile_action p where p.allowed=true and p.action.feature.module.id =?1 and p.profile.id =?2) and f.codeparent is null) or (f.parent=true and f.module.id =?1)")
	List<Feature> findByModuleActive(String moduleId, String profilId);
	
	@Query("SELECT f FROM feature f where (f.id IN (SELECT p.action.feature.id FROM profile_action p where p.allowed=true and p.action.feature.module.id =?1 and p.profile.id =?2) and f.codeparent=?3)")
	List<Feature> findByModuleActiveCodeParent(String moduleId, String profilId, String codeparent);
}
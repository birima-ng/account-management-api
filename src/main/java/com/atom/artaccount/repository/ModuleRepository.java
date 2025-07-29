package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.atom.artaccount.model.Module;

public interface ModuleRepository extends JpaRepository<Module, String>, JpaSpecificationExecutor<Module> {
	boolean existsByNomOrCode(String nom, String code);
	Optional<Module> findByNomOrCode(String nom, String code);
	Optional<Module> findByNom(String nom);
	Optional<Module> findByCode(String code);
	@Query("select m from module m where m.id IN (SELECT p.action.feature.module.id FROM profile_action p where p.allowed=true and p.profile.id =?1)")
	List<Module> findActiveModuleProfil(String profilId);
	List<Module> findBySystemeId(String systemeId);
}
package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String>, JpaSpecificationExecutor<Profile> {
	boolean existsByNomOrCode(String nom, String code);
	Optional<Profile> findByNomOrCode(String nom, String code);
	Optional<Profile> findByNom(String nom);
	Optional<Profile> findByCode(String code);
	List<Profile> findByPaysIdAndSystemeId(String paysId, String systemeId);
}
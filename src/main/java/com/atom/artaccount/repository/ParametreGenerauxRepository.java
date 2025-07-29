package com.atom.artaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atom.artaccount.model.ParametreGeneraux;

@Repository
public interface ParametreGenerauxRepository extends JpaRepository <ParametreGeneraux, String>  {
	Optional<ParametreGeneraux> findByLibelleOrCode(String libelle, String code);
	Optional<ParametreGeneraux> findByLibelle(String libelle);
	Optional<ParametreGeneraux> findByCode(String code);
	boolean existsByLibelleOrCode(String libelle, String code);
	
}

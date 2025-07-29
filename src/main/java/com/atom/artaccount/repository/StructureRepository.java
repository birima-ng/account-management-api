package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Structure;

public interface StructureRepository extends JpaRepository<Structure, String>, JpaSpecificationExecutor<Structure> {
	boolean existsByCodeOrLibelle(String code, String libelle);
	boolean existsByCode(String code);
	boolean existsByLibelle(String libelle);
	Optional<Structure> findByCodeOrLibelle(String code, String libelle);
	Optional<Structure> findByLibelle(String libelle);
	Optional<Structure> findByCode(String code);
	List<Structure> findByPaysId(String paysId);
}
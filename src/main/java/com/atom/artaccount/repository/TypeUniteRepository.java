package com.atom.artaccount.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.TypeUnite;

public interface TypeUniteRepository extends JpaRepository<TypeUnite, String>, JpaSpecificationExecutor<TypeUnite> {
	boolean existsByLibelle(String code);
	Optional<TypeUnite> findByLibelle(String libelle);
}
package com.atom.artaccount.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.TypeFicheCollecte;

public interface TypeFicheCollecteRepository extends JpaRepository<TypeFicheCollecte, String>, JpaSpecificationExecutor<TypeFicheCollecte> {
	Page<TypeFicheCollecte> findBySystemeId(String systemeId, Pageable pageable);
	List<TypeFicheCollecte> findBySystemeId(String systemeId);
}
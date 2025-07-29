package com.atom.artaccount.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atom.artaccount.model.Organisationnelle;

@Repository
public interface OrganisationnelleRepository extends JpaRepository <Organisationnelle, String>  {
	List<Organisationnelle> findAll();
	Optional<Organisationnelle> findByName(String name);
	boolean existsByName(String name);
}

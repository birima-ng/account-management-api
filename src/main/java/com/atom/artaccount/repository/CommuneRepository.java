package com.atom.artaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atom.artaccount.model.Commune;

@Repository
public interface CommuneRepository extends JpaRepository <Commune, String>  {
	
	List<Commune> findByDepartementId(String departementId);
}

package com.atom.artaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atom.artaccount.model.Departement;

@Repository
public interface DepartementRepository extends JpaRepository <Departement, String>  {
	List<Departement> findByRegionId(String regionId);
	/*
	@Query("SELECT d FROM departement d JOIN FETCH d.region r where d.id=?1")
	Departement findById(String id);
	
	@Query("SELECT d FROM departement d JOIN FETCH d.region r")
	List<Departement> findAllJoin();

	
	@Query("SELECT d FROM departement d JOIN FETCH d.region r where d.nom=?1")
	Departement findByNom(String id);*/
	
	
	
	@Query("SELECT d FROM departement d where d.id=?1")
	List<Departement> findByRegionDepartement(String idDepartement);
}

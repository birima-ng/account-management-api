package com.atom.artaccount.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atom.artaccount.model.MessageBienvenue;

public interface MessageBienvenueRepository extends JpaRepository<MessageBienvenue, String>, JpaSpecificationExecutor<MessageBienvenue> {
	//boolean existsByLibelle(int libelle);
	//Optional<Annee> findByLibelle(int libelle);
	@Query("SELECT a FROM MessageBienvenue a WHERE a.telephone=:telephone AND a.datesave BETWEEN :start AND :end")
	List<MessageBienvenue> findToday(
	        @Param("start") Date start,
	        @Param("end") Date end,  @Param("telephone") String telephone
	);
}
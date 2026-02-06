package com.atom.artaccount.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Reclamation;

public interface ReclamationRepository extends JpaRepository<Reclamation, String>, JpaSpecificationExecutor<Reclamation> {

}
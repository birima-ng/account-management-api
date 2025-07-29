package com.atom.artaccount.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atom.artaccount.model.Pays;

@Repository
public interface PaysRepository extends JpaRepository <Pays, String>  {
}

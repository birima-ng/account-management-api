package com.atom.artaccount.dto;

import java.util.List;

import com.atom.artaccount.model.Departement;

public class RegionDTO {
    private String id;
    private String nom;
    private List<Departement> departements;
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}

    
}

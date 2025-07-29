package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="commune")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commune extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Commune() {
		
	}
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="code")
	private String code;
	
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departement", nullable = false)
    private Departement departement;
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@JsonCreator
	public Commune(String nom, String code, Departement departement) {
		super();
		this.nom = nom;
		this.code = code;
		this.departement = departement;
	}
    
}

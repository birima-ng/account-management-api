package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="departement")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departement extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Departement() {
		
	}
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="code")
	private String code;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region", nullable = false)
    private Region region;
	
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


	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@JsonCreator
	public Departement(String nom, String code, Region region) {
		super();
		this.nom = nom;
		this.code = code;
		this.region = region;
	}
    
}

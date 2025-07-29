package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="structure")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Structure extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Structure() {
		
	}
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="code")
	private String code;
	
	@Column(name="libelle")
	private String libelle;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = true)
    private Pays pays;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@JsonCreator
	public Structure(String adresse, String code, String libelle, Pays pays) {
		super();
		this.adresse = adresse;
		this.code = code;
		this.libelle = libelle;
		this.pays = pays;
	}
    
}

package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="parametre_generaux")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParametreGeneraux extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public ParametreGeneraux() {
		
	}
	
	@Column(name="code")
	private String code;
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="valeur")
	private String valeur;

	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@JsonCreator
	public ParametreGeneraux(String libelle) {
		super();
		this.libelle = libelle;
	}


	
    
}

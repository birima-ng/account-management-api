package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="categorie_speculation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategorieSpeculation extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public CategorieSpeculation() {
		
	}
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="libelle_en")
	private String libelleen;
	
	@Column(name="description")
	private String description;
	
	@Column(name="description_en")
	private String descriptionen;
	
	@Column(name="code")
	private String code;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelleen() {
		return libelleen;
	}

	public void setLibelleen(String libelleen) {
		this.libelleen = libelleen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionen() {
		return descriptionen;
	}

	public void setDescriptionen(String descriptionen) {
		this.descriptionen = descriptionen;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
    
    
}

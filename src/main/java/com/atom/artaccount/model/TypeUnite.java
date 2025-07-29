package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="type_unite")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TypeUnite extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="libelle")
    private String libelle;
	
	@Column(name="libelleen")
    private String libelleen;

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
    
}

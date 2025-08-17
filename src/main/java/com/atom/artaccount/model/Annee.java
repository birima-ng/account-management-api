package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="annee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Annee extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="libelle")
    private int libelle;

	public int getLibelle() {
		return libelle;
	}

	public void setLibelle(int libelle) {
		this.libelle = libelle;
	}
	
    
}

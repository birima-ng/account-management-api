package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="decoupage1")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Decoupage1 extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Decoupage1() {
		
	}
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="code")
	private String code;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = false)
    private Pays pays;

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

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}
	
	
    
}

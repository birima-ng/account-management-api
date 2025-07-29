package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="decoupage2")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Decoupage2 extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Decoupage2() {
		
	}
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="code")
	private String code;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "decoupage1", nullable = false)
    private Decoupage1 decoupage1;

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

	public Decoupage1 getDecoupage1() {
		return decoupage1;
	}

	public void setDecoupage1(Decoupage1 decoupage1) {
		this.decoupage1 = decoupage1;
	}

    
}

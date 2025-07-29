package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity(name="unite")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unite extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="code")
    private String code;
	
	@Column(name="libelle")
    private String libelle;
	
	@Column(name="libelleen")
    private String libelleen;
	
	@Lob
	@Column(name="description", columnDefinition = "LONGTEXT")
    private String description;
	
	@Lob
	@Column(name="descriptionen", columnDefinition = "LONGTEXT")
    private String descriptionen;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeunite", nullable = false)
    private TypeUnite typeunite;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


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


	public TypeUnite getTypeunite() {
		return typeunite;
	}


	public void setTypeunite(TypeUnite typeunite) {
		this.typeunite = typeunite;
	}
	
	
	
    
}

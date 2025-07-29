package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="action")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Action extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="nom")
    private String nom;
	
	@Column(name="nom_en")
    private String nomen;
	
	@Column(name="nom_es")
    private String nomes;
	
	@Column(name="code")
    private String code;
    
    @Column(name="indice")
    private Integer indice;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature", nullable = false)
    private Feature feature;
    

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

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getNomen() {
		return nomen;
	}

	public void setNomen(String nomen) {
		this.nomen = nomen;
	}

	public String getNomes() {
		return nomes;
	}

	public void setNomes(String nomes) {
		this.nomes = nomes;
	}

}

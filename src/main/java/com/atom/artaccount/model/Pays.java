package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity(name="pays")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pays extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Pays() {
		
	}
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="code")
	private String code;
	
	@Lob
	@Column(name="description", columnDefinition = "LONGTEXT")
	private String description;
	
	@Lob
	@Column(name="description_en", columnDefinition = "LONGTEXT")
	private String description_en;
	
	@Column(name="langue")
	private String langue;
	
	@Column(name="monnaie")
	private String monnaie;
	
	@Column(name="linksectoriel")
	private String linksectoriel;
    
	@Column(name="drapeau")
	private String drapeau;
    
	@Column(name="indicatif")
	private String indicatif;
	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getMonnaie() {
		return monnaie;
	}

	public void setMonnaie(String monnaie) {
		this.monnaie = monnaie;
	}

	public String getLinksectoriel() {
		return linksectoriel;
	}

	public void setLinksectoriel(String linksectoriel) {
		this.linksectoriel = linksectoriel;
	}

	public String getDrapeau() {
		return drapeau;
	}

	public void setDrapeau(String drapeau) {
		this.drapeau = drapeau;
	}

	public String getIndicatif() {
		return indicatif;
	}

	public void setIndicatif(String indicatif) {
		this.indicatif = indicatif;
	}

	@JsonCreator
	public Pays(String libelle, String code, String description, String description_en, String langue, String monnaie,
			String linksectoriel) {
		super();
		this.libelle = libelle;
		this.code = code;
		this.description = description;
		this.description_en = description_en;
		this.langue = langue;
		this.monnaie = monnaie;
		this.linksectoriel = linksectoriel;
	}
    
}

package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity(name="type_fiche_collecte")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TypeFicheCollecte extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;
	
	@Column(name="code")
    private String code;
	
	@Column(name="libelle")
    private String libelle;
	
	@Column(name="libelleen")
    private String libelleen;
	
	@Column(name="icon")
    private String icon;
	
	@Lob
	@Column(name="requete", columnDefinition = "LONGTEXT")
    private String requete;
	
	@Column(name="typerequete")
    private String typerequete;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = false)
    private Systeme systeme;

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

	public String getRequete() {
		return requete;
	}

	public void setRequete(String requete) {
		this.requete = requete;
	}

	public String getTyperequete() {
		return typerequete;
	}

	public void setTyperequete(String typerequete) {
		this.typerequete = typerequete;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
    
}

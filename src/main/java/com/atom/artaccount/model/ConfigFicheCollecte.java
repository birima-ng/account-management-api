package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="config_fiche_collecte")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConfigFicheCollecte extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speculation", nullable = false)
    private Speculation speculation;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typefiche", nullable = false)
    private TypeFicheCollecte typefiche;
	
	@Column(name="configured")
    private Boolean configured;
	
	@Column(name="afficher")
    private Boolean afficher;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = false)
    private Systeme systeme;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = false)
    private Pays pays;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campagne", nullable = false)
    private CampagneAgricol campagne;

	public Speculation getSpeculation() {
		return speculation;
	}

	public void setSpeculation(Speculation speculation) {
		this.speculation = speculation;
	}

	public TypeFicheCollecte getTypefiche() {
		return typefiche;
	}

	public void setTypefiche(TypeFicheCollecte typefiche) {
		this.typefiche = typefiche;
	}

	public Boolean getConfigured() {
		return configured;
	}

	public void setConfigured(Boolean configured) {
		this.configured = configured;
	}

	public Boolean getAfficher() {
		return afficher;
	}

	public void setAfficher(Boolean afficher) {
		this.afficher = afficher;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public CampagneAgricol getCampagne() {
		return campagne;
	}

	public void setCampagne(CampagneAgricol campagne) {
		this.campagne = campagne;
	}
    
	
}

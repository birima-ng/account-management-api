package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="principaux_produits_fiche")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrincipauxProduitsFiche extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speculation", nullable = false)
    private Speculation speculation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typefiche", nullable = false)
    private TypeFicheCollecte typefiche;
	
	@Column(name="configured")
    private Boolean configured;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = false)
    private Pays pays;

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

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

}

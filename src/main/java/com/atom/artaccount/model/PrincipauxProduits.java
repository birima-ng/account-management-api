package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="principaux_produits")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrincipauxProduits extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public PrincipauxProduits() {
		
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = false)
    private Systeme systeme;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = false)
    private Pays pays;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speculation", nullable = false)
    private Speculation speculation;
    
    @Column(name="configured")
    private Boolean configured;

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

	public Speculation getSpeculation() {
		return speculation;
	}

	public void setSpeculation(Speculation speculation) {
		this.speculation = speculation;
	}

	public Boolean getConfigured() {
		return configured;
	}

	public void setConfigured(Boolean configured) {
		this.configured = configured;
	}
    
}

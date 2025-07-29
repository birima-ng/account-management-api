package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="parametrage_decoupage")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParametrageDecoupage extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = false)
    private Pays pays;
	
	@Column(name="niveau1libelle")
    private String niveau1libelle;
    
    @Column(name="niveau1libelleen")
    private String niveau1libelleen;
    
    @Column(name="niveau2libelle")
    private String niveau2libelle;
    
    @Column(name="niveau2libelleen")
    private String niveau2libelleen;

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public String getNiveau1libelle() {
		return niveau1libelle;
	}

	public void setNiveau1libelle(String niveau1libelle) {
		this.niveau1libelle = niveau1libelle;
	}

	public String getNiveau1libelleen() {
		return niveau1libelleen;
	}

	public void setNiveau1libelleen(String niveau1libelleen) {
		this.niveau1libelleen = niveau1libelleen;
	}

	public String getNiveau2libelle() {
		return niveau2libelle;
	}

	public void setNiveau2libelle(String niveau2libelle) {
		this.niveau2libelle = niveau2libelle;
	}

	public String getNiveau2libelleen() {
		return niveau2libelleen;
	}

	public void setNiveau2libelleen(String niveau2libelleen) {
		this.niveau2libelleen = niveau2libelleen;
	}
    
}

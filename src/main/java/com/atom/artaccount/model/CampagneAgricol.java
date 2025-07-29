package com.atom.artaccount.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;

@jakarta.persistence.Entity(name="campagne_agricol")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CampagneAgricol extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;


	@Column(name="code")
    private String code;
	
	@Column(name="libelle")
    private String libelle;
	
	@Column(name="libelleen")
    private String libelleen;
	
	@Column(name="observation")
    private String observation;
	
	@Column(name="date_debut")
    private Date dateDebut;
	
	@Column(name="date_fin")
    private Date dateFin;

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

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
    

}

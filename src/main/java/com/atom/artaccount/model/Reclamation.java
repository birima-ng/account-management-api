package com.atom.artaccount.model;

import java.util.Date;

import com.atom.artaccount.dto.ReclamationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="reclamation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reclamation extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;
    
    @Column(name="matricule")
	private String matricule;
    
    @Column(name="cni")
	private String cni;
    
    @Column(name="prenom")
	private String prenom;
    
    @Column(name="nom")
	private String nom;
    
    @Column(name="datenaissance")
	private Date datenaissance;
    
    @Column(name="email")
	private String email;
    
    @Column(name="telephone")
	private String telephone;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Reclamation(ReclamationDTO reclamation) {
		super();
		this.matricule = reclamation.getMatricule();
		this.cni = reclamation.getCni();
		this.prenom = reclamation.getPrenom();
		this.nom = reclamation.getNom();
		this.datenaissance = reclamation.getDatenaissance();
		this.email = reclamation.getEmail();
		this.telephone = reclamation.getTelephone();
	}
	 
	
}

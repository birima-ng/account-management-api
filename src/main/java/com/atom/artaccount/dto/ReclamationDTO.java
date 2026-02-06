package com.atom.artaccount.dto;

import java.util.Date;

public class ReclamationDTO {
	
	  private String matricule;
	  private String cni;
	  private String prenom;
	  private String nom;
	  private Date datenaissance;
	  private String email;
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
	  
}

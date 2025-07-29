package com.atom.artaccount.dto;

public class SearchDTO {
	
	private String departement;
	private String secteur;
	private String stadecommerce;
	private String telephonefix;
	private String nom;
	
	public String getDepartement() {
		return departement;
	}
	
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	public String getSecteur() {
		return secteur;
	}
	
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	
	public String getStadecommerce() {
		return stadecommerce;
	}
	
	public void setStadecommerce(String stadecommerce) {
		this.stadecommerce = stadecommerce;
	}

	public String getTelephonefix() {
		return telephonefix;
	}

	public void setTelephonefix(String telephonefix) {
		this.telephonefix = telephonefix;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}

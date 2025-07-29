package com.atom.artaccount.dto;

import java.util.Date;

public class CollecteDTO {
	
	
	private String entreprise;
	private String idtypeproduit;
	private Date datecollecte;
	
	public String getEntreprise() {
		return entreprise;
	}
	
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
	
	public String getIdtypeproduit() {
		return idtypeproduit;
	}
	
	public void setIdtypeproduit(String idtypeproduit) {
		this.idtypeproduit = idtypeproduit;
	}
	
	public Date getDatecollecte() {
		return datecollecte;
	}
	
	public void setDatecollecte(Date datecollecte) {
		this.datecollecte = datecollecte;
	}
	 
}

package com.atom.artaccount.dto;

public class CumulDTO {
	
	
	private String produit;
	private String typeproduit;
	private Double quantitestock;
	private Double prixmaximum;
	private Double prixminimum;
	private Double prixdominant;
	
	public String getProduit() {
		return produit;
	}
	
	public void setProduit(String produit) {
		this.produit = produit;
	}
	
	public String getTypeproduit() {
		return typeproduit;
	}

	public void setTypeproduit(String typeproduit) {
		this.typeproduit = typeproduit;
	}

	public Double getQuantitestock() {
		return quantitestock;
	}
	
	public void setQuantitestock(Double quantitestock) {
		this.quantitestock = quantitestock;
	}
	
	public Double getPrixmaximum() {
		return prixmaximum;
	}
	
	public void setPrixmaximum(Double prixmaximum) {
		this.prixmaximum = prixmaximum;
	}
	
	public Double getPrixminimum() {
		return prixminimum;
	}
	
	public void setPrixminimum(Double prixminimum) {
		this.prixminimum = prixminimum;
	}
	
	public Double getPrixdominant() {
		return prixdominant;
	}
	
	public void setPrixdominant(Double prixdominant) {
		this.prixdominant = prixdominant;
	}
	
	
	 
}

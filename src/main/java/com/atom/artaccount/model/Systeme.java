package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="systeme")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Systeme extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="code")
    private String code;
	
	@Column(name="description")
    private String description;
	
	@Column(name="description_en")
    private String descriptionen;
	
	@Column(name="libelle")
    private String libelle;
	
	@Column(name="libelle_en")
    private String libelleen;
	
	@Column(name="link_sectoriel")
    private String link_sectoriel;
	
	@Column(name="logo")
    private String logo;
	
	@Column(name="url")
    private String url;
	
	@Column(name="pindex")
    private String pindex;
    
    
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescriptionen() {
		return descriptionen;
	}
	
	public void setDescriptionen(String descriptionen) {
		this.descriptionen = descriptionen;
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
	
	public String getLink_sectoriel() {
		return link_sectoriel;
	}
	
	public void setLink_sectoriel(String link_sectoriel) {
		this.link_sectoriel = link_sectoriel;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPindex() {
		return pindex;
	}
	
	public void setPindex(String pindex) {
		this.pindex = pindex;
	}
    
}

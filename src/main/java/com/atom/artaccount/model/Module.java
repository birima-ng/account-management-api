package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


@Entity(name="module")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Module extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	@Column(name="nom")
    private String nom;
	
	@Column(name="nom_en")
    private String nomen;
	
	@Column(name="nom_es")
    private String nomes;
    
    @Column(name="code")
    private String code;

    @Column(name="icon")
    private String icon;
    
    @Column(name="indice")
    private Integer indice;
    
	@Lob
	@Column(name="description", columnDefinition = "LONGTEXT")
	private String description;
	
	@Lob
	@Column(name="description_en", columnDefinition = "LONGTEXT")
	private String description_en;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = true)
    private Systeme systeme;
    
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getNomen() {
		return nomen;
	}

	public void setNomen(String nomen) {
		this.nomen = nomen;
	}

	public String getNomes() {
		return nomes;
	}

	public void setNomes(String nomes) {
		this.nomes = nomes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}
    
	
}
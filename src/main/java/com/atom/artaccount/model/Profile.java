package com.atom.artaccount.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity(name="profile")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Profile extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
   
    @Column(name="code")
	private String code;
   
    @Column(name="nom")
	private String nom;
    
    @Column(name="nom_en")
	private String nomen;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = true)
    private Pays pays;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = true)
    private Systeme systeme;
	
	@Lob
	@Column(name="description_en", columnDefinition = "LONGTEXT")
	private String description_en;
	
	@Lob
	@Column(name="description", columnDefinition = "LONGTEXT")
	private String description;

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

	public String getNomen() {
		return nomen;
	}

	public void setNomen(String nomen) {
		this.nomen = nomen;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	
}

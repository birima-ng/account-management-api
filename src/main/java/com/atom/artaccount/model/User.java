package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User  extends com.atom.artaccount.model.Entity{
 
	private static final long serialVersionUID = -8091879091924046844L;
	
    private String username;
    private String password;
    
    @Column(name="prenom")
    private String prenom;
    
    @Column(name="adresse")
    private String adresse;
    
    @Column(name="nom")
    private String nom;
    
    @Column(name="email")
    private String email;
    
    @Column(name="telephone")
    private String telephone;
    
    @Column(name="enabled")
    private boolean enabled = false;
    
    @Column(name="codeactivation")
    private String codeactivation;
    
    @Column(name="token")
    private String token;
    
    public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
    
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organisationnelle", nullable = true)
    private Organisationnelle organisationnelle;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile", nullable = true)
    private Profile profile;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structure", nullable = true)
    private Structure structure;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = true)
    private Systeme systeme;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = true)
    private Pays pays;
	

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodeactivation() {
		return codeactivation;
	}

	public void setCodeactivation(String codeactivation) {
		this.codeactivation = codeactivation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Organisationnelle getOrganisationnelle() {
		return organisationnelle;
	}

	public void setOrganisationnelle(Organisationnelle organisationnelle) {
		this.organisationnelle = organisationnelle;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}
    
	
}

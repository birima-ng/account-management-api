package com.atom.artaccount.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="valeur_indicateur")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ValeurIndicateur extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="datesaisie")
    private Date datesaisie;
	
	@Column(name="datevalidee")
    private Date datevalidee;
	
	@Column(name="valeur")
    private BigDecimal valeur;
	
	@Column(name="periode")
    private String periode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unite", nullable = true)
    private Unite unite;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campagneagricole", nullable = true)
    private CampagneAgricol campagneagricole;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indicateur", nullable = true)
    private Indicateur indicateur;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departement", nullable = true)
    private Decoupage2 departement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region", nullable = true)
    private Decoupage1 region;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pays", nullable = true)
    private Pays pays;
    
    @Column(name="statut")
    private Integer statut;
    
    @Column(name="niveau")
    private Integer niveau;
    
    @Column(name="generer")
    private Integer generer;
    
    @Column(name="codecomposite")
    private String codecomposite;
    
    @Column(name="decade")
    private int decade;
    
    @Column(name="mois")
    private int mois;

	public Date getDatesaisie() {
		return datesaisie;
	}

	public void setDatesaisie(Date datesaisie) {
		this.datesaisie = datesaisie;
	}

	public Date getDatevalidee() {
		return datevalidee;
	}

	public void setDatevalidee(Date datevalidee) {
		this.datevalidee = datevalidee;
	}

	public BigDecimal getValeur() {
		return valeur;
	}

	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public CampagneAgricol getCampagneagricole() {
		return campagneagricole;
	}

	public void setCampagneagricole(CampagneAgricol campagneagricole) {
		this.campagneagricole = campagneagricole;
	}

	public Indicateur getIndicateur() {
		return indicateur;
	}

	public void setIndicateur(Indicateur indicateur) {
		this.indicateur = indicateur;
	}

	public Decoupage2 getDepartement() {
		return departement;
	}

	public void setDepartement(Decoupage2 departement) {
		this.departement = departement;
	}

	public Decoupage1 getRegion() {
		return region;
	}

	public void setRegion(Decoupage1 region) {
		this.region = region;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Integer getStatut() {
		return statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}

	public Integer getGenerer() {
		return generer;
	}

	public void setGenerer(Integer generer) {
		this.generer = generer;
	}

	public String getCodecomposite() {
		return codecomposite;
	}

	public void setCodecomposite(String codecomposite) {
		this.codecomposite = codecomposite;
	}

	public int getDecade() {
		return decade;
	}

	public void setDecade(int decade) {
		this.decade = decade;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}
	
    
}

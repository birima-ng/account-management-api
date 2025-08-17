package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="indicateur")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Indicateur extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="categorie")
    private String categorie;
	
	@Column(name="code")
    private String code;
	
	@Column(name="libelleen")
    private String libelleen;
	
	@Column(name="libellefr")
    private String libellefr;
	
	@Column(name="variable")
    private String variable;
	
	@Column(name="formule")
    private String formule;
	
	@Column(name="responsablecollecte")
    private String responsablecollecte;
	
	@Column(name="methodecollecte")
    private String methodecollecte;
	
	@Column(name="niveau")
    private String niveau;
	
	@Column(name="codeformule")
    private String codeformule;
	
	@Column(name="codeformulenumerateur")
    private String codeformulenumerateur;
	
	@Column(name="codeformuledenominateur")
    private String codeformuledenominateur;
	
	@Column(name="ordre")
    private Integer ordre;
	
	@Column(name="type")
    private String type;
	
	@Column(name="ouinondept")
    private Boolean ouinondept;
	
	@Column(name="ouinonreg")
    private Boolean ouinonreg;
	
	@Column(name="ouinonpays")
    private Boolean ouinonpays;
	
	@Column(name="ouinonecowas")
    private Boolean ouinonecowas;
	
	@Column(name="iscomposite")
    private Boolean iscomposite;
	
	@Column(name="iscra")
    private Boolean iscra;
	
	@Column(name="description")
    private String description;
	
	@Column(name="descriptionen")
    private String descriptionen;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = false)
    private Systeme systeme;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "frequencecollecte", nullable = false)
    private FrequenceCollecte frequencecollecte;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unite", nullable = false)
    private Unite unite;

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleen() {
		return libelleen;
	}

	public void setLibelleen(String libelleen) {
		this.libelleen = libelleen;
	}

	public String getLibellefr() {
		return libellefr;
	}

	public void setLibellefr(String libellefr) {
		this.libellefr = libellefr;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getFormule() {
		return formule;
	}

	public void setFormule(String formule) {
		this.formule = formule;
	}

	public String getResponsablecollecte() {
		return responsablecollecte;
	}

	public void setResponsablecollecte(String responsablecollecte) {
		this.responsablecollecte = responsablecollecte;
	}

	public String getMethodecollecte() {
		return methodecollecte;
	}

	public void setMethodecollecte(String methodecollecte) {
		this.methodecollecte = methodecollecte;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getCodeformule() {
		return codeformule;
	}

	public void setCodeformule(String codeformule) {
		this.codeformule = codeformule;
	}

	public String getCodeformulenumerateur() {
		return codeformulenumerateur;
	}

	public void setCodeformulenumerateur(String codeformulenumerateur) {
		this.codeformulenumerateur = codeformulenumerateur;
	}

	public String getCodeformuledenominateur() {
		return codeformuledenominateur;
	}

	public void setCodeformuledenominateur(String codeformuledenominateur) {
		this.codeformuledenominateur = codeformuledenominateur;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getOuinondept() {
		return ouinondept;
	}

	public void setOuinondept(Boolean ouinondept) {
		this.ouinondept = ouinondept;
	}

	public Boolean getOuinonreg() {
		return ouinonreg;
	}

	public void setOuinonreg(Boolean ouinonreg) {
		this.ouinonreg = ouinonreg;
	}

	public Boolean getOuinonpays() {
		return ouinonpays;
	}

	public void setOuinonpays(Boolean ouinonpays) {
		this.ouinonpays = ouinonpays;
	}

	public Boolean getOuinonecowas() {
		return ouinonecowas;
	}

	public void setOuinonecowas(Boolean ouinonecowas) {
		this.ouinonecowas = ouinonecowas;
	}

	public Boolean getIscomposite() {
		return iscomposite;
	}

	public void setIscomposite(Boolean iscomposite) {
		this.iscomposite = iscomposite;
	}

	public Boolean getIscra() {
		return iscra;
	}

	public void setIscra(Boolean iscra) {
		this.iscra = iscra;
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

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public FrequenceCollecte getFrequencecollecte() {
		return frequencecollecte;
	}

	public void setFrequencecollecte(FrequenceCollecte frequencecollecte) {
		this.frequencecollecte = frequencecollecte;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}
    
}

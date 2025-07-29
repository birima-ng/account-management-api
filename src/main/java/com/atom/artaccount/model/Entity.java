package com.atom.artaccount.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.util.Date;
import java.util.UUID;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Entity implements java.io.Serializable {
	
    @Id
    @Column(name="id", nullable=false, length = 40)
	protected String id;

    @Column(name="datesave")
    private Date datesave;
    
    @Column(name="dateupdate")
    private Date dateupdate;
    
    @Column(name="deleted")
    private Boolean deleted;
    
    @Column(name="datedeleted")
    private Date datedeleted;
    
    @PrePersist
    public void prePersist() {
        if (id == null || id.equals("")) {
            id = UUID.randomUUID().toString();
        }
        
        setDatesave();
    }
    

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public Date getDatesave() {
		return datesave;
	}


	

	public void setDatesave() {
		this.datesave = new Date();
		this.dateupdate = new Date();
	}


	public Date getDateupdate() {
		return dateupdate;
	}



	public void setDateupdate() {
		this.dateupdate = new Date();
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted() {
		this.deleted = true;
	}


	public Date getDatedeleted() {
		return datedeleted;
	}


	public void setDatedeleted() {
		this.datedeleted = new Date();
	}
	


    @PreUpdate
    public void preUpdate() {
        System.out.println(">>> Avant mise à jour");
        setDateupdate();
    }

    @PreRemove
    public void preRemove() {
        System.out.println(">>> Avant suppression de l'utilisateur : ");
        this.setDeleted();
        setDatedeleted();
    }
	
	
}

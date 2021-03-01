package com.thomason.outdoors.models.camp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="campers")
public class Camp {
	// Variables
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=2, message="Camp name must be present and at least 2 characters")
    private String campName;
    
    @Size(min=2, message="Location must be present and at least 2 characters")
    private String lastName;
    
    @Size(min=2, message="Features must be present")
    private String features;
    
    //@Size(min=2, message="Price")
   // private String price;
   
    @Column(updatable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    
    // Create a One to Many - user can create many tasks
   // @OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
    //private List<Task> tasks;
    
 // Create a One to Many - user(assignee) can have many tasks
    //@OneToMany(mappedBy="assignee", fetch=FetchType.LAZY)
   // private List<Task> assigned_tasks;
    
    
    // Constructors
    public Camp() {
    }
    
    //Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

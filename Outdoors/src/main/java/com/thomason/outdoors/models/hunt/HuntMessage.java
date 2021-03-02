package com.thomason.outdoors.models.hunt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="hunt_messages")
public class HuntMessage {
	
	//Variables
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@NotNull
	@Size(message="Let's talk")
	private String comments;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	}
	
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
	//Relationships
	
	
	//Constructors
	
	public HuntMessage() {
		
	}
	
	//Getters and Setters

}

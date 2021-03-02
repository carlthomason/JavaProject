package com.thomason.outdoors.models.fish;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.thomason.outdoors.models.User;




@Entity
@Table(name="fish_messages")
public class FishMessage {

	
	

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fish_id")
	private Fish fish;
	
	
	
	//Constructors
	
	public FishMessage() {
	}
	
}

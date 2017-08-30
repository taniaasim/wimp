package com.libertymutual.goforcode.wimp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
public class Movie {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(length=300, nullable=false)
	private String title;
	
	
	private Date releaseDate;
	

	@ManyToMany
	private Set<Actor> actors;
	
	
	
	private Long budget;
	
	
	@Column(length=500, nullable=false)
	private String distributor;

	
	// need to have a no param constructor to work
	public Movie() {
		
	}
	
	public Movie(String title, String distributor) {
		this.title = title;
		this.distributor = distributor;
	}
		
	
	
	public void addActor(Actor actor) {
		if (actors == null) {
			actors = new HashSet<Actor>();
		}
		actors.add(actor);
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public Long getBudget() {
		return budget;
	}


	public void setBudget(Long budget) {
		this.budget = budget;
	}


	public String getDistributor() {
		return distributor;
	}


	public void setDistributer(String distributor) {
		this.distributor = distributor;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	
	
	
}

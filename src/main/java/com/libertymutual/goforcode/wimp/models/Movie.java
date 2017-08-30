package com.libertymutual.goforcode.wimp.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Movie {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(length=300, nullable=false)
	private String title;
	
	
	private Date releaseDate;
	
	
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
	
	
	
}

package com.libertymutual.goforcode.wimp.models;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
public class Actor {

	// FIELDS
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(length=75, nullable=false)
	private String firstName;
	

	@Column (length=75)
	private String lastName;
	
	//@JsonIgnore
	@ManyToMany(mappedBy="actors")
	private Set<Movie> movies;
	
	private Long activeSinceYear;
	

	private Date birthDate;

	
	@OneToMany
	private Set<Award> awards;



	public void addAward(Award award) {
		if (awards == null) {
			awards = new HashSet<Award>();
		}
		awards.add(award);
		
	}

	
	// GETTERS AND SETTERS START HERE
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Long getActiveSinceYear() {
		return activeSinceYear;
	}


	public void setActiveSinceYear(Long activeSinceYear) {
		this.activeSinceYear = activeSinceYear;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Set<Movie> getMovies() {
		return movies;
	}


	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}


	public Set<Award> getAwards() {
		return awards;
	}


	public void setAwards(Set<Award> awards) {
		this.awards = awards;
	}
	
}

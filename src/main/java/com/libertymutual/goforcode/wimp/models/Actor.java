package com.libertymutual.goforcode.wimp.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(length=75, nullable=false)
	private String firstName;
	

	@Column (length=75)
	private String lastName;
	
	
	private Long activeSinceYear;
	

	private Date birthDate;


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
	
}

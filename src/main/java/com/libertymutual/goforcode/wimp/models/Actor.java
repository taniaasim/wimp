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
	
}

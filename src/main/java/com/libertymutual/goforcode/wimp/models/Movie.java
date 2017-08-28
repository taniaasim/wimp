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
	
	
	@Column (length=500, nullable=false)
	private String distributer;
	
	
	
}

package com.libertymutual.goforcode.wimp.models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActorWithMovies extends Actor {

	@JsonProperty
	public Set<Movie> noReallyMovies() {
		return getMovies();
	}
	
	
	
	
}

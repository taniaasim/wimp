package com.libertymutual.goforcode.wimp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.libertymutual.goforcode.wimp.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}

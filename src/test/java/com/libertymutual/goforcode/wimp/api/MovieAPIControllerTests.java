package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;


public class MovieAPIControllerTests {
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	private MovieAPIController controller;
	
	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieAPIController(movieRepo, actorRepo);
	}
	
	@Test
	public void test_getAll_returns_all_Movies_returned_by_the_repo() {
		// arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		
		when(movieRepo.findAll()).thenReturn(movies);
	
		//act
		List<Movie> actual = controller.getAll();
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll(); //verifying this method even got called
	}
	
	@Test
	public void test_getOne_returns_one_Movie_returned_by_the_repo() throws StuffNotFoundException {
		Movie pinocchio = new Movie();
		when(movieRepo.findOne(4l)).thenReturn(pinocchio);
		
		Movie actual = controller.getOne(4l);
		
		assertThat(actual).isSameAs(pinocchio);
		verify(movieRepo).findOne(4l);
	}
	
	@Test
	public void test_getOne_throws_StuffNotFoundException_when_no_Movie_returned_from_repo() {
		try {
			controller.getOne(1);
			fail("The controller didn't throw the StuffNotFoundException");
			
		} catch (StuffNotFoundException snfe) {
			
		}
	}
	
	@Test
	public void test_delete_returns_Movie_deleted_when_found() {
		//arrange
		Movie bob = new Movie();
		when(movieRepo.findOne(3l)).thenReturn(bob);
		
		//act
		Movie actual = controller.delete(3l);
		
		//assert
		assertThat(bob).isSameAs(actual);
		verify(movieRepo).delete(3l);
		verify(movieRepo).findOne(3l);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_delete_throws_EmptyResultDataAccess() throws StuffNotFoundException {
		//arrange
		when(movieRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Movie actual = controller.delete(8l);
		
		//assert
		assertThat(actual).isNull();
		verify(movieRepo).findOne(8l);
	}
	
	@Test
	public void test_that_actor_gets_created_and_added_to_repo() {
		//arange
		Movie bob = new Movie();
		when(movieRepo.save(bob)).thenReturn(bob);
		
		//act
		Movie actual = controller.create(bob);
		
		//assert
		assertThat(actual).isSameAs(bob);
		verify(movieRepo).save(bob);
		
	}
	
	@Test
	public void test_that_actor_gets_updated_with_ID_and_added_to_repo() {
		//arange
		Movie bob = new Movie();
		bob.setId(99l);
		when(movieRepo.save(bob)).thenReturn(bob);	
		
		Movie actual = controller.create(bob);
		actual.setId(99l);
		
		//act
		actual = controller.update(bob, 100l);
		
		//assert
		assertThat(actual.getId()).isEqualTo(100);
	}
	
	//Testing associateAnActor()
    @Test
    public void test_associateAnActor_returns_associated_movies_for_given_actor() {
        //Arrange
        Movie movie = new Movie();
        when(movieRepo.findOne(4L)).thenReturn(movie);
        
        Actor actor = new Actor();
        actor.setId(5L);
        when(actorRepo.findOne(5L)).thenReturn(actor);
        
        //Act
        Movie actualMovie = controller.associateAnActor(4L, actor);        
        
        //Assert
        assertThat(movie.getActors()).isSameAs(actualMovie.getActors());
        verify(movieRepo).save(actualMovie);
        verify(actorRepo).findOne(5L);
        verify(movieRepo).findOne(4L);
    }
	
}

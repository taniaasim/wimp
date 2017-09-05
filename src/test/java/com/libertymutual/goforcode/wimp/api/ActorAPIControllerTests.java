package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class ActorAPIControllerTests {
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorAPIController controller;
	private AwardAPIController awardController;
	private MovieRepository movieRepo;
	
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		movieRepo = mock(MovieRepository.class);
		awardRepo = mock(AwardRepository.class);
		controller = new ActorAPIController(actorRepo, awardRepo);
	}
	
	@Test
	public void test_getAll_returns_all_Actors_returned_by_the_repo() {
		// arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		
		when(actorRepo.findAll()).thenReturn(actors);
	
		//act
		List<Actor> actual = controller.getAll();
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll(); //verifying this method even got called
	}
	
	@Test
	public void test_getOne_returns_one_Actor_returned_by_the_repo() throws StuffNotFoundException {
		Actor pinocchio = new Actor();
		when(actorRepo.findOne(4l)).thenReturn(pinocchio);
		
		Actor actual = controller.getOne(4l);
		
		assertThat(actual).isSameAs(pinocchio);
		verify(actorRepo).findOne(4l);
	}
	
	@Test
	public void test_getOne_throws_StuffNotFoundException_when_no_Actor_returned_from_repo() {
		try {
			controller.getOne(1);
			fail("The controller didn't throw the StuffNotFoundException");
			
		} catch (StuffNotFoundException snfe) {
			
		}
	}
	
	@Test
	public void test_delete_returns_Actor_deleted_when_found() {
		//arrange
		Actor bob = new Actor();
		when(actorRepo.findOne(3l)).thenReturn(bob);
		
		//act
		Actor actual = controller.delete(3l);
		
		//assert
		assertThat(bob).isSameAs(actual);
		verify(actorRepo).delete(3l);
		verify(actorRepo).findOne(3l);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_delete_throws_EmptyResultDataAccess() throws StuffNotFoundException {
		//arrange
		when(actorRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Actor actual = controller.delete(8l);
		
		//assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(8l);
	}
	
	@Test
	public void test_that_actor_gets_created_and_added_to_repo() {
		//arange
		Actor bob = new Actor();
		when(actorRepo.save(bob)).thenReturn(bob);
		
		//act
		Actor actual = controller.create(bob);
		
		//assert
		assertThat(actual).isSameAs(bob);
		verify(actorRepo).save(bob);
		
	}
	
	@Test
	public void test_that_actor_gets_updated_with_ID_and_added_to_repo() {
		//arange
		Actor bob = new Actor();
		bob.setId(99l);
		when(actorRepo.save(bob)).thenReturn(bob);	
		
		Actor actual = controller.create(bob);
		actual.setId(99l);
		
		//act
		actual = controller.update(bob, 100l);
		
		//assert
		assertThat(actual.getId()).isEqualTo(100);
	}
	
	//Testing associateAnActor()
    @Test
    public void test_associateAnActor_returns_associated_awards_for_given_actor() {
        //Arrange
        Actor actor = new Actor();
        actor.setId(5L);
        when(actorRepo.findOne(5L)).thenReturn(actor);
        
        Award award = new Award();
        award.setId(4L);
        when(awardRepo.findOne(4L)).thenReturn(award);
        
        //Act
        Actor actualActor = controller.associateAnActor(5L, award);      
        
        //Assert
        assertThat(actualActor.getAwards()).contains(award);
        verify(actorRepo).save(actualActor);
        verify(actorRepo).findOne(5L);
    }
}

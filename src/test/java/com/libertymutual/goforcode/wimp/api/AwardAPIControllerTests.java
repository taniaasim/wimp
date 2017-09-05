package com.libertymutual.goforcode.wimp.api;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class AwardAPIControllerTests {

	private AwardRepository awardRepo;
	private AwardAPIController controller;
	
	@Before
	public void setUp() {
		awardRepo = mock(AwardRepository.class);
		controller = new AwardAPIController(awardRepo);
	}
	
	@Test
	public void test_getAll_returns_all_Awards_returned_by_the_repo() {
		// arrange
		ArrayList<Award> awards = new ArrayList<Award>();
		awards.add(new Award());
		awards.add(new Award());
		
		when(awardRepo.findAll()).thenReturn(awards);
	
		//act
		List<Award> actual = controller.getAll();
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(awards.get(0));
		verify(awardRepo).findAll(); //verifying this method even got called
	}
	
	@Test
	public void test_getOne_returns_one_Award_returned_by_the_repo() throws StuffNotFoundException {
		Award pinocchio = new Award();
		when(awardRepo.findOne(4l)).thenReturn(pinocchio);
		
		Award actual = controller.getOne(4l);
		
		assertThat(actual).isSameAs(pinocchio);
		verify(awardRepo).findOne(4l);
	}
	
	@Test
	public void test_getOne_throws_StuffNotFoundException_when_no_Award_returned_from_repo() {
		try {
			controller.getOne(1);
			fail("The controller didn't throw the StuffNotFoundException");
			
		} catch (StuffNotFoundException snfe) {
			
		}
	}
	
	@Test
	public void test_delete_returns_Award_deleted_when_found() {
		//arrange
		Award bob = new Award();
		when(awardRepo.findOne(3l)).thenReturn(bob);
		
		//act
		Award actual = controller.delete(3l);
		
		//assert
		assertThat(bob).isSameAs(actual);
		verify(awardRepo).delete(3l);
		verify(awardRepo).findOne(3l);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_delete_throws_EmptyResultDataAccess() throws StuffNotFoundException {
		//arrange
		when(awardRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Award actual = controller.delete(8l);
		
		//assert
		assertThat(actual).isNull();
		verify(awardRepo).findOne(8l);
	}
	
	@Test
	public void test_that_award_gets_created_and_added_to_repo() {
		//arange
		Award bob = new Award();
		when(awardRepo.save(bob)).thenReturn(bob);
		
		//act
		Award actual = controller.create(bob);
		
		//assert
		assertThat(actual).isSameAs(bob);
		verify(awardRepo).save(bob);
		
	}
	
	@Test
	public void test_that_award_gets_updated_with_ID_and_added_to_repo() {
		//arange
		Award bob = new Award();
		bob.setId(99l);
		when(awardRepo.save(bob)).thenReturn(bob);	
		
		Award actual = controller.create(bob);
		actual.setId(99l);
		
		//act
		actual = controller.update(bob, 100l);
		
		//assert
		assertThat(actual.getId()).isEqualTo(100);
	}
	
}


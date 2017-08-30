package com.libertymutual.goforcode.wimp.api;

import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;



@RestController
@RequestMapping("/api/actors")
public class ActorAPIController {
	
	private ActorRepository actorRepo;

	public ActorAPIController(ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
		
		Actor actor = new Actor();
		actor.setFirstName("Brad");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Jim");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Bruce");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Jennifer");
		actorRepo.save(actor);
	}
	
	
	@GetMapping("") 
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			throw new StuffNotFoundException();
		}
//		ActorWithMovies newActor = new ActorWithMovies();
//		newActor.setActiveSinceYear(actor.getActiveSinceYear());
//		newActor.setId(actor.getId());
//		newActor.setBirthDate(actor.getBirthDate());
//		newActor.setMovies(actor.getMovies());
//		newActor.setFirstName(actor.getFirstName());
//		newActor.setLastName(actor.getLastName());
		return actor;
	}
	
	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			actorRepo.delete(id);
			return actor;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	

	
	@PostMapping("") // requestbody will turn the json into that object
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
	
	
}

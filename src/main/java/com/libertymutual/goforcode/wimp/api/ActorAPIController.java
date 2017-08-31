package com.libertymutual.goforcode.wimp.api;

import java.util.*;

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
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorAPIController {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;

	public ActorAPIController(ActorRepository actorRepo, AwardRepository awardRepo) {
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
		
		Actor actor = new Actor();
		actor.setFirstName("Brad");
		actor.setLastName("Pitt");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Jim");
		actor.setLastName("Carey");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Bruce");
		actor.setLastName("Willis");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Jennifer");
		actor.setLastName("Lawrence");
		actorRepo.save(actor);
	}
	
	@GetMapping("") 
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	// Curtis, I know you hate commented code but I need to keep this commented code
	// here for my notes.
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
	
	@PostMapping("{actorId}/awards")
	public Actor associateAnActor(@PathVariable long actorId, @RequestBody Award award) {
		Actor actor = actorRepo.findOne(actorId); 
		award = awardRepo.findOne(award.getId()); 
		
		actor.addAward(award); 
		actorRepo.save(actor); 
		return actor;
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

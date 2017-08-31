package com.libertymutual.goforcode.wimp.api;

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

import com.libertymutual.goforcode.wimp.models.Award;

import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

@RestController
@RequestMapping("/api/awards")
public class AwardAPIController {

	private AwardRepository awardRepo;
	
	public AwardAPIController(AwardRepository awardRepo) {
		this.awardRepo = awardRepo;
		
		Award award = new Award();
		award.setTitle("Best Actor");
		award.setOrganization("The Academy");
		award.setYear(1995);
		awardRepo.save(award);
		
		award = new Award();
		award.setTitle("Best Actress");
		award.setOrganization("The Academy");
		award.setYear(1999);
		
		awardRepo.save(award);
	}
	
	@GetMapping("") 
	public List<Award> getAll() {
		return awardRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Award getOne(@PathVariable long id) throws StuffNotFoundException {
		Award award = awardRepo.findOne(id);
		if (award == null) {
			throw new StuffNotFoundException();
		}
//		ActorWithMovies newActor = new ActorWithMovies();
//		newActor.setActiveSinceYear(actor.getActiveSinceYear());
//		newActor.setId(actor.getId());
//		newActor.setBirthDate(actor.getBirthDate());
//		newActor.setMovies(actor.getMovies());
//		newActor.setFirstName(actor.getFirstName());
//		newActor.setLastName(actor.getLastName());
		return award;
	}
	
	@DeleteMapping("{id}")
	public Award delete(@PathVariable long id) {
		try {
			Award award = awardRepo.findOne(id);
			awardRepo.delete(id);
			return award;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	@PostMapping("") // requestbody will turn the json into that object
	public Award create(@RequestBody Award award) {
		return awardRepo.save(award);
	}
	
	@PutMapping("{id}")
	public Award update(@RequestBody Award award, @PathVariable long id) {
		award.setId(id);
		return awardRepo.save(award);
	}
	
}

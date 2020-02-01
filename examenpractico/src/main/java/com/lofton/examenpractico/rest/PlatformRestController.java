package com.lofton.examenpractico.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lofton.examenpractico.dao.PlatformRepository;
import com.lofton.examenpractico.entity.Platform;

@RestController
@RequestMapping("/api")
public class PlatformRestController {
	
	
	private PlatformRepository platformRepo;
	
	// quick and dirty : inject genre repository
	@Autowired
	public PlatformRestController( PlatformRepository thePlatformRepository ) {
		platformRepo = thePlatformRepository;
	}
	
	// expose "/platforms" and return list of platforms
	@GetMapping("/platforms")
	public List<Platform> findAll(){
		return platformRepo.findAll();
	}
	
	// add mapping for findById /platforms/{platformId}
	@GetMapping("/platforms/{platformId}")
	public Platform getPlatform(@PathVariable int platformId) {
		
		Optional<Platform> result = platformRepo.findById(platformId); 
		
		Platform thePlatform = null;
		
		if (result.isPresent()) {
			thePlatform = result.get();
		}else {
			throw new RuntimeException("Did not find platform id - " + platformId);
		}
		
		return thePlatform;
	}
	
	// add mapping for POST /genres - add new genre 
	@PostMapping("/platforms")
	public Platform addPlatform(@RequestBody Platform thePlatform) {
		
		thePlatform.setId(0);
		
		platformRepo.save(thePlatform);
		
		return thePlatform;
	}
	
	//add mapping for PUT /games - update existing game
	@PutMapping("/platforms")
	public Platform updatePlatform(@RequestBody Platform thePlatform) {
		
		platformRepo.save(thePlatform);
		
		return thePlatform;
	}
	
	// add mapping for DELETE /games/{gameId} - disable existing game
	@DeleteMapping("/platforms/{platformId}")
	public String deletePlatform(@PathVariable int platformId) {
		
		Optional<Platform> tempPlatform = platformRepo.findById(platformId);
		
		Platform thePlatform = null;
		
		if(tempPlatform.isPresent()) {
			
			thePlatform = tempPlatform.get();
		}else {
			throw new RuntimeException("platform id not found - " + platformId);
		}
		
		platformRepo.delete(thePlatform);
		
		return "Platform deleted id - " + platformId;
	}



}

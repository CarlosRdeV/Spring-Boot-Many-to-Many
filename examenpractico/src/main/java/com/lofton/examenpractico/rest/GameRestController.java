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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lofton.examenpractico.dao.GameRepository;
import com.lofton.examenpractico.entity.Game;

@RestController
@RequestMapping("/api")
public class GameRestController {

	private GameRepository gameRepo;
	
	// quick and dirty : inject game repository
	@Autowired
	public GameRestController ( GameRepository theGameRepository ) {
		gameRepo = theGameRepository;
	}
	
	// expose "/games" and return list of games
	@GetMapping("/games")
	public List<Game> findAll(){
		return gameRepo.findAll();
	}
	
	// add mapping for findById /games/{gameId}
	@GetMapping("/games/{gameId}")
	public Game getGame(@PathVariable int gameId) {
		
		Optional<Game> result = gameRepo.findById(gameId); 
		
		Game theGame = null;
		
		if (result.isPresent()) {
			theGame = result.get();
		}else {
			throw new RuntimeException("Did not find game id - " + gameId);
		}
		
		return theGame;
	}
	
	// add mapping for POST /games - add new game 
	@PostMapping("/games")
	public Game addGame(@RequestBody Game theGame) {
		
		theGame.setId(0);
		theGame.setEnable(true);
		
		gameRepo.save(theGame);
		
		return theGame;
	}
	
	//add mapping for PUT /games - update existing game
	@PutMapping("/games")
	public Game updateGame(@RequestBody Game theGame) {
		
		theGame.setEnable(true);
		
		gameRepo.save(theGame);
		
		return theGame;
	}
	
	// add mapping for DELETE /games/{gameId} - disable existing game
	@DeleteMapping("/games/{gameId}")
	public Game disableGame(@PathVariable int gameId) {
		
		Optional<Game> tempGame = gameRepo.findById(gameId);
		
		Game theGame = null;
		
		if(tempGame.isPresent()) {
			
			theGame = tempGame.get();
		}else {
			throw new RuntimeException("Game id not found - " + gameId);
		}
		
		theGame.setEnable(false);

		gameRepo.save(theGame);
		
		return theGame;
	}
}

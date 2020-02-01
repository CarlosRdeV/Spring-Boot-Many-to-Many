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

import com.lofton.examenpractico.dao.GameRepository;
import com.lofton.examenpractico.dao.GenreRepository;
import com.lofton.examenpractico.dao.PlatformRepository;
import com.lofton.examenpractico.entity.Game;
import com.lofton.examenpractico.entity.Genre;
import com.lofton.examenpractico.entity.Platform;

@RestController
@RequestMapping("/api")
public class GameRestController {

	private GameRepository gameRepo;
	
	private GenreRepository genreRepo;
	
	private PlatformRepository platformRepo;
	// quick and dirty : inject game repository
	@Autowired
	public GameRestController ( GameRepository theGameRepository, 
			GenreRepository theGenreRepository, PlatformRepository ThePlatformRepo ) {
		
		gameRepo = theGameRepository;
		
		genreRepo = theGenreRepository;
		
		platformRepo = ThePlatformRepo;
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
	
	// add mapping for PUT /games/{gameId}/{genreId} - add existing genre to a existing game
	@PutMapping("/games/genres/{gameId}/{genreId}")
	public Game addGenreGame(@PathVariable int gameId, @PathVariable int genreId) {
		
		Optional<Game> tempGame = gameRepo.findById(gameId);
		
		Game theGame = null;
		
		Optional<Genre> tempGenre = genreRepo.findById(genreId);
		
		Genre theGenre = null;
		
		if(tempGame.isPresent()) {
			
				if(tempGenre.isPresent()) {
					theGame = tempGame.get();
					theGenre= tempGenre.get();
					
				}else {
					throw new RuntimeException("Genre id not found - " + genreId);
				}
		
		}else {
			throw new RuntimeException("Game id not found - " + gameId);
		}
		
		theGame.addGenre(theGenre);
		
		gameRepo.save(theGame);
		
		return theGame;
	}
	
	// add mapping for PUT /games/{gameId}/{genreId} - add existing genre to a existing game
	@PutMapping("/games/platforms/{gameId}/{platformId}")
	public Game addPlaformGame(@PathVariable int gameId, @PathVariable int platformId) {
		
		Optional<Game> tempGame = gameRepo.findById(gameId);
		
		Game theGame = null;
		
		Optional<Platform> tempPlatform = platformRepo.findById(platformId);
		
		Platform thePlatform = null;
		
		if(tempGame.isPresent()) {
			
				if(tempPlatform.isPresent()) {
					theGame = tempGame.get();
					thePlatform= tempPlatform.get();
					
				}else {
					throw new RuntimeException("Platform id not found - " + platformId);
				}
		
		}else {
			throw new RuntimeException("Game id not found - " + gameId);
		}
		
		theGame.addPlatform(thePlatform);
		
		gameRepo.save(theGame);
		
		return theGame;
	}

	
}

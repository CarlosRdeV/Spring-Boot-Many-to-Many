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

import com.lofton.examenpractico.dao.GenreRepository;
import com.lofton.examenpractico.entity.Genre;

@RestController
@RequestMapping("/api")
public class GenreRestController {
	
	private GenreRepository genreRepo;
	
	// quick and dirty : inject genre repository
	@Autowired
	public GenreRestController( GenreRepository theGenreRepository ) {
		genreRepo = theGenreRepository;
	}
	
	// expose "/genres" and return list of games
	@GetMapping("/genres")
	public List<Genre> findAll(){
		return genreRepo.findAll();
	}
	
	// add mapping for findById /genres/{genreId}
	@GetMapping("/genres/{genreId}")
	public Genre getGenre(@PathVariable int genreId) {
		
		Optional<Genre> result = genreRepo.findById(genreId); 
		
		Genre theGenre = null;
		
		if (result.isPresent()) {
			theGenre = result.get();
		}else {
			throw new RuntimeException("Did not find genre id - " + genreId);
		}
		
		return theGenre;
	}
	
	// add mapping for POST /genres - add new genre 
	@PostMapping("/genres")
	public Genre addGenre(@RequestBody Genre theGenre) {
		
		theGenre.setId(0);
		
		genreRepo.save(theGenre);
		
		return theGenre;
	}
	
	//add mapping for PUT /games - update existing game
	@PutMapping("/genres")
	public Genre updateGenre(@RequestBody Genre theGenre) {
		
		genreRepo.save(theGenre);
		
		return theGenre;
	}
	
	// add mapping for DELETE /games/{gameId} - disable existing game
	@DeleteMapping("/genres/{genreId}")
	public String deleteGenre(@PathVariable int genreId) {
		
		Optional<Genre> tempGenre = genreRepo.findById(genreId);
		
		Genre theGenre = null;
		
		if(tempGenre.isPresent()) {
			
			theGenre = tempGenre.get();
		}else {
			throw new RuntimeException("Genre id not found - " + genreId);
		}
		
		genreRepo.delete(theGenre);
		
		return "Gender deleted id - " + genreId;
	}


}

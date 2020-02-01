package com.lofton.examenpractico.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "juego")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String name;

	@Column(name = "game_year")
	private int game_year;

	@Column(name = "esrb")
	private String esrb;

	@Column(name = "company")
	private String company;

	@Column(name = "enable")
	private Boolean enable;
	
	@ManyToMany( fetch = FetchType.LAZY ,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH} )
	@JoinTable(
			name = "juego_genero",
			joinColumns = @JoinColumn(name = "juego_id"),
			inverseJoinColumns = @JoinColumn(name = "genero_id")
			)
	private List<Genre> genres;
	

	@ManyToMany( fetch = FetchType.LAZY ,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH} )
	@JoinTable(
			name = "juego_plataforma",
			joinColumns = @JoinColumn(name = "juego_id"),
			inverseJoinColumns = @JoinColumn(name = "plataforma_id")
			)
	private List<Platform> platforms;

	
	public Game() {

	}
	
	public Game(String name, int game_year, String esrb, String company) {
		this.name = name;
		this.game_year = game_year;
		this.esrb = esrb;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGame_year() {
		return game_year;
	}

	public void setGame_year(int game_year) {
		this.game_year = game_year;
	}

	public String getEsrb() {
		return esrb;
	}

	public void setEsrb(String esrb) {
		this.esrb = esrb;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	// getter and setter for many to many colums
	
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}


	public List<Platform> getPlataforms() {
		return platforms;
	}

	public void setPlataforms(List<Platform> platforms) {
		this.platforms = platforms;
	}
	

	
	//add convenience methods
	
	public void addGenre(Genre theGenre) {
		
		if(genres == null) {
			genres = new ArrayList<>();	
		}
		
		genres.add(theGenre);
	}

	public void addPlatform(Platform thePlatform) {
		
		if(platforms == null) {
			platforms = new ArrayList<>();	
		}
		
		platforms.add(thePlatform);
	}

	
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", game_year=" + game_year + ", esrb=" + esrb + ", company="
				+ company + ", enable=" + enable + ", genres=" + genres + "]";
	}



}

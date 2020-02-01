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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plataforma")
public class Platform {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name="plataforma")
	private String plataforma;
	
	@JsonIgnore
	@ManyToMany( fetch = FetchType.LAZY ,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH} )
	@JoinTable(
			name = "juego_plataforma",
			joinColumns = @JoinColumn(name = "plataforma_id"),
			inverseJoinColumns = @JoinColumn(name = "juego_id")
			)
	private List<Game> games;
	
	public Platform() {
		
	}

	public Platform(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public void addGame(Game theGame) {
		
		if(games == null) {
			games = new ArrayList<>();	
		}
		
		games.add(theGame);
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", plataforma=" + plataforma + "]";
	}

}

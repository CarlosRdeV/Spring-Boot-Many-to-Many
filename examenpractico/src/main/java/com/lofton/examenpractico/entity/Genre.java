package com.lofton.examenpractico.entity;

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
@Table(name = "genero")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "genero")
	private String genero;

	@ManyToMany( fetch = FetchType.LAZY ,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH} )
	@JoinTable(
			name = "juego_genero",
			joinColumns = @JoinColumn(name = "genero_id"),
			inverseJoinColumns = @JoinColumn(name = "juego_id")
			)
	private List<Game> games;

	public Genre() {

	}

	public Genre(String genero) {
		this.genero = genero;
	}

	
	
	public Genre(String genero, List<Game> games) {
		this.genero = genero;
		this.games = games;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Genero{" + "id=" + id + ", genero=" + genero + '}';
	}

}

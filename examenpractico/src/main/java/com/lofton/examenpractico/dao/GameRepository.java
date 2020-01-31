package com.lofton.examenpractico.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lofton.examenpractico.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

}

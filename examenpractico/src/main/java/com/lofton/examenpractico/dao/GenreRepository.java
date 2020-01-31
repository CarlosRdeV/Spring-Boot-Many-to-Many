package com.lofton.examenpractico.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lofton.examenpractico.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}

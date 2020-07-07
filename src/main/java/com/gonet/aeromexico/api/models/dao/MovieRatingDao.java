package com.gonet.aeromexico.api.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonet.aeromexico.api.models.entity.MovieRating;
import com.gonet.aeromexico.api.models.entity.Usuario;

public interface MovieRatingDao extends JpaRepository<MovieRating, Long> {

	public List<MovieRating> findAllByUsuario(Usuario usuario);

	public MovieRating findByMovieIdAndUsuario(Long id, Usuario usuario);

}

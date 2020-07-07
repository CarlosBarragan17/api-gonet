package com.gonet.aeromexico.api.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gonet.aeromexico.api.models.dto.Movie;
import com.gonet.aeromexico.api.models.dto.MovieRatingJson;
import com.gonet.aeromexico.api.models.entity.MovieRating;

public interface MovieService {
	
	public Movie getMovie(String name);

	public List<MovieRatingJson> findAllByUser(HttpServletRequest request);

	public MovieRating saveMovieRating(HttpServletRequest request, MovieRating movieRating);

	public MovieRating updateMovieRating(HttpServletRequest request, Long id, MovieRating movieRating);

	public void deleteMovieRating(HttpServletRequest request, Long id);

	public MovieRatingJson findByIdAndUser(HttpServletRequest request, Long id);

}

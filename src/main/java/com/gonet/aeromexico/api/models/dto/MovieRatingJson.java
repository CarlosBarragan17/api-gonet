package com.gonet.aeromexico.api.models.dto;

import com.gonet.aeromexico.api.models.entity.MovieRating;

public class MovieRatingJson {
	
	private MovieRating movieRating;
	
	private Movie movie;

	public MovieRatingJson() {
	}

	public MovieRatingJson(MovieRating movieRating, Movie movie) {
		this.movieRating = movieRating;
		this.movie = movie;
	}

	public MovieRating getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}

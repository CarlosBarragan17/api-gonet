package com.gonet.aeromexico.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gonet.aeromexico.api.models.dto.Movie;
import com.gonet.aeromexico.api.models.dto.MovieRatingJson;
import com.gonet.aeromexico.api.models.entity.MovieRating;
import com.gonet.aeromexico.api.services.MovieService;

@RestController
@RequestMapping(value = "/api")
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@GetMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMovie(String name) {
		Map<String, Object> response = new HashMap<>();
		Movie movie = movieService.getMovie(name);
		if (movie == null) {
			response.put("mensaje", "No existe pelicula con tal nombre.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/my-movies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMyMovies(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		List<MovieRatingJson> list = movieService.findAllByUser(request);
		if (list.size() == 0) {
			response.put("mensaje", "No tiene peliculas para mostrar.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<MovieRatingJson>>(list, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/my-movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMovieById(HttpServletRequest request, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		MovieRatingJson movieRatingJson = movieService.findByIdAndUser(request, id);
		if (movieRatingJson == null) {
			response.put("mensaje", "La pelicula no existe en base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MovieRatingJson>(movieRatingJson, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/my-movies/movie", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createMovieRating(HttpServletRequest request, @RequestBody MovieRating movieRating) {
		Map<String, Object> response = new HashMap<>();
		MovieRating newMovieRating = movieService.saveMovieRating(request, movieRating);
		if (newMovieRating == null) {
			response.put("mensaje", "No se pudo guardar su pelicula.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<MovieRating>(newMovieRating, HttpStatus.CREATED);
		}
	}
	
	@PutMapping(value = "/my-movies/movie/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMovieRating(HttpServletRequest request, @PathVariable Long id, @RequestBody MovieRating movieRating) {
		Map<String, Object> response = new HashMap<>();
		MovieRating updatedMovieRating = movieService.updateMovieRating(request, id, movieRating);
		if (updatedMovieRating == null) {
			response.put("mensaje", "No existe pelicula en base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<MovieRating>(updatedMovieRating, HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/my-movies/movie/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMovieRating(HttpServletRequest request, @PathVariable Long id) {
		movieService.deleteMovieRating(request, id);
	}
	
}

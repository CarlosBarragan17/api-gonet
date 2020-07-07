package com.gonet.aeromexico.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gonet.aeromexico.api.models.dao.MovieRatingDao;
import com.gonet.aeromexico.api.models.dao.UsuarioDao;
import com.gonet.aeromexico.api.models.dto.Movie;
import com.gonet.aeromexico.api.models.dto.MovieRatingJson;
import com.gonet.aeromexico.api.models.entity.MovieRating;
import com.gonet.aeromexico.api.models.entity.Usuario;
import com.gonet.aeromexico.api.services.MovieService;
import com.gonet.aeromexico.api.utils.Utility;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Value("${app.movieapi}")
	private String apiUrl;
	@Value("${app.key}")
	private String apiKey;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MovieRatingDao movieRatingDao;
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Movie getMovie(String name) {
		String urlService = apiUrl.concat("t=").concat(name).concat("&apikey=").concat(apiKey);
		Movie movie = restTemplate.getForObject(urlService, Movie.class);
		if (movie.getTitle() == null) {
			return null;
		} else {
			return movie;
		}		
	}

	@Override
	public List<MovieRatingJson> findAllByUser(HttpServletRequest request) {
		String username = Utility.getUserFromToken(request);
		Usuario usuario = usuarioDao.findByUsername(username);
		List<MovieRating> userList = movieRatingDao.findAllByUsuario(usuario);
		List<MovieRatingJson> results = new ArrayList<>();
		if (userList.size() > 0) {
			userList.stream().forEach(m -> {
				String urlService = apiUrl.concat("i=").concat(m.getImdbId()).concat("&apikey=").concat(apiKey);
				Movie movie = restTemplate.getForObject(urlService, Movie.class);
				results.add(new MovieRatingJson(m, movie));
			});
		}
		return results;
	}

	@Override
	public MovieRating saveMovieRating(HttpServletRequest request, MovieRating movieRating) {
		String username = Utility.getUserFromToken(request);
		Usuario usuario = usuarioDao.findByUsername(username);
		movieRating.setUsuario(usuario);
		return movieRatingDao.save(movieRating);
	}

	@Override
	public MovieRating updateMovieRating(HttpServletRequest request, Long id, MovieRating movieRating) {
		String username = Utility.getUserFromToken(request);
		Usuario usuario = usuarioDao.findByUsername(username);
		MovieRating dbMovieRating = movieRatingDao.findByMovieIdAndUsuario(id, usuario);
		if (dbMovieRating == null) {
			return null;
		}
		if (movieRating.getImdbId() != null) {
			dbMovieRating.setImdbId(movieRating.getImdbId());	
		}
		if (movieRating.getRating() != null) {
			dbMovieRating.setRating(movieRating.getRating());
		}
		if (movieRating.getViewDate() != null) {
			dbMovieRating.setViewDate(movieRating.getViewDate());
		}
		if (movieRating.getComments() != null) {
			dbMovieRating.setComments(movieRating.getComments());
		}
		return movieRatingDao.save(dbMovieRating);
	}

	@Override
	public void deleteMovieRating(HttpServletRequest request, Long id) {
		String username = Utility.getUserFromToken(request);
		Usuario usuario = usuarioDao.findByUsername(username);
		MovieRating movieRating = movieRatingDao.findByMovieIdAndUsuario(id, usuario);
		if (movieRating != null) {
			movieRatingDao.delete(movieRating);
		}
	}

	@Override
	public MovieRatingJson findByIdAndUser(HttpServletRequest request, Long id) {
		String username = Utility.getUserFromToken(request);
		Usuario usuario = usuarioDao.findByUsername(username);
		MovieRatingJson result = null;
		MovieRating movieRating = movieRatingDao.findByMovieIdAndUsuario(id, usuario);
		if (movieRating != null) {
			String urlService = apiUrl.concat("i=").concat(movieRating.getImdbId()).concat("&apikey=").concat(apiKey);
			Movie movie = restTemplate.getForObject(urlService, Movie.class);
			result = new MovieRatingJson(movieRating, movie);
		}
		return result;
	}

}

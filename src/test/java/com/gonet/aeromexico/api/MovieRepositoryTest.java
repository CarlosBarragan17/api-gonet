package com.gonet.aeromexico.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gonet.aeromexico.api.models.dao.MovieRatingDao;
import com.gonet.aeromexico.api.models.entity.MovieRating;
import com.gonet.aeromexico.api.models.entity.Role;
import com.gonet.aeromexico.api.models.entity.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private MovieRatingDao movieRatingDao;
	
	@Test
	public void saveMovieRating() {
		MovieRating movieRating = createMovie();
		entityManager.persistAndFlush(movieRating);
		assertThat(movieRating.getMovieId()).isNotNull();
	}
	
	@Test
	public void deleteMovieRating() {
		MovieRating movieRating = createMovie();
		entityManager.persistAndFlush(movieRating);
		movieRatingDao.deleteAll();
		assertThat(movieRatingDao.findAll()).isEmpty();
	}
	
	private MovieRating createMovie() {
		MovieRating movieRating = new MovieRating();
		Usuario usuario = new Usuario();
		usuario.setUsername("prueba");
		usuario.setPassword("$2a$10$XHK0HdLOfJLk57TrsURaF.dCTTX430THLjqlE7Soz4QitLO4DiqOS");
		usuario.setEnabled(true);
		usuario.setNombre("Carlos");
		usuario.setApellido("Barragan");
		usuario.setEmail("prueba@example.com");
		Role role = new Role();
		role.setNombre("ROLE_PRUEBA");
		entityManager.persistAndFlush(role);
		usuario.setRoles(Arrays.asList(role));
		entityManager.persistAndFlush(usuario);
		movieRating.setUsuario(usuario);
		movieRating.setRating(8.50);
		movieRating.setViewDate(new Date());
		movieRating.setComments("Muy buena");
		movieRating.setImdbId("tt1285016");
		return movieRating;
	}

}

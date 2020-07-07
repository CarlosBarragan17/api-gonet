package com.gonet.aeromexico.api.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movie_ratings")
public class MovieRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonProperty("calificacion")
	private Double rating;
	
	@JsonProperty("fecha-visto")
	@Temporal(TemporalType.DATE)
	private Date viewDate;
	
	@JsonProperty("comentarios")
	private String comments;
	
	private String imdbId;
	
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

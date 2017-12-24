package com.movie_genre.model;

public class Movie_GenreVO implements java.io.Serializable {
	private Long movie_id;
	private Integer genre_id;
	
	public Long getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	public Integer getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}
}

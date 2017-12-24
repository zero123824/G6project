package com.movie_genre.model;

import java.util.*;

public interface Movie_GenreDAO_interface {
	public void insert(Movie_GenreVO movie_genreVO);
	public void update(Movie_GenreVO movie_genreVO);
	public void delete(Long movie_id, Integer genre_id);
	public List<Movie_GenreVO> getAll();
	//查詢某類型的電影
	public Set<Movie_GenreVO> getMovieByGenre(Integer genre_id);
}

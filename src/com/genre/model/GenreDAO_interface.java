package com.genre.model;

import java.util.List;

public interface GenreDAO_interface {
	public void insert(GenreVO genreVO);
	public void update(GenreVO genreVO);
	public void delete(Integer genre_id);
	public GenreVO findByPrimaryKey(Integer genre_id);
	public List<GenreVO> getAll();
}

package com.genre.model;

import java.util.List;

public interface GenreDAO_interface {
	public GenreVO findByPrimaryKey(Integer genre_id);
	public List<GenreVO> getAll();
}

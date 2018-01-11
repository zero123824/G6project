package com.genre.model;

import java.util.List;

public class GenreService {
	
	private GenreDAO_interface dao;
	
	public GenreService() {
		dao = new GenreDAO();
	}
	
	public GenreVO getOneGenre(Integer genre_id) {
		return dao.findByPrimaryKey(genre_id);
	}
	
	public List<GenreVO> getAll() {
		return dao.getAll();
	}
}

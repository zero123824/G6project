package com.genre.model;

public class GenreVO implements java.io.Serializable {
	private Integer genre_id;
	private String genre_name;
	
	public Integer getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
}

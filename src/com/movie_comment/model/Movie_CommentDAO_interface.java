package com.movie_comment.model;

import java.util.List;

public interface Movie_CommentDAO_interface {
	public void insert(Movie_CommentVO movie_commentVO);
	public void update(Movie_CommentVO movie_commentVO);
	public void delete(Long comment_id);
	public Movie_CommentVO findByPrimaryKey(Long comment_id);
	public List<Movie_CommentVO> getAll();
}

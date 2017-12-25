package com.movie.model;

import java.util.*;

import com.movie_comment.model.Movie_CommentVO;
import com.movie_schedule.model.Movie_ScheduleVO;

public interface MovieDAO_interface {
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(Long movie_id);
	public MovieVO findByPrimaryKey(Long movie_id);
	public List<MovieVO> getAll();
	//查詢某電影的場次
	public Set<Movie_ScheduleVO> getScheduleByMovie(Long movie_id);
	//查詢某電影的評論
	public Set<Movie_CommentVO> getCommentByMovie(Long movie_id);
}

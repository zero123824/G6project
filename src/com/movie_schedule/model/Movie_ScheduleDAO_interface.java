package com.movie_schedule.model;

import java.util.*;

public interface Movie_ScheduleDAO_interface {
	public void insert(Movie_ScheduleVO movie_scheduleVO);
	public void update(Movie_ScheduleVO movie_scheduleVO);
	public void delete(Long schedule_id);
	public Movie_ScheduleVO findByPrimaryKey(Long schedule_id);
	public List<Movie_ScheduleVO> getAll();
}

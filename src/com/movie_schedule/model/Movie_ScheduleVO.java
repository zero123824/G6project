package com.movie_schedule.model;

import java.sql.Timestamp;

public class Movie_ScheduleVO implements java.io.Serializable {
	private Long schedule_id;
	private Long movie_id;
	private Integer hall_id;
	private Timestamp schedule_date;
	private Integer sold_seat;
	private String seat_stat;
	private Boolean midnight;
	
	public Long getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Long schedule_id) {
		this.schedule_id = schedule_id;
	}
	public Long getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public Timestamp getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(Timestamp schedule_date) {
		this.schedule_date = schedule_date;
	}
	public Integer getSold_seat() {
		return sold_seat;
	}
	public void setSold_seat(Integer sold_seat) {
		this.sold_seat = sold_seat;
	}
	public String getSeat_stat() {
		return seat_stat;
	}
	public void setSeat_stat(String seat_stat) {
		this.seat_stat = seat_stat;
	}
	public Boolean getMidnight() {
		return midnight;
	}
	public void setMidnight(Boolean midnight) {
		this.midnight = midnight;
	}
}

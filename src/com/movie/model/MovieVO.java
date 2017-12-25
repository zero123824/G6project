package com.movie.model;

import java.io.Reader;
import java.sql.Date;

public class MovieVO implements java.io.Serializable {
	private Long movie_id;
	private String movie_name_ch;
	private String movie_name_en;
	private byte[] movie_poster;
	private String movie_rating;
	private Date movie_date;
	private Integer movie_time;
	private String movie_company;
	private String movie_director;
	private String movie_actor;
	private String movie_introduce;
	private String movie_url;
	private Boolean movie_stat;
	
	public Long getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name_ch() {
		return movie_name_ch;
	}
	public void setMovie_name_ch(String movie_name_ch) {
		this.movie_name_ch = movie_name_ch;
	}
	public String getMovie_name_en() {
		return movie_name_en;
	}
	public void setMovie_name_en(String movie_name_en) {
		this.movie_name_en = movie_name_en;
	}
	public byte[] getMovie_poster() {
		return movie_poster;
	}
	public void setMovie_poster(byte[] movie_poster) {
		this.movie_poster = movie_poster;
	}
	public String getMovie_rating() {
		return movie_rating;
	}
	public void setMovie_rating(String movie_rating) {
		this.movie_rating = movie_rating;
	}
	public Date getMovie_date() {
		return movie_date;
	}
	public void setMovie_date(Date movie_date) {
		this.movie_date = movie_date;
	}
	public Integer getMovie_time() {
		return movie_time;
	}
	public void setMovie_time(Integer movie_time) {
		this.movie_time = movie_time;
	}
	public String getMovie_company() {
		return movie_company;
	}
	public void setMovie_company(String movie_company) {
		this.movie_company = movie_company;
	}
	public String getMovie_director() {
		return movie_director;
	}
	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	public String getMovie_actor() {
		return movie_actor;
	}
	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}
	public String getMovie_introduce() {
		return movie_introduce;
	}
	public void setMovie_introduce(String movie_introduce) {
		this.movie_introduce = movie_introduce;
	}
	public String getMovie_url() {
		return movie_url;
	}
	public void setMovie_url(String movie_url) {
		this.movie_url = movie_url;
	}
	public Boolean getMovie_stat() {
		return movie_stat;
	}
	public void setMovie_stat(Boolean movie_stat) {
		this.movie_stat = movie_stat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie_id == null) ? 0 : movie_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieVO other = (MovieVO) obj;
		if (movie_id == null) {
			if (other.movie_id != null)
				return false;
		} else if (!movie_id.equals(other.movie_id))
			return false;
		return true;
	}
	
}

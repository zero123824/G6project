package com.movie_comment.model;

import java.sql.Timestamp;

public class Movie_CommentVO {
	private Long comment_id;
	private Long movie_id;
	private Long member_id;
	private Timestamp comment_time;
	private String comment_content;
	private Boolean comment_stat;
	
	public Long getComment_id() {
		return comment_id;
	}
	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}
	public Long getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public Timestamp getComment_time() {
		return comment_time;
	}
	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Boolean getComment_stat() {
		return comment_stat;
	}
	public void setComment_stat(Boolean comment_stat) {
		this.comment_stat = comment_stat;
	}
}

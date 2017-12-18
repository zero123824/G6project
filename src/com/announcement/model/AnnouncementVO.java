package com.announcement.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnnouncementVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer announce_id;
	private String announce_title;
	private String announce_content;
	private Timestamp announce_time;
	private Integer empno;
	private Integer announce_status;
	public Integer getAnnounce_id() {
		return announce_id;
	}
	public AnnouncementVO setAnnounce_id(Integer announce_id) {
		this.announce_id = announce_id;
		return this;
	}
	public String getAnnounce_title() {
		return announce_title;
	}
	public AnnouncementVO setAnnounce_title(String announce_title) {
		this.announce_title = announce_title;
		return this;
	}
	public String getAnnounce_content() {
		return announce_content;
	}
	public AnnouncementVO setAnnounce_content(String announce_content) {
		this.announce_content = announce_content;
		return this;
	}
	public Timestamp getAnnounce_time() {
		return announce_time;
	}
	public AnnouncementVO setAnnounce_time(Timestamp announce_time) {
		this.announce_time = announce_time;
		return this;
	}
	public Integer getEmpno() {
		return empno;
	}
	public AnnouncementVO setEmpno(Integer empno) {
		this.empno = empno;
		return this;
	}
	public Integer getAnnounce_status() {
		return announce_status;
	}
	public AnnouncementVO setAnnounce_status(Integer announce_status) {
		this.announce_status = announce_status;
		return this;
	}
	

}

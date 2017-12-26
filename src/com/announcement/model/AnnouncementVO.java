package com.announcement.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnnouncementVO implements Serializable ,Comparable<AnnouncementVO>{
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
	@Override
	public int compareTo(AnnouncementVO announcementVO) {
		if(this.getAnnounce_time().getTime()>announcementVO.getAnnounce_time().getTime()){
			return 1;
		}else if(this.getAnnounce_time().getTime() == announcementVO.getAnnounce_time().getTime()){
			return 0;
		}else{
			return -1;
		}		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announce_id == null) ? 0 : announce_id.hashCode());
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
		AnnouncementVO other = (AnnouncementVO) obj;
		if (announce_id == null) {
			if (other.announce_id != null)
				return false;
		} else if (!announce_id.equals(other.announce_id))
			return false;
		return true;
	}
}

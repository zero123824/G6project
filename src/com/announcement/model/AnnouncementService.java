package com.announcement.model;

import java.util.Collections;
import java.util.List;

public class AnnouncementService {
	private AnnouncementDAO_interface dao;
	public AnnouncementService() {
		dao = new AnnouncementDAO();
	}
	public void add(AnnouncementVO newannouncement) {
		dao.add(newannouncement);
	}
		
	
	public void update(AnnouncementVO selectedannoun) {
		dao.update(selectedannoun);
	}
		
	public void delete(Integer announce_id) {
		dao.delete(announce_id);
	}

	public AnnouncementVO findByPK(Integer announce_id) {
		return dao.findByPK(announce_id);
	}

	public List<AnnouncementVO> getAll() {
		List<AnnouncementVO> announcelist = dao.getAll();
		Collections.reverse(announcelist);
		return announcelist;
	}
	
}

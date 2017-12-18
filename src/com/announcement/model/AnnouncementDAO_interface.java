package com.announcement.model;

import java.util.List;

public interface AnnouncementDAO_interface {
	void add(AnnouncementVO newannouncement);
	void update(AnnouncementVO selectedannoun);
	void delete(Integer announce_id);
	AnnouncementVO findByPK(Integer announce_id);
	List<AnnouncementVO> getAll();
}

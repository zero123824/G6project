package com.memberFavor.model;

import java.util.List;

public interface MemberFavorDAO_interface {
	void add(MemberFavorVO newmemberfavor);
	void delete(Integer member_id, Integer genre_id);
	List<MemberFavorVO> getOneMemFavor(Integer member_id);
}

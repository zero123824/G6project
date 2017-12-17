package com.member.model;
import java.util.List;

public interface MemberDAO_interface {
	void add(MemberVO newmember);
	void update(MemberVO selectedmem);
	void delete(Integer member_id);
	MemberVO findByPK(Integer member_id);
	List<MemberVO> getAll();
	
}

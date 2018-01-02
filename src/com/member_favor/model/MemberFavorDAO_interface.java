package com.member_favor.model;

import java.util.List;

public interface MemberFavorDAO_interface {
	void add(MemberFavorVO newmemberfavor);
	void delete(Integer member_id, Integer genre_id);
	List<MemberFavorVO> getOneMemFavor(Integer member_id);
	
	//使用純JDBC進行交易的ACID
	void addFavorByGeneratedMember_ID(MemberFavorVO memberFavorVO,java.sql.Connection con);
}

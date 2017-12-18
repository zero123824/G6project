package com.memberFavor.model;

import java.io.Serializable;

public class MemberFavorVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer member_id;
	private Integer genre_id;
	public Integer getMember_id() {
		return member_id;
	}
	public MemberFavorVO setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getGenre_id() {
		return genre_id;
	}
	public MemberFavorVO setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
		return this;
	}

}

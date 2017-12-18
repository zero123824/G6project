package com.friend.model;

import java.io.Serializable;

public class FriendVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer member_id1;
	private Integer member_id2;
	private Integer relation_status;
	private String member_msg;
	private Integer msg_status;
	
	public Integer getMember_id1() {
		return member_id1;
	}
	public FriendVO setMember_id1(Integer member_id1) {
		this.member_id1 = member_id1;
		return this;
	}
	public Integer getMember_id2() {
		return member_id2;
	}
	public FriendVO setMember_id2(Integer member_id2) {
		this.member_id2 = member_id2;
		return this;
	}
	public Integer getRelation_status() {
		return relation_status;
	}
	public FriendVO setRelation_status(Integer relation_status) {
		this.relation_status = relation_status;
		return this;
	}
	public String getMember_msg() {
		return member_msg;
	}
	public FriendVO setMember_msg(String member_msg) {
		this.member_msg = member_msg;
		return this;
	}
	public Integer getMsg_status() {
		return msg_status;
	}
	public FriendVO setMsg_status(Integer msg_status) {
		this.msg_status = msg_status;
		return this;
	}
	



}

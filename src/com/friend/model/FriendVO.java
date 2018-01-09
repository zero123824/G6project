package com.friend.model;

import java.io.Serializable;

public class FriendVO implements Serializable,Comparable<FriendVO> {
	private static final long serialVersionUID = 1L;
	
	private Integer member_id1;
	private Integer member_id2;
	private Integer relation_status;
	private String member_msg;
	private java.sql.Timestamp last_msg_time;
	private Integer member_id1_unread;
	private Integer member_id2_unread;
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
	public java.sql.Timestamp getLast_msg_time() {
		return last_msg_time;
	}
	public FriendVO setLast_msg_time(java.sql.Timestamp last_msg_time) {
		this.last_msg_time = last_msg_time;
		return this;
	}
	public Integer getMember_id1_unread() {
		return member_id1_unread;
	}
	public FriendVO setMember_id1_unread(Integer member_id1_unread) {
		this.member_id1_unread = member_id1_unread;
		return this;
	}
	public Integer getMember_id2_unread() {
		return member_id2_unread;
	}
	public FriendVO setMember_id2_unread(Integer member_id2_unread) {
		this.member_id2_unread = member_id2_unread;
		return this;
	}
	@Override
	public int compareTo(FriendVO friendVO) {
		if(this.getLast_msg_time().getTime()<friendVO.getLast_msg_time().getTime()){
			return 1;
		}else if(this.getLast_msg_time().getTime() == friendVO.getLast_msg_time().getTime()){
			return 0;
		}else{
			return -1;
		}
	}
}

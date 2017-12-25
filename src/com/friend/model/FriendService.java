package com.friend.model;

import java.util.List;

public class FriendService {
	private FriendDAO_interface dao;
	public FriendService(){
		dao = new FriendDAO();
	}
	
	public FriendVO add(Integer member_id1 ,Integer member_id2,String member_msg) {
		FriendVO friendVO = new FriendVO();
		friendVO.setMember_id1(member_id1);
		friendVO.setMember_id2(member_id2);
		friendVO.setRelation_status(0);
		friendVO.setMember_msg(member_msg);
		friendVO.setMsg_status(0);
		dao.add(friendVO);
		return friendVO;
	}
	
	public FriendVO update(Integer member_id1 ,Integer member_id2,Integer relation_status,String member_msg,Integer msg_status) {
		FriendVO friendVO = new FriendVO();
		friendVO.setMember_id1(member_id1);
		friendVO.setMember_id2(member_id2);
		friendVO.setRelation_status(relation_status);
		friendVO.setMember_msg(member_msg);
		friendVO.setMsg_status(msg_status);		
		dao.update(friendVO);
		return friendVO;
	}
	
	public void delete(FriendVO friendVO) {		
		dao.delete(friendVO);
	}

	public List<FriendVO> getOneMemFriends(Integer member_id1) {
		return dao.getOneMemFriends(member_id1);
	}	
}

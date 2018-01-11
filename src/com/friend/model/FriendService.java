package com.friend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;
import com.member.model.MemberVO;

public class FriendService {
	private FriendDAO_interface dao;
	private MemberDAO_interface memdao;
	public FriendService(){
		dao = new FriendDAO();
		memdao = new MemberDAO();
	}
	
	public FriendVO add(Integer member_id1 ,Integer member_id2,String member_msg,Long nowtime) {
		FriendVO friendVO = new FriendVO();
		friendVO.setMember_id1(member_id1);
		friendVO.setMember_id2(member_id2);
		friendVO.setRelation_status(0);
		friendVO.setMember_msg(member_msg);
		friendVO.setLast_msg_time(new java.sql.Timestamp(nowtime));
		friendVO.setMember_id2_unread(1);
		dao.add(friendVO);
		return friendVO;
	}
	
	public FriendVO update(Integer member_id1 ,Integer member_id2,Integer relation_status,String member_msg,Integer msg_status,Long nowtime) {
		FriendVO friendVO = new FriendVO();
		friendVO.setMember_id1(member_id1);
		friendVO.setMember_id2(member_id2);
		friendVO.setRelation_status(relation_status);
		friendVO.setMember_id1_unread(0);
		friendVO.setMember_id2_unread(0);
		friendVO.setLast_msg_time(new java.sql.Timestamp(nowtime));
		friendVO.setMember_msg(member_msg);
		friendVO.setMsg_status(msg_status);
		dao.update(friendVO);
		return friendVO;
	}
	
	public void delete(Integer member_id1 ,Integer member_id2) {
		FriendVO friendVO = new FriendVO();
		friendVO.setMember_id1(member_id1);
		friendVO.setMember_id2(member_id2);
		dao.delete(friendVO);
	}

	public Set<MemberVO> getOneMemFriends(Integer member_id) {
		Set<MemberVO> friends = new LinkedHashSet<MemberVO>();
		List<FriendVO> onememfriendlist = dao.getOneMemFriends(member_id);
		Collections.sort(onememfriendlist);
		for(FriendVO friendVO : onememfriendlist){
			MemberVO onemem = new MemberVO();
			onemem = memdao.findByPK(friendVO.getMember_id1());
			friends.add(onemem);
			onemem = memdao.findByPK(friendVO.getMember_id2());
			friends.add(onemem);
		}
		friends.remove(memdao.findByPK(member_id));
		return friends;
	}
	
	public List<FriendVO> getFriendRelation(Integer member_id) {
		List<FriendVO> onememfriendlist = dao.getOneMemFriends(member_id);
		Collections.sort(onememfriendlist);
		return onememfriendlist;
	}
	
	public FriendVO getRelationInTwo(Integer member_id1, Integer member_id2) {
		FriendVO relationInTwo = dao.getRelationInTwo(member_id1, member_id2);
		return relationInTwo;	
	}
	
	public boolean updateMessage(Integer receiver ,Integer sender,String member_msg,Integer msg_status,Long nowtime){
		FriendVO friendVO = dao.getRelationInTwo(receiver, sender) ;
		Integer unread = 0;
		friendVO.setMember_id1_unread(++unread);
		if(friendVO.getRelation_status() == null){
			friendVO = dao.getRelationInTwo(sender, receiver);
			unread = friendVO.getMember_id2_unread();
			friendVO.setMember_id2_unread(++unread);
		}
		unread = friendVO.getMember_id1_unread();
		friendVO.setRelation_status(friendVO.getRelation_status());
		System.out.println(friendVO.getMember_msg());
		friendVO.setMember_msg(friendVO.getMember_msg()+"\n"+member_msg);
		friendVO.setMsg_status(msg_status);
		friendVO.setLast_msg_time(new java.sql.Timestamp(nowtime));
		dao.update(friendVO);
		return true;
	}
	
	public boolean updateMessageStatus(Integer receiver ,Integer sender,Integer memberPosition){
		FriendVO friendVO = dao.getRelationInTwo(receiver, sender) ;
		Integer unread = 0;
		if(friendVO.getRelation_status() == null){
			friendVO = dao.getRelationInTwo(sender, receiver);
		}
		if(memberPosition == 1){
			friendVO.setMember_id1_unread(unread);
		}else{
			friendVO.setMember_id2_unread(unread);
		}
		dao.update(friendVO);
		return true;
	}
}

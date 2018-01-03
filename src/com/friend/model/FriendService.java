package com.friend.model;

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

	public Set<MemberVO> getOneMemFriends(Integer member_id) {
		Set<MemberVO> friends = new LinkedHashSet<MemberVO>();
		List<FriendVO> onememfriendlist = dao.getOneMemFriends(member_id);
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
	
	public FriendVO getRelationInTwo(Integer member_id1, Integer member_id2) {
		FriendVO relationInTwo = dao.getRelationInTwo(member_id1, member_id2);
		return relationInTwo;	
	}
}

package com.friend.model;

import java.util.List;

public interface FriendDAO_interface {
	void add(FriendVO newfriend);
	void update(FriendVO friendVO);
	void delete(FriendVO friendVO);
	List<FriendVO> getOneMemFriends(Integer member_id);
	
	FriendVO getRelationInTwo(Integer member_id1,Integer member_id2);
}

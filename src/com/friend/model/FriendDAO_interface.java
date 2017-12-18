package com.friend.model;

import java.util.List;

public interface FriendDAO_interface {
	void add(FriendVO newfriend);
	void update(FriendVO member_id1, FriendVO member_id2);
	void delete(FriendVO member_id1, FriendVO member_id2);
	List<FriendVO> getOneMemFriends(Integer member_id1);
}

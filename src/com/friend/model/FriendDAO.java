package com.friend.model;


import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FriendDAO implements FriendDAO_interface{
	//共用資源連線池
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	private static final String INSERT = "INSERT INTO FRIEND (MEMBER_ID1,MEMBER_ID2,RELATION_STATUS,LAST_MSG_TIME,MEMBER_ID2_UNREAD,MSG_STATUS) "
										 + "VALUES(?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE FRIEND SET RELATION_STATUS = ?,MEMBER_MSG=?,LAST_MSG_TIME=?,MEMBER_ID1_UNREAD=?,MEMBER_ID2_UNREAD=?, MSG_STATUS=?"
										 +"WHERE MEMBER_ID1 = ? AND MEMBER_ID2 = ?";
	private static final String DELETE = "DELETE FROM FRIEND WHERE MEMBER_ID1 = ? AND MEMBER_ID2 = ?";
	private static final String GETONEMEMFRIEND = "SELECT * FROM FRIEND WHERE MEMBER_ID1 = ? OR MEMBER_ID2 = ?";
	private static final String GETMSGS = "SELECT * FROM FRIEND WHERE MEMBER_ID1 = ? AND MEMBER_ID2 = ?";
		
	@Override
	public void add(FriendVO newfriend) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			Reader reader = new StringReader(newfriend.getMember_msg());
			psmt = con.prepareStatement(INSERT);
			psmt.setInt(1, newfriend.getMember_id1());
			psmt.setInt(2, newfriend.getMember_id2());
			psmt.setInt(3, newfriend.getRelation_status());
			psmt.setTimestamp(4, newfriend.getLast_msg_time());
			psmt.setInt(5, newfriend.getMember_id2_unread());
			psmt.setInt(6, 0);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Override
	public void update(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			Reader reader = new StringReader(friendVO.getMember_msg());
			psmt = con.prepareStatement(UPDATE);
			psmt.setInt(7, friendVO.getMember_id1());
			psmt.setInt(8, friendVO.getMember_id2());
			psmt.setInt(1, friendVO.getRelation_status());
			psmt.setCharacterStream(2, reader);
			psmt.setTimestamp(3, friendVO.getLast_msg_time());
			psmt.setInt(4, friendVO.getMember_id1_unread());
			psmt.setInt(5, friendVO.getMember_id2_unread());
			psmt.setInt(6, friendVO.getMsg_status());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Override
	public void delete(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, friendVO.getMember_id1());
			psmt.setInt(2, friendVO.getMember_id2());
			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}				
	}

	@Override
	public List<FriendVO> getOneMemFriends(Integer member_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		FriendVO friend = null;
		ResultSet rs = null;
		List<FriendVO> memfriendList = new ArrayList<>();
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETONEMEMFRIEND);
			psmt.setInt(1, member_id);
			psmt.setInt(2, member_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				friend = new FriendVO();
				friend.setMember_id1(rs.getInt(1));
				friend.setMember_id2(rs.getInt(2));
				friend.setRelation_status(rs.getInt(3));
				friend.setMember_msg(rs.getString(4));
				friend.setLast_msg_time(rs.getTimestamp(5));
				friend.setMember_id1_unread(rs.getInt(6));
				friend.setMember_id2_unread(rs.getInt(7));
				friend.setMsg_status(rs.getInt(8));
				memfriendList.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memfriendList;
	}

	@Override
	public FriendVO getRelationInTwo(Integer member_id1, Integer member_id2) {
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		FriendVO friendVO = new FriendVO();
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETMSGS);
			psmt.setInt(1, member_id1);
			psmt.setInt(2, member_id2);
			rs = psmt.executeQuery();
			while (rs.next()) {
				friendVO.setMember_id1(rs.getInt(1));
				friendVO.setMember_id2(rs.getInt(2));
				friendVO.setRelation_status(rs.getInt(3));
				friendVO.setMember_msg(rs.getString(4));
				friendVO.setLast_msg_time(rs.getTimestamp(5));
				friendVO.setMember_id1_unread(rs.getInt(6));
				friendVO.setMember_id2_unread(rs.getInt(7));
				friendVO.setMsg_status(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return friendVO;
	}
}

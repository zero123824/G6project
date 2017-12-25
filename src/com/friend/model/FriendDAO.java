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
	private static final String INSERT = "INSERT INTO FRIEND (MEMBER_ID1,MEMBER_ID2,RELATION_STATUS,MEMBER_MSG,MSG_STATUS) "
										 + "VALUES(?,?,?,?,?)";
	private static final String UPDATE = "UPDATE FRIEND SET RELATION_STATUS = ?, MEMBER_MSG=?, MSG_STATUS=? "
										 +"WHERE MEMBER_ID1 = ? AND MEMBER_ID2 = ?";
	private static final String DELETE = "DELETE FROM FRIEND WHERE MEMBER_ID1 = ? AND MEMBER_ID2 = ?";
	private static final String GETONEMEMFRIEND = "SELECT * FROM FRIEND WHERE MEMBER_ID1 = ? ";
		
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
			psmt.setCharacterStream(4, reader);
			psmt.setInt(5, newfriend.getMsg_status());
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
			psmt.setInt(4, friendVO.getMember_id1());
			psmt.setInt(5, friendVO.getMember_id2());
			psmt.setInt(1, friendVO.getRelation_status());
			psmt.setCharacterStream(2, reader);
			psmt.setInt(3, friendVO.getMsg_status());

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
	public List<FriendVO> getOneMemFriends(Integer member_id1) {
		Connection con = null;
		PreparedStatement psmt = null;
		FriendVO friend = null;
		ResultSet rs = null;
		List<FriendVO> memfriendList = new ArrayList<>();
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETONEMEMFRIEND);
			psmt.setInt(1, member_id1);
			rs = psmt.executeQuery();
			while (rs.next()) {
				friend = new FriendVO();
				friend.setMember_id1(rs.getInt(1));
				friend.setMember_id2(rs.getInt(2));
				friend.setRelation_status(rs.getInt(3));
				friend.setMember_msg(rs.getString(4));
				friend.setMsg_status(rs.getInt(5));
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
}

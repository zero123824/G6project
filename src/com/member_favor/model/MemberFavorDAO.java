package com.member_favor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberFavorDAO implements MemberFavorDAO_interface {
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
	private static final String INSERT = "INSERT INTO MEMBER_FAVOR (MEMBER_ID,GENRE_ID) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM MEMBER_FAVOR WHERE MEMBER_ID = ? AND GENRE_ID = ?";
	private static final String GETONEMEMFAVOR = "SELECT * FROM MEMBER_FAVOR WHERE MEMBER_ID = ? ";
	
	@Override
	public void add(MemberFavorVO newmemberfavor) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT);
			con.setAutoCommit(false);
			psmt.setInt(1, newmemberfavor.getMember_id());
			psmt.setInt(2, newmemberfavor.getGenre_id());
			psmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if(con!=null)
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	public void delete(Integer member_id, Integer genre_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, member_id);
			psmt.setInt(2, genre_id);
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
	public List<MemberFavorVO> getOneMemFavor(Integer member_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		MemberFavorVO memberfavor = null;
		ResultSet rs = null;
		List<MemberFavorVO> memfavorList = new ArrayList<>();

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETONEMEMFAVOR);
			psmt.setInt(1, member_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				memberfavor = new MemberFavorVO();
				memberfavor.setMember_id(rs.getInt(1));
				memberfavor.setGenre_id(rs.getInt(2));
				memfavorList.add(memberfavor);
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
		return memfavorList;
	}
}

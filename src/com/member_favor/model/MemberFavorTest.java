package com.member_favor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberFavorTest {
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static final String INSERT = "INSERT INTO MEMBER_FAVOR (MEMBER_ID,GENRE_ID) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM MEMBER_FAVOR WHERE MEMBER_ID = ? AND GENRE_ID = ?";
	private static final String GETONEMEMFAVOR = "SELECT * FROM MEMBER_FAVOR WHERE MEMBER_ID = ? ";
	
	
	public static void main(String[] args) {
		MemberFavorVO memberfavor = new MemberFavorVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ba105g6", "ba105g6");
			System.out.println("success");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		memberfavor.setMember_id(1000000004).setGenre_id(90004);
//		add(memberfavor);	
//		delete(1000000002,90004);
		List list = getOneMemFavor(1000000002);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			MemberFavorVO memfavor = (MemberFavorVO)it.next();
			if(memfavor.getMember_id() == 90013){
				delete(memfavor.getMember_id() ,memfavor.getGenre_id());
			}
			else{
				System.out.print(memfavor.getMember_id()+",");
				System.out.println(memfavor.getGenre_id());
			}
		}

	}
	
	public static void add(MemberFavorVO newmemberfavor) {
		try {
			psmt = con.prepareStatement(INSERT);
			psmt.setInt(1, newmemberfavor.getMember_id());
			psmt.setInt(2, newmemberfavor.getGenre_id());
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

	
	public static void delete(Integer member_id, Integer genre_id) {
		try {
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

	public static List<MemberFavorVO> getOneMemFavor(Integer member_id) {
		MemberFavorVO memberfavor = null;
		ResultSet rs = null;
		List<MemberFavorVO> memfavorList = new ArrayList<>();

		try {
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
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
		}
		return memfavorList;
	}
}

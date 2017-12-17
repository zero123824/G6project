package com.member.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface{
	private static Connection con = null;
	static {
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	private PreparedStatement psmt;
	private static final String INSERT = "INSERT INTO MEMBER_INFO (MEMBER_ID, MEMBER_ACCOUNT, MEMBER_PSW, MEMBER_LASTNAME, MEMBER_FIRSTNAME, MEMBER_ADDRESS, MOBILENUM, MEMBER_EMAILADDRESS, MEMBER_BIRTHDAY, MEMBER_IDCODE, CREADITCARD, SUBSENEWS, MEMBER_SEX, MEMBER_LOCK_STATUS, MEMBER_PIC, MEMBER_NICKNAME)"
										  +"VALUES ('1'||LPAD(MEMBER_SEQUENCE.NEXTVAL,9,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE MEMBER_INFO SET MEMBER_ACCOUNT = ? ,MEMBER_PSW = ?,MEMBER_LASTNAME = ?,"
										  +"MEMBER_FIRSTNAME = ?,MEMBER_ADDRESS=?,MOBILENUM=?,MEMBER_EMAILADDRESS=?,"
										  +"MEMBER_BIRTHDAY=?,MEMBER_IDCODE=?,CREADITCARD=?,SUBSENEWS=?,MEMBER_SEX=?,"
										  +"MEMBER_LOCK_STATUS=?,MEMBER_PIC=?,MEMBER_NICKNAME=? WHERE MEMBER_ID = ? ";
	private static final String DELETE = "DELETE FROM MEMBER_INFO WHERE MEMBER_ID = ? ";
	private static final String SELECT = "SELECT * FROM MEMBER_INFO WHERE MEMBER_ID = ? ";
	private static final String GETALL = "SELECT * FROM MEMBER_INFO";
	
		
	
	@Override
	public void add(MemberVO newmember){
		try {
			psmt = con.prepareStatement(INSERT);
			
			psmt.setString(1, newmember.getMember_account());
			psmt.setString(2, newmember.getMember_psw());
			psmt.setString(3, newmember.getMember_lastname());
			psmt.setString(4, newmember.getMember_firstname());
			psmt.setString(5, newmember.getMember_address());
			psmt.setString(6, newmember.getMobilenum());
			psmt.setString(7, newmember.getMember_emailaddress());
			psmt.setDate(8, newmember.getMember_birthday());
			psmt.setString(9, newmember.getMember_idcode());
			psmt.setString(10, newmember.getCreaditcard());
			psmt.setInt(11, newmember.getSubsenews());
			psmt.setInt(12, newmember.getMember_sex());
			psmt.setInt(13, newmember.getMember_lock_status());
			psmt.setBytes(14, newmember.getMember_pic());
			psmt.setString(15,newmember.getMember_nickname());
			int num = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void update(MemberVO selectedmem) {
		try {
			psmt = con.prepareStatement(UPDATE);
			
			psmt.setString(1, selectedmem.getMember_account());
			psmt.setString(2, selectedmem.getMember_psw());
			psmt.setString(3, selectedmem.getMember_lastname());
			psmt.setString(4, selectedmem.getMember_firstname());
			psmt.setString(5, selectedmem.getMember_address());
			psmt.setString(6, selectedmem.getMobilenum());
			psmt.setString(7, selectedmem.getMember_emailaddress());
			psmt.setDate(8, selectedmem.getMember_birthday());
			psmt.setString(9, selectedmem.getMember_idcode());
			psmt.setString(10, selectedmem.getCreaditcard());
			psmt.setInt(11, selectedmem.getSubsenews());
			psmt.setInt(12, selectedmem.getMember_sex());
			psmt.setInt(13, selectedmem.getMember_lock_status());
			psmt.setBytes(14, selectedmem.getMember_pic());
			psmt.setString(15,selectedmem.getMember_nickname());
			psmt.setInt(16, selectedmem.getMember_id());
			int num = psmt.executeUpdate();
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void delete(Integer member_id) {
		try {
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, member_id);
			psmt.executeUpdate();
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public MemberVO findByPK(Integer member_id) {
		MemberVO member = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, member_id);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				member = new MemberVO();
				member.setMember_id(rs.getInt(1));
				member.setMember_account(rs.getString(2));
				member.setMember_psw(rs.getString(3));
				member.setMember_lastname(rs.getString(4));
				member.setMember_firstname(rs.getString(5));
				member.setMember_address(rs.getString(6));
				member.setMobilenum(rs.getString(7));
				member.setMember_emailaddress(rs.getString(8));
				member.setMember_birthday(rs.getDate(9));
				member.setMember_idcode(rs.getString(10));
				member.setCreaditcard(rs.getString(11));
				member.setSubsenews(rs.getInt(12));
				member.setMember_sex(rs.getInt(13));
				member.setMember_lock_status(rs.getInt(14));
				member.setMember_pic(rs.getBytes(15));
				member.setMember_nickname(rs.getString(16));
			}		
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return member;
	}


	@Override
	public List<MemberVO> getAll() {
		MemberVO member = null;
		ResultSet rs = null;
		List<MemberVO> memList = new ArrayList<>();

		try {
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();
			while(rs.next()){
				member = new MemberVO();
				member.setMember_id(rs.getInt(1));
				member.setMember_account(rs.getString(2));
				member.setMember_psw(rs.getString(3));
				member.setMember_lastname(rs.getString(4));
				member.setMember_firstname(rs.getString(5));
				member.setMember_address(rs.getString(6));
				member.setMobilenum(rs.getString(7));
				member.setMember_emailaddress(rs.getString(8));
				member.setMember_birthday(rs.getDate(9));
				member.setMember_idcode(rs.getString(10));
				member.setCreaditcard(rs.getString(11));
				member.setSubsenews(rs.getInt(12));
				member.setMember_sex(rs.getInt(13));
				member.setMember_lock_status(rs.getInt(14));
				member.setMember_pic(rs.getBytes(15));
				member.setMember_nickname(rs.getString(16));
				memList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(psmt != null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memList;
	}
}
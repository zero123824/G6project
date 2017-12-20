package com.member.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//測試 SQL指令 IN javaSE
public class MemberTest {
	private static final String INSERT = "INSERT INTO MEMBER_INFO (MEMBER_ID, MEMBER_ACCOUNT, MEMBER_PSW, MEMBER_LASTNAME, MEMBER_FIRSTNAME, MEMBER_ADDRESS, MOBILENUM, MEMBER_EMAILADDRESS, MEMBER_BIRTHDAY, MEMBER_IDCODE, CREADITCARD, SUBSENEWS, MEMBER_SEX, MEMBER_LOCK_STATUS, MEMBER_PIC, MEMBER_NICKNAME)"
			+ "VALUES ('1'||LPAD(MEMBER_SEQUENCE.NEXTVAL,9,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE MEMBER_INFO SET MEMBER_ACCOUNT = ? ,MEMBER_PSW = ?,MEMBER_LASTNAME = ?,"
			+ "MEMBER_FIRSTNAME = ?,MEMBER_ADDRESS=?,MOBILENUM=?,MEMBER_EMAILADDRESS=?,"
			+ "MEMBER_BIRTHDAY=?,MEMBER_IDCODE=?,CREADITCARD=?,SUBSENEWS=?,MEMBER_SEX=?,"
			+ "MEMBER_LOCK_STATUS=?,MEMBER_PIC=?,MEMBER_NICKNAME=? WHERE MEMBER_ID = ? ";
	private static final String DELETE = "DELETE FROM MEMBER_INFO WHERE MEMBER_ID = ? ";
	private static final String SELECT = "SELECT * FROM MEMBER_INFO WHERE MEMBER_ID = ? ";
	private static final String SELECTBYACCOUNT = "SELECT * FROM MEMBER_INFO WHERE MEMBER_ACCOUNT = ? ";
	private static final String GETALL = "SELECT * FROM MEMBER_INFO";
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static final String PICUPDATE = "UPDATE MEMBER_INFO SET MEMBER_PIC =? where MEMBER_ID = ? ";

	public static void main(String[] args) {
		MemberVO newmember = new MemberVO();
		Date today = new Date();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ba105g6", "ba105g6");
			psmt = con.prepareStatement(PICUPDATE);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("success");
		newmember.setMember_account("abctest").setMember_psw("123455").setMember_lastname("李").setMember_firstname("大同")
				.setMember_address("中山北路789號").setMobilenum("0987654321").setMember_emailaddress("abctest@gmail.com")
				.setMember_birthday(new java.sql.Date(today.getTime())).setMember_idcode("A123456789")
				.setCreaditcard("123156456451561").setSubsenews(1).setMember_sex(2).setMember_lock_status(1)
				.setMember_pic(new byte[5]).setMember_nickname("yoyo");
		// add(newmember);
		// newmember.setMember_id(1000000011);
		// update(newmember);
		// MemberVO mem2 = findByPK(1000000002);
		// List list = getAll();
		// Iterator it = list.iterator();
		// while (it.hasNext()) {
		// MemberVO mem = (MemberVO) it.next();
		// System.out.println(mem.getMember_account());
		// System.out.println(mem.getMember_account());
		// System.out.println(mem.getMember_psw());
		// System.out.println(mem.getMember_lastname());
		// System.out.println(mem.getMember_firstname());
		// System.out.println(mem.getMember_address());
		// System.out.println(mem.getMobilenum());
		// System.out.println(mem.getMember_emailaddress());
		// System.out.println(mem.getMember_birthday());
		// System.out.println(mem.getMember_idcode());
		// System.out.println(mem.getCreaditcard());
		// System.out.println(mem.getSubsenews());
		// System.out.println(mem.getMember_sex());
		// System.out.println(mem.getMember_lock_status());
		// System.out.println(mem.getMember_pic());
		// System.out.println(mem.getMember_nickname());
		// }
		
		// 修改假圖片進入資料庫 JDBCDAO
		byte[] pic = getPictureByteArray("C:\\Users\\Java\\Pictures\\nopic.jpg");

		for (int i = 1000000001; i <= 1000000014; i++) {
			try {
				psmt.setInt(2, i);
				psmt.setBytes(1, pic);
				psmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] getPictureByteArray(String file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8 * 1024];
		int i;
		try {
			while ((i = bis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			;
			baos.close();
			bis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static void add(MemberVO newmember) {
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
			psmt.setString(15, newmember.getMember_nickname());
			int num = psmt.executeUpdate();

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

	public static void update(MemberVO selectedmem) {
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
			psmt.setString(15, selectedmem.getMember_nickname());
			psmt.setInt(16, selectedmem.getMember_id());
			int num = psmt.executeUpdate();

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

	public void delete(Integer member_id) {
		try {
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, member_id);
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

	public static MemberVO findByPK(Integer member_id) {
		MemberVO member = null;
		try {
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, member_id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
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
		return member;
	}

	public static List<MemberVO> getAll() {
		MemberVO member = null;
		List<MemberVO> memList = new ArrayList<>();

		try {
			psmt = con.prepareStatement(GETALL);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
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
		return memList;
	}
	public MemberVO findByAccount(String member_account) {
		MemberVO member = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(SELECTBYACCOUNT);
			psmt.setString(1, member_account);
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
}

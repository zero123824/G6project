package com.announcement.model;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnnounceTest {
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static final String INSERT = "INSERT INTO ANNOUNCEMENT (ANOUNCE_ID, ANOUNCE_TITLE, ANOUNCE_CONTENT, ANOUNCE_TIME ,EMPNO,ANOUNCE_STATUS) "
			+ "VALUES('7'||LPAD(ANNOUNCEMENT_SEQUENCE.NEXTVAL,4,'0'),?,?,?,?,?)";
	private static final String UPDATE = "UPDATE ANNOUNCEMENT SET ANOUNCE_TITLE=?,ANOUNCE_CONTENT=?,ANOUNCE_TIME=?,EMPNO=?,ANOUNCE_STATUS=? "
			+ "WHERE ANOUNCE_ID = ?";
	private static final String DELETE = "DELETE FROM ANNOUNCEMENT WHERE ANOUNCE_ID = ?";
	private static final String SELECT = "SELECT * FROM ANNOUNCEMENT WHERE ANOUNCE_ID = ?";
	private static final String GETALL = "SELECT * FROM ANNOUNCEMENT ";

	public static void main(String[] args) {
		AnnouncementVO newannounce = new AnnouncementVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ba105g6", "ba105g6");
			System.out.println("success");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		newannounce.setAnnounce_title("ninhao")
		.setAnnounce_content("asdasdkoam")
		.setAnnounce_time(new Timestamp(1616145655511L))
		.setEmpno(13005).setAnnounce_id(70005)
		.setAnnounce_status(1);
//		add(newannounce);
//		update(newannounce);
//		delete(70005);
//		AnnouncementVO emp = findByPK(70006);
		List list = getAll();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			AnnouncementVO emp = (AnnouncementVO)it.next();
			System.out.println(emp.getAnnounce_title());
			System.out.println(emp.getAnnounce_content());
			System.out.println(emp.getAnnounce_id());
			System.out.println(emp.getAnnounce_status());
			System.out.println(emp.getAnnounce_time());
			System.out.println(emp.getEmpno());
		}
	}
	public static void add(AnnouncementVO newannouncement) {
		try {
			Reader reader = getLongStringStream(newannouncement.getAnnounce_content());
			
			psmt = con.prepareStatement(INSERT);
			psmt.setString(1, newannouncement.getAnnounce_title());
			psmt.setCharacterStream(2, reader);
			psmt.setTimestamp(3, newannouncement.getAnnounce_time());
			psmt.setInt(4, newannouncement.getEmpno());
			psmt.setInt(5, newannouncement.getAnnounce_status());
			psmt.executeUpdate();

		} catch (SQLException | IOException e) {
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
	
	public static void update(AnnouncementVO selectedannoun) {
		try {
			Reader reader = getLongStringStream(selectedannoun.getAnnounce_content());

			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, selectedannoun.getAnnounce_title());
			psmt.setCharacterStream(2, reader);
			psmt.setTimestamp(3, selectedannoun.getAnnounce_time());
			psmt.setInt(4, selectedannoun.getEmpno());
			psmt.setInt(5, selectedannoun.getAnnounce_status());
			psmt.setInt(6, selectedannoun.getAnnounce_id());
			psmt.executeUpdate();
		} catch (SQLException | IOException e) {
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

	public static void delete(Integer announce_id) {
		try {
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, announce_id);
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

	public static AnnouncementVO findByPK(Integer announce_id) {
		AnnouncementVO announce = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, announce_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				announce = new AnnouncementVO();
				announce.setAnnounce_id(rs.getInt(1));
				announce.setAnnounce_title(rs.getString(2));
				announce.setAnnounce_content(rs.getString(3));
				announce.setAnnounce_time(rs.getTimestamp(4));
				announce.setEmpno(rs.getInt(5));
				announce.setAnnounce_status(rs.getInt(6));			
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
		return announce;
	}

	public static List<AnnouncementVO> getAll() {
		AnnouncementVO announce = null;
		ResultSet rs = null;
		List<AnnouncementVO> announceList = new ArrayList<>();

		try {
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				announce = new AnnouncementVO();
				announce.setAnnounce_id(rs.getInt(1));
				announce.setAnnounce_title(rs.getString(2));
				announce.setAnnounce_content(rs.getString(3));
				announce.setAnnounce_time(rs.getTimestamp(4));
				announce.setEmpno(rs.getInt(5));
				announce.setAnnounce_status(rs.getInt(6));
				announceList.add(announce);
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
		return announceList;
	}
	public static Reader getLongStringStream(String content) throws IOException {
		return new StringReader(content);
	}
}

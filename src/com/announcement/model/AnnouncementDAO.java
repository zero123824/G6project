package com.announcement.model;

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

public class AnnouncementDAO implements AnnouncementDAO_interface {
	private static final String INSERT = "INSERT INTO ANNOUNCEMENT (ANOUNCE_ID, ANOUNCE_TITLE, ANOUNCE_CONTENT, ANOUNCE_TIME, EMPNO,ANOUNCE_STATUS) "
										 +"VALUES ('7'||LPAD(ANNOUNCEMENT_SEQUENCE.NEXTVAL,4,'0'),?,?,?,?,?)";
	private static final String UPDATE = "UPDATE ANNOUNCEMENT SET ANOUNCE_TITLE=?,ANOUNCE_CONTENT=?,ANOUNCE_TIME=?,EMPNO=?,ANOUNCE_STATUS=? "
										 +"WHERE ANOUNCE_ID = ?";
	private static final String DELETE = "DELETE FROM ANNOUNCEMENT WHERE ANOUNCE_ID = ?";
	private static final String SELECT = "SELECT * FROM ANNOUNCEMENT WHERE ANOUNCE_ID = ?";
	private static final String GETALL = "SELECT * FROM ANNOUNCEMENT ORDER BY ANOUNCE_TIME DESC ";

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
	
	@Override
	public void add(AnnouncementVO newannouncement) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			Reader reader = new StringReader(newannouncement.getAnnounce_content());
			psmt = con.prepareStatement(INSERT);
			psmt.setString(1, newannouncement.getAnnounce_title());
			psmt.setCharacterStream(2, reader);
			psmt.setTimestamp(3, newannouncement.getAnnounce_time());
			psmt.setInt(4, newannouncement.getEmpno());
			psmt.setInt(5, newannouncement.getAnnounce_status());
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
	public void update(AnnouncementVO selectedannoun) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, selectedannoun.getAnnounce_title());
			psmt.setString(2, selectedannoun.getAnnounce_content());
			psmt.setTimestamp(3, selectedannoun.getAnnounce_time());
			psmt.setInt(4, selectedannoun.getEmpno());
			psmt.setInt(5, selectedannoun.getAnnounce_status());
			psmt.setInt(6, selectedannoun.getAnnounce_id());
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
	public void delete(Integer announce_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
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

	@Override
	public AnnouncementVO findByPK(Integer announce_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		AnnouncementVO announce = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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

	@Override
	public List<AnnouncementVO> getAll() {
		Connection con = null;
		PreparedStatement psmt = null;
		AnnouncementVO announce = null;
		ResultSet rs = null;
		List<AnnouncementVO> announceList = new ArrayList<>();
		try {
			con = ds.getConnection();
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
}

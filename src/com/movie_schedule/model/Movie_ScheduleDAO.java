package com.movie_schedule.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Movie_ScheduleDAO implements Movie_ScheduleDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO movie_schedule (schedule_id,movie_id,hall_id,schedule_date,sold_seat,seat_stat,midnight) VALUES (movie_schedule_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT schedule_id,movie_id,hall_id,to_char(schedule_date,'yyyy-mm-dd hh:mi:ss')schedule_date,sold_seat,seat_stat,midnight FROM movie_schedule order by schedule_id";
	private static final String GET_ONE_STMT = 
		"SELECT schedule_id,movie_id,hall_id,to_char(schedule_date,'yyyy-mm-dd hh:mi:ss')schedule_date,sold_seat,seat_stat,midnight FROM movie_schedule where schedule_id = ?";
	private static final String DELETE = 
		"DELETE FROM movie_schedule where schedule_id = ?";
	private static final String UPDATE = 
		"UPDATE movie_schedule set movie_id=?, hall_id=?, schedule_date=?, sold_seat=?, seat_stat=?, midnight=? where schedule_id = ?";

	@Override
	public void insert(Movie_ScheduleVO movie_scheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setLong(1, movie_scheduleVO.getMovie_id());
			pstmt.setLong(2, movie_scheduleVO.getHall_id());
			pstmt.setTimestamp(3, movie_scheduleVO.getSchedule_date());
			pstmt.setInt(4, movie_scheduleVO.getSold_seat());
			pstmt.setString(5, movie_scheduleVO.getSeat_stat());
			pstmt.setBoolean(6, movie_scheduleVO.getMidnight());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(Movie_ScheduleVO movie_scheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setLong(1, movie_scheduleVO.getMovie_id());
			pstmt.setLong(2, movie_scheduleVO.getHall_id());
			pstmt.setTimestamp(3, movie_scheduleVO.getSchedule_date());
			pstmt.setInt(4, movie_scheduleVO.getSold_seat());
			pstmt.setString(5, movie_scheduleVO.getSeat_stat());
			pstmt.setBoolean(6, movie_scheduleVO.getMidnight());
			pstmt.setLong(7, movie_scheduleVO.getSchedule_id());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}
	
	@Override
	public void delete(Long schedule_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setLong(1, schedule_id);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}
	
	@Override
	public Movie_ScheduleVO findByPrimaryKey(Long schedule_id) {
		Movie_ScheduleVO movie_scheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setLong(1, schedule_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_scheduleVO = new Movie_ScheduleVO();
				movie_scheduleVO.setSchedule_id(rs.getLong("schedule_id"));
				movie_scheduleVO.setMovie_id(rs.getLong("movie_id"));
				movie_scheduleVO.setHall_id(rs.getInt("hall_id"));
				movie_scheduleVO.setSchedule_date(rs.getTimestamp("schedule_date"));
				movie_scheduleVO.setSold_seat(rs.getInt("sold_seat"));
				movie_scheduleVO.setSeat_stat(rs.getString("seat_stat"));
				movie_scheduleVO.setMidnight(rs.getBoolean("midnight"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return movie_scheduleVO;
	}
	@Override
	public List<Movie_ScheduleVO> getAll() {
		List<Movie_ScheduleVO> list = new ArrayList<Movie_ScheduleVO>();
		Movie_ScheduleVO movie_scheduleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_scheduleVO = new Movie_ScheduleVO();
				movie_scheduleVO = new Movie_ScheduleVO();
				movie_scheduleVO.setSchedule_id(rs.getLong("schedule_id"));
				movie_scheduleVO.setMovie_id(rs.getLong("movie_id"));
				movie_scheduleVO.setHall_id(rs.getInt("hall_id"));
				movie_scheduleVO.setSchedule_date(rs.getTimestamp("schedule_date"));
				movie_scheduleVO.setSold_seat(rs.getInt("sold_seat"));
				movie_scheduleVO.setSeat_stat(rs.getString("seat_stat"));
				movie_scheduleVO.setMidnight(rs.getBoolean("midnight"));
				list.add(movie_scheduleVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}

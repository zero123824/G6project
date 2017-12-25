package com.movie_schedule.model;

import java.util.*;
import java.sql.*;

public class Movie_ScheduleJDBCDAO implements Movie_ScheduleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.211.55.4:1521:XE";
	String userid = "ba105g6";
	String passwd = "ba105g6";

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
		PreparedStatement pstmt =null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setLong(1, movie_scheduleVO.getMovie_id());
			pstmt.setLong(2, movie_scheduleVO.getHall_id());
			pstmt.setTimestamp(3, movie_scheduleVO.getSchedule_date());
			pstmt.setInt(4, movie_scheduleVO.getSold_seat());
			pstmt.setString(5, movie_scheduleVO.getSeat_stat());
			pstmt.setBoolean(6, movie_scheduleVO.getMidnight());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setLong(1, movie_scheduleVO.getMovie_id());
			pstmt.setLong(2, movie_scheduleVO.getHall_id());
			pstmt.setTimestamp(3, movie_scheduleVO.getSchedule_date());
			pstmt.setInt(4, movie_scheduleVO.getSold_seat());
			pstmt.setString(5, movie_scheduleVO.getSeat_stat());
			pstmt.setBoolean(6, movie_scheduleVO.getMidnight());
			pstmt.setLong(7, movie_scheduleVO.getSchedule_id());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setLong(1, schedule_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	public static void main(String[] args) {
		
		Movie_ScheduleJDBCDAO dao = new Movie_ScheduleJDBCDAO();
		
		//新增
		Movie_ScheduleVO movie_scheduleVO1 = new Movie_ScheduleVO();
		movie_scheduleVO1.setMovie_id(4000000006L);
		movie_scheduleVO1.setHall_id(1);
		movie_scheduleVO1.setSchedule_date(java.sql.Timestamp.valueOf("2017-12-22 17:00:00"));
		movie_scheduleVO1.setSold_seat(0);
		movie_scheduleVO1.setSeat_stat("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		movie_scheduleVO1.setMidnight(false);
		dao.insert(movie_scheduleVO1);
		System.out.println("新增成功");
		
		//修改
		Movie_ScheduleVO movie_scheduleVO2 = new Movie_ScheduleVO();
		movie_scheduleVO2.setSchedule_id(6000000112L);
		movie_scheduleVO2.setMovie_id(4000000006L);
		movie_scheduleVO2.setHall_id(3);
		movie_scheduleVO2.setSchedule_date(java.sql.Timestamp.valueOf("2017-12-22 01:00:00"));
		movie_scheduleVO2.setSold_seat(10);
		movie_scheduleVO2.setSeat_stat("111111111100000000000000000000000000000000000000000000000000");
		movie_scheduleVO2.setMidnight(true);
		dao.update(movie_scheduleVO2);
		System.out.println("修改成功");
		
		//刪除
		dao.delete(6000000111L);
		System.out.println("刪除成功");
		
		//查詢
		Movie_ScheduleVO movie_scheduleVO3 = dao.findByPrimaryKey(6000000112L);
		System.out.print(movie_scheduleVO3.getSchedule_id() + ",");
		System.out.print(movie_scheduleVO3.getMovie_id() + ",");
		System.out.print(movie_scheduleVO3.getHall_id() + ",");
		System.out.print(movie_scheduleVO3.getSchedule_date() + ",");
		System.out.print(movie_scheduleVO3.getSold_seat() + ",");
		System.out.print(movie_scheduleVO3.getSeat_stat() + ",");
		System.out.println(movie_scheduleVO3.getMidnight());
		System.out.println("---------------------");
		
		//查詢
		List<Movie_ScheduleVO> list = dao.getAll();
		for (Movie_ScheduleVO amovie_schedule : list) {
			System.out.print(amovie_schedule.getSchedule_id() + ",");
			System.out.print(amovie_schedule.getMovie_id() + ",");
			System.out.print(amovie_schedule.getHall_id() + ",");
			System.out.print(amovie_schedule.getSchedule_date() + ",");
			System.out.print(amovie_schedule.getSold_seat() + ",");
			System.out.print(amovie_schedule.getSeat_stat() + ",");
			System.out.print(amovie_schedule.getMidnight());
			System.out.println();
		}
	}
}

package com.movie.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.movie_comment.model.Movie_CommentVO;
import com.movie_schedule.model.Movie_ScheduleVO;

public class MovieDAO implements MovieDAO_interface {

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
		"INSERT INTO MOVIE (movie_id,movie_name_ch,movie_name_en,movie_poster,movie_rating,movie_date,movie_time,movie_company,movie_director,movie_actor,movie_introduce,movie_url,movie_stat) VALUES (MOVIE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT movie_id,movie_name_ch,movie_name_en,movie_poster,movie_rating,to_char(movie_date,'yyyy-mm-dd') movie_date,movie_time,movie_company,movie_director,movie_actor,movie_introduce,movie_url,movie_stat FROM movie order by movie_id";
	private static final String GET_ONE_STMT = 
		"SELECT movie_id,movie_name_ch,movie_name_en,movie_poster,movie_rating,to_char(movie_date,'yyyy-mm-dd') movie_date,movie_time,movie_company,movie_director,movie_actor,movie_introduce,movie_url,movie_stat FROM movie where movie_id = ?";
	private static final String DELETE = 
		"DELETE FROM movie where movie_id = ?";
	private static final String UPDATE = 
		"UPDATE movie set movie_name_ch=?, movie_name_en=?, movie_poster=?, movie_rating=?, movie_date=?, movie_time=?, movie_company=?, movie_director=?, movie_actor=?, movie_introduce=?, movie_url=?, movie_stat=? where movie_id = ?";
	private static final String GET_SCHEDULE_BYMOVIE_STMT = 
		"SELECT schedule_id,movie_id,hall_id,to_char(schedule_date,'yyyy-mm-dd hh:mi:ss')schedule_date,sold_seat,seat_stat,midnight FROM movie_schedule where movie_id = ? order by schedule_id";
	private static final String GET_COMMENT_BYMOVIE_STMT = 
			"SELECT comment_id,movie_id,member_id,to_char(comment_time,'yyyy-mm-dd hh24:mi:ss')comment_time,comment_content,comment_stat FROM movie_comment where movie_id = ? order by comment_id";
	
	@Override
	public void insert(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movieVO.getMovie_name_ch());
			pstmt.setString(2, movieVO.getMovie_name_en());
			pstmt.setBytes(3, movieVO.getMovie_poster());
			pstmt.setString(4, movieVO.getMovie_rating());
			pstmt.setDate(5, movieVO.getMovie_date());
			pstmt.setInt(6, movieVO.getMovie_time());
			pstmt.setString(7, movieVO.getMovie_company());
			pstmt.setString(8, movieVO.getMovie_director());
			pstmt.setString(9, movieVO.getMovie_actor());
			pstmt.setString(10, movieVO.getMovie_introduce());
			pstmt.setString(11, movieVO.getMovie_url());
			pstmt.setBoolean(12, movieVO.getMovie_stat());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movieVO.getMovie_name_ch());
			pstmt.setString(2, movieVO.getMovie_name_en());
			pstmt.setBytes(3, movieVO.getMovie_poster());
			pstmt.setString(4, movieVO.getMovie_rating());
			pstmt.setDate(5, movieVO.getMovie_date());
			pstmt.setInt(6, movieVO.getMovie_time());
			pstmt.setString(7, movieVO.getMovie_company());
			pstmt.setString(8, movieVO.getMovie_director());
			pstmt.setString(9, movieVO.getMovie_actor());
			pstmt.setString(10, movieVO.getMovie_introduce());
			pstmt.setString(11, movieVO.getMovie_url());
			pstmt.setBoolean(12, movieVO.getMovie_stat());
			pstmt.setLong(13, movieVO.getMovie_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Long movie_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setLong(1, movie_id);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MovieVO findByPrimaryKey(Long movie_id) {
		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setLong(1, movie_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getLong("movie_id"));
				movieVO.setMovie_name_ch(rs.getString("movie_name_ch"));
				movieVO.setMovie_name_en(rs.getString("movie_name_en"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_rating(rs.getString("movie_rating"));
				movieVO.setMovie_date(rs.getDate("movie_date"));
				movieVO.setMovie_time(rs.getInt("movie_time"));
				movieVO.setMovie_company(rs.getString("movie_company"));
				movieVO.setMovie_director(rs.getString("movie_director"));
				movieVO.setMovie_actor(rs.getString("movie_actor"));
				movieVO.setMovie_introduce(rs.getString("movie_introduce"));
				movieVO.setMovie_url(rs.getString("movie_url"));
				movieVO.setMovie_stat(rs.getBoolean("movie_stat"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return movieVO;
	}

	@Override
	public List<MovieVO> getAll() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getLong("movie_id"));
				movieVO.setMovie_name_ch(rs.getString("movie_name_ch"));
				movieVO.setMovie_name_en(rs.getString("movie_name_en"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_rating(rs.getString("movie_rating"));
				movieVO.setMovie_date(rs.getDate("movie_date"));
				movieVO.setMovie_time(rs.getInt("movie_time"));
				movieVO.setMovie_company(rs.getString("movie_company"));
				movieVO.setMovie_director(rs.getString("movie_director"));
				movieVO.setMovie_actor(rs.getString("movie_actor"));
				movieVO.setMovie_introduce(rs.getString("movie_introduc"));
				movieVO.setMovie_url(rs.getString("movie_url"));
				movieVO.setMovie_stat(rs.getBoolean("movie_stat"));
				list.add(movieVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public Set<Movie_ScheduleVO> getScheduleByMovie(Long movie_id) {
		Set<Movie_ScheduleVO> set = new LinkedHashSet<Movie_ScheduleVO>();
		Movie_ScheduleVO movie_scheduleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SCHEDULE_BYMOVIE_STMT);
			
			pstmt.setLong(1, movie_id);
			
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
				set.add(movie_scheduleVO);
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	@Override
	public Set<Movie_CommentVO> getCommentByMovie(Long movie_id) {
		Set<Movie_CommentVO> set = new LinkedHashSet<Movie_CommentVO>();
		Movie_CommentVO movie_commentVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COMMENT_BYMOVIE_STMT);
			pstmt.setLong(1, movie_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				movie_commentVO = new Movie_CommentVO();
				movie_commentVO.setComment_id(rs.getLong("comment_id"));
				movie_commentVO.setMovie_id(rs.getLong("movie_id"));
				movie_commentVO.setMember_id(rs.getLong("membere_id"));
				movie_commentVO.setComment_time(rs.getTimestamp("comment_time"));
				movie_commentVO.setComment_content(rs.getString("comment_content"));
				movie_commentVO.setComment_stat(rs.getBoolean("comment_stat"));
				set.add(movie_commentVO);
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}

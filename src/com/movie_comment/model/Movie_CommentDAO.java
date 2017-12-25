package com.movie_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Movie_CommentDAO implements Movie_CommentDAO_interface {

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
		"INSERT INTO movie_comment (comment_id,movie_id,member_id,comment_time,comment_content,comment_stat) VALUES (movie_comment_seq.NEXTVAL, ?, ?, CURRENT_TIMESTAMP, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT comment_id,movie_id,member_id,to_char(comment_time,'yyyy-mm-dd hh24:mi:ss')comment_time,comment_content,comment_stat FROM movie_comment order by comment_id";
	private static final String GET_ONE_STMT = 
		"SELECT comment_id,movie_id,member_id,to_char(comment_time,'yyyy-mm-dd hh24:mi:ss')comment_time,comment_content,comment_stat FROM movie_comment where comment_id = ?";
	private static final String DELETE = 
		"DELETE FROM movie_comment where comment_id = ?";
	private static final String UPDATE = 
		"UPDATE movie_comment set comment_content=? ,comment_stat=? where comment_id = ?";
		
	@Override
	public void insert(Movie_CommentVO movie_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setLong(1, movie_commentVO.getMovie_id());
			pstmt.setLong(2, movie_commentVO.getMember_id());
			pstmt.setString(3, movie_commentVO.getComment_content());
			pstmt.setBoolean(4, movie_commentVO.getComment_stat());
			
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
	public void update(Movie_CommentVO movie_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, movie_commentVO.getComment_content());
			pstmt.setBoolean(2, movie_commentVO.getComment_stat());
			pstmt.setLong(3, movie_commentVO.getComment_id());
			
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
	public void delete(Long comment_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setLong(1, comment_id);
			
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
	public Movie_CommentVO findByPrimaryKey(Long comment_id) {
		Movie_CommentVO movie_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setLong(1, comment_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				movie_commentVO = new Movie_CommentVO();
				movie_commentVO.setComment_id(rs.getLong("comment_id"));
				movie_commentVO.setMovie_id(rs.getLong("movie_id"));
				movie_commentVO.setMember_id(rs.getLong("member_id"));
				movie_commentVO.setComment_time(rs.getTimestamp("comment_time"));
				movie_commentVO.setComment_content(rs.getString("comment_content"));
				movie_commentVO.setComment_stat(rs.getBoolean("comment_stat"));
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
		return movie_commentVO;
	}

	@Override
	public List<Movie_CommentVO> getAll() {
		List<Movie_CommentVO> list = new ArrayList<Movie_CommentVO>();
		Movie_CommentVO movie_commentVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				movie_commentVO = new Movie_CommentVO();
				movie_commentVO.setComment_id(rs.getLong("comment_id"));
				movie_commentVO.setMovie_id(rs.getLong("movie_id"));
				movie_commentVO.setMember_id(rs.getLong("member_id"));
				movie_commentVO.setComment_time(rs.getTimestamp("comment_time"));
				movie_commentVO.setComment_content(rs.getString("comment_content"));
				movie_commentVO.setComment_stat(rs.getBoolean("comment_stat"));
				list.add(movie_commentVO);
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

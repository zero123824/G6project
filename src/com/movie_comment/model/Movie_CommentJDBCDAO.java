package com.movie_comment.model;

import java.util.*;
import java.sql.*;

public class Movie_CommentJDBCDAO implements Movie_CommentDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.211.55.4:1521:XE";
	String userid = "ba105g6";
	String passwd = "ba105g6";

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
		PreparedStatement pstmt =null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setLong(1, movie_commentVO.getMovie_id());
			pstmt.setLong(2, movie_commentVO.getMember_id());
			pstmt.setString(3, movie_commentVO.getComment_content());
			pstmt.setBoolean(4, movie_commentVO.getComment_stat());
			
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
	public void update(Movie_CommentVO movie_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, movie_commentVO.getComment_content());
			pstmt.setBoolean(2, movie_commentVO.getComment_stat());
			pstmt.setLong(3, movie_commentVO.getComment_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setLong(1, comment_id);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		Movie_CommentJDBCDAO dao = new Movie_CommentJDBCDAO();
		
		//新增
		Movie_CommentVO movie_commentVO1 = new Movie_CommentVO();
		movie_commentVO1.setMovie_id(4000000006L);
		movie_commentVO1.setMember_id(1000000003L);
		movie_commentVO1.setComment_content("背負著第七集票房大賣20億美金的正宗續作終於上映，可惜這次表現差強人意，想要超展開可惜後繼無力，差點葬送原力覺醒帶來的完美開場及光環，本作我只願意給70分，我能理解本作不按牌理，故意唱反調的劇情，是為了讓觀眾更有驚喜感，但編劇自作主張… 安排多餘的設定，真的讓粉絲看了非常出戲！");
		movie_commentVO1.setComment_stat(true);
		dao.insert(movie_commentVO1);
		System.out.println("新增成功");
		
		//修改
		Movie_CommentVO movie_commentVO2 = new Movie_CommentVO();
		movie_commentVO2.setComment_id(8000000001L);
		movie_commentVO2.setComment_content("修改測試用內容");
		movie_commentVO2.setComment_stat(true);
		dao.update(movie_commentVO2);
		System.out.println("修改成功");
		
		//刪除
		dao.delete(8000000002L);
		System.out.println("刪除成功");
		
		// 查詢
		Movie_CommentVO movie_commentVO3 = dao.findByPrimaryKey(8000000010L);
		System.out.print(movie_commentVO3.getComment_id() + ",");
		System.out.print(movie_commentVO3.getMovie_id() + ",");
		System.out.print(movie_commentVO3.getMember_id() + ",");
		System.out.print(movie_commentVO3.getComment_time() + ",");
		System.out.print(movie_commentVO3.getComment_content() + ",");
		System.out.println(movie_commentVO3.getComment_stat());
		System.out.println("---------------------");
		
		//查詢
		List<Movie_CommentVO> list = dao.getAll();
		for (Movie_CommentVO aMovie_Comment : list) {
			System.out.print(aMovie_Comment.getComment_id() + ",");
			System.out.print(aMovie_Comment.getMovie_id() + ",");
			System.out.print(aMovie_Comment.getMember_id() + ",");
			System.out.print(aMovie_Comment.getComment_time() + ",");
			System.out.print(aMovie_Comment.getComment_content() + ",");
			System.out.print(aMovie_Comment.getComment_stat());
			System.out.println();
		}
	}

}

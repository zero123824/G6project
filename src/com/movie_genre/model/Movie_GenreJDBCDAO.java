package com.movie_genre.model;

import java.util.*;
import java.sql.*;

public class Movie_GenreJDBCDAO implements Movie_GenreDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.211.55.4:1521:XE";
	String userid = "ba105g6";
	String passwd = "ba105g6";
	
	private static final String INSERT_STMT = 
		"INSERT INTO movie_genre (movie_id,genre_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT movie_id,genre_id FROM movie_genre order by genre_id";
	private static final String GET_MOVEI_BYGENRE_STMT = 
		"SELECT movie_id,genre_id FROM movie_genre where genre_id = ?";
	private static final String DELETE = 
		"DELETE FROM movie_genre where movie_id = ? and genre_id = ?";
	private static final String UPDATE = 
		"UPDATE movie_genre set genre_id=? where movie_id = ?";
	
	@Override
	public void insert(Movie_GenreVO movie_genreVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setLong(1, movie_genreVO.getMovie_id());
			pstmt.setInt(2, movie_genreVO.getGenre_id());

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
	public void update(Movie_GenreVO movie_genreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, movie_genreVO.getGenre_id());
			pstmt.setLong(2, movie_genreVO.getMovie_id());

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
	public void delete(Long movie_id, Integer genre_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setLong(1, movie_id);
			pstmt.setInt(2, genre_id);

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
	public Set<Long> getMovieByGenre(Integer genre_id) {
		Set<Long> set = new LinkedHashSet<Long>();
		Movie_GenreVO movie_genreVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MOVEI_BYGENRE_STMT);
			
			pstmt.setInt(1, genre_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_genreVO = new Movie_GenreVO();
				movie_genreVO.setMovie_id(rs.getLong("movie_id"));
				movie_genreVO.setGenre_id(rs.getInt("genre_id"));
				set.add(movie_genreVO.getMovie_id());
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return set;
	}
	
	@Override
	public List<Movie_GenreVO> getAll() {
		List<Movie_GenreVO> list = new ArrayList<Movie_GenreVO>();
		Movie_GenreVO movie_genreVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_genreVO = new Movie_GenreVO();
				movie_genreVO.setMovie_id(rs.getLong("movie_id"));
				movie_genreVO.setGenre_id(rs.getInt("genre_id"));
				list.add(movie_genreVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[] args) {
		
		Movie_GenreJDBCDAO dao = new Movie_GenreJDBCDAO();
		
		//新增
		Movie_GenreVO movie_genreVO1 = new Movie_GenreVO();
		movie_genreVO1.setMovie_id(4000000001L);
		movie_genreVO1.setGenre_id(90001);
		dao.insert(movie_genreVO1);
		System.out.println("新增成功");
		
		//刪除
		dao.delete(4000000002L, 90005);
		System.out.println("刪除成功");
		
		//查詢
		List<Movie_GenreVO> list = dao.getAll();
		for (Movie_GenreVO aMovie_Genre : list) {
			System.out.print(aMovie_Genre.getMovie_id() + ",");
			System.out.print(aMovie_Genre.getGenre_id());
			System.out.println();
		}
		System.out.println("---------------------");
		
		//查詢某類型的電影
//		Set<Movie_GenreVO> set = dao.getMovieByGenre(90005);
//		for (Movie_GenreVO aMovie_Genre : set) {
//			System.out.print(aMovie_Genre.getMovie_id() + ",");
//			System.out.print(aMovie_Genre.getGenre_id());
//			System.out.println();
//		}
	}	
}

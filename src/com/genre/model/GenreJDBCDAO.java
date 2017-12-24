package com.genre.model;

import java.util.*;
import java.sql.*;

public class GenreJDBCDAO implements GenreDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.211.55.4:1521:XE";
	String userid = "ba105g6";
	String passwd = "ba105g6";
	
	private static final String INSERT_STMT = 
		"INSERT INTO genre (genre_id,genre_name) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT genre_id,genre_name FROM genre order by genre_id";
	private static final String GET_ONE_STMT = 
		"SELECT genre_id,genre_name FROM genre where genre_id = ?";
	private static final String DELETE = 
		"DELETE FROM genre where genre_id = ?";
	private static final String UPDATE = 
		"UPDATE genre set genre_name=? where genre_id = ?";
	
	@Override
	public void insert(GenreVO genreVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, genreVO.getGenre_id());
			pstmt.setString(2, genreVO.getGenre_name());

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
	public void update(GenreVO genreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, genreVO.getGenre_name());
			pstmt.setInt(2, genreVO.getGenre_id());

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
	public void delete(Integer genre_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, genre_id);

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
	public GenreVO findByPrimaryKey(Integer genre_id) {
		GenreVO genreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, genre_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				genreVO = new GenreVO();
				genreVO.setGenre_id(rs.getInt("genre_id"));
				genreVO.setGenre_name(rs.getString("genre_name"));
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
		return genreVO;
	}
	
	@Override
	public List<GenreVO> getAll() {
		List<GenreVO> list = new ArrayList<GenreVO>();
		GenreVO genreVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				genreVO = new GenreVO();
				genreVO.setGenre_id(rs.getInt("genre_id"));
				genreVO.setGenre_name(rs.getString("genre_name"));
				list.add(genreVO);
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
		
		GenreJDBCDAO dao = new GenreJDBCDAO();
		
		//新增
		GenreVO genreVO1 = new GenreVO();
		genreVO1.setGenre_id(90020);
		genreVO1.setGenre_name("測試用類型");
		dao.insert(genreVO1);
		System.out.println("新增成功");
		
		//修改
		GenreVO genreVO2 = new GenreVO();
		genreVO2.setGenre_id(90019);
		genreVO2.setGenre_name("修改用類型");
		dao.update(genreVO2);
		System.out.println("修改成功");
		
		//查詢
		GenreVO genreVO3 = dao.findByPrimaryKey(90020);
		System.out.print(genreVO3.getGenre_id() + ",");
		System.out.println(genreVO3.getGenre_name());
		System.out.println("---------------------");

		//查詢
		List<GenreVO> list = dao.getAll();
		for (GenreVO aGenre : list) {
			System.out.print(aGenre.getGenre_id() + ",");
			System.out.print(aGenre.getGenre_name());
			System.out.println();
		}
	}
}

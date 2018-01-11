package com.genre.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GenreDAO implements GenreDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = 
		"SELECT genre_id,genre_name FROM genre order by genre_id";
	private static final String GET_ONE_STMT = 
		"SELECT genre_id,genre_name FROM genre where genre_id = ?";

	@Override
	public GenreVO findByPrimaryKey(Integer genre_id) {
		GenreVO genreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, genre_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				genreVO = new GenreVO();
				genreVO.setGenre_id(rs.getInt("genre_id"));
				genreVO.setGenre_name(rs.getString("genre_name"));
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				genreVO = new GenreVO();
				genreVO.setGenre_id(rs.getInt("genre_id"));
				genreVO.setGenre_name(rs.getString("genre_name"));
				list.add(genreVO);
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
}

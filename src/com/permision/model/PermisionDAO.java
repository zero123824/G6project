package com.permision.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PermisionDAO implements PermisionDAO_interface{
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
	private static final String INSERT = "INSERT INTO PERMISION (EMPNO,OPERATION_ID) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM PERMISION WHERE EMPNO = ? AND OPERATION_ID = ?";
	private static final String GETONEEMPPERMISION = "SELECT * FROM PERMISION WHERE EMPNO = ? ";
	@Override
	public void add(PermisionVO newmpermision) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT);
			psmt.setInt(1, newmpermision.getEmpno());
			psmt.setInt(2, newmpermision.getOperation_id());
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
	public void delete(Integer empno,Integer operation_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, empno);
			psmt.setInt(2, operation_id);
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
	public List<PermisionVO> getOneEmpPermision(Integer empno) {
		Connection con = null;
		PreparedStatement psmt = null;
		PermisionVO permision = null;
		ResultSet rs = null;
		List<PermisionVO> permisionList = new ArrayList<>();

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETONEEMPPERMISION);
			psmt.setInt(1, empno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				permision = new PermisionVO();
				permision.setEmpno(rs.getInt(1));
				permision.setOperation_id(rs.getInt(2));
				permisionList.add(permision);
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
		return permisionList;
	}
}

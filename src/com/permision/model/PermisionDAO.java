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
	private static Connection con = null;
	static {
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private PreparedStatement psmt;
	private static final String INSERT = "INSERT INTO PERMISION (EMPNO,OPERATION_ID) VALUES(?,?)";
	private static final String UPDATE = "UPDATE PERMISION SET OPERATION_ID=? WHERE EMPNO = ?";
	private static final String DELETE = "DELETE FROM PERMISION WHERE EMPNO = ?";
	private static final String SELECT = "SELECT * FROM PERMISION WHERE EMPNO = ?";
	private static final String GETALL = "SELECT * FROM PERMISION ";
	@Override
	public void add(PermisionVO newmpermision) {
		try {
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
	public void update(PermisionVO selectedpermision) {
		try {
			psmt = con.prepareStatement(UPDATE);
			psmt.setInt(1, selectedpermision.getOperation_id());
			psmt.setInt(2, selectedpermision.getEmpno());
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
	public void delete(Integer empno) {
		try {
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, empno);
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
	public PermisionVO findByPK(Integer empno) {
		PermisionVO permision = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, empno);
			rs = psmt.executeQuery();

			while (rs.next()) {
				permision = new PermisionVO();
				permision.setEmpno(rs.getInt(1));
				permision.setOperation_id(rs.getInt(2));
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
		return permision;	
	}

	@Override
	public List<PermisionVO> getAll() {
		PermisionVO permision = null;
		ResultSet rs = null;
		List<PermisionVO> permisionList = new ArrayList<>();

		try {
			psmt = con.prepareStatement(GETALL);
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

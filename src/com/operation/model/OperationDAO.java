package com.operation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class OperationDAO implements OperationDAO_interface {
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
	private static final String INSERT = "INSERT INTO OPERATION (OPERATION_ID,OPERATION_NAME) VALUES(?,?)";
	private static final String UPDATE = "UPDATE OPERATION SET OPERATION_NAME=? WHERE OPERATION_ID = ?";
	private static final String DELETE = "DELETE FROM OPERATION WHERE OPERATION_ID = ?";
	private static final String SELECT = "SELECT * FROM OPERATION WHERE OPERATION_ID = ?";
	private static final String GETALL = "SELECT * FROM OPERATION ";

	@Override
	public void add(OperationVO newmoperation) {
		try {
			psmt = con.prepareStatement(INSERT);
			psmt.setInt(1, newmoperation.getOperation_id());
			psmt.setString(2, newmoperation.getOperation_name());
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
	public void update(OperationVO selectedoperation) {
		try {
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, selectedoperation.getOperation_name());
			psmt.setInt(2, selectedoperation.getOperation_id());
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
	public void delete(Integer operation_id) {
		try {
			psmt = con.prepareStatement(DELETE);
			psmt.setInt(1, operation_id);
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
	public OperationVO findByPK(Integer operation_id) {
		OperationVO operation = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, operation_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				operation = new OperationVO();
				operation.setOperation_id(rs.getInt(1));
				operation.setOperation_name(rs.getString(2));
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
		return operation;
	}

	@Override
	public List<OperationVO> getAll() {
		OperationVO operation = null;
		ResultSet rs = null;
		List<OperationVO> opList = new ArrayList<>();

		try {
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				operation = new OperationVO();
				operation.setOperation_id(rs.getInt(1));
				operation.setOperation_name(rs.getString(2));
				opList.add(operation);
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
		return opList;
	}

}

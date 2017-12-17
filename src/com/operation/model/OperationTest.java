package com.operation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OperationTest {
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static final String INSERT = "INSERT INTO OPERATION (OPERATION_ID,OPERATION_NAME) VALUES(?,?)";
	private static final String UPDATE = "UPDATE OPERATION SET OPERATION_NAME=? WHERE OPERATION_ID = ?";
	private static final String DELETE = "DELETE FROM OPERATION WHERE OPERATION_ID = ?";
	private static final String SELECT = "SELECT * FROM OPERATION WHERE OPERATION_ID = ?";
	private static final String GETALL = "SELECT * FROM OPERATION ";

	public static void main(String[] args) {
		OperationVO newop = new OperationVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ba105g6", "ba105g6");
			System.out.println("success");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		newop.setOperation_id(15003).setOperation_name("權限管理");
		// add(newop);
		// update(newop);
		// OperationVO op = findByPK(15004);
		List list = getAll();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			OperationVO op = (OperationVO)it.next();
			System.out.println(op.getOperation_name());
			System.out.println(op.getOperation_id());
		}
	}

	public static void add(OperationVO newmoperation) {
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

	public static void update(OperationVO selectedoperation) {
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

	public static OperationVO findByPK(Integer operation_id) {
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

	public static List<OperationVO> getAll() {
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

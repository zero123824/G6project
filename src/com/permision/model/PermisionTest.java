package com.permision.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PermisionTest {
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static final String INSERT = "INSERT INTO PERMISION (EMPNO,OPERATION_ID) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM PERMISION WHERE EMPNO = ? AND OPERATION_ID = ?";
	private static final String GETONEEMPPERMISION = "SELECT * FROM PERMISION WHERE EMPNO = ? ";
	
	public static void main(String[] args) {
		PermisionVO permision = new PermisionVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ba105g6", "ba105g6");
			System.out.println("success");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		permision.setEmpno(13007).setOperation_id(15005);
//		add(permision);		
//		delete(13007,15005);
//		List list = getOneEmpPermision(13001);
//		Iterator it = list.iterator();
//		while (it.hasNext()) {
//			PermisionVO per = (PermisionVO)it.next();
//			if(per.getOperation_id() == 15006){
//				delete(per.getEmpno() ,per.getOperation_id());
//			}
//			else{
//				System.out.print(per.getEmpno()+",");
//				System.out.println(per.getOperation_id());
//			}
//		}	
	}
	
	public static void add(PermisionVO newmpermision) {
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
	public static void delete(Integer empno,Integer operation_id) {
		try {
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

	public static List<PermisionVO> getOneEmpPermision(Integer empno) {
		PermisionVO permision = null;
		ResultSet rs = null;
		List<PermisionVO> permisionList = new ArrayList<>();

		try {
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
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
		}
		return permisionList;
	}
}

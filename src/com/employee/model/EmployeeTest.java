package com.employee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EmployeeTest {
	private static final String INSERT = "INSERT INTO EMPLOYEE (EMPNO,EMP_PSW,EMP_NAME,EMP_EMAIL,EMP_HIREDATE,EMP_BIRTHDAY,EMP_ADDRESS,EMP_PHONE,EMP_SEX,LAST_ACTIVITY,INSERVICED)"
			+ "VALUES('13'||LPAD(EMPLOYEE_SEQUENCE.NEXTVAL,3,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE EMPLOYEE SET EMP_PSW=?,EMP_NAME=?,EMP_EMAIL=?,EMP_HIREDATE=?,"
			+ "EMP_BIRTHDAY=?,EMP_ADDRESS=?,EMP_PHONE=?,EMP_SEX=?,LAST_ACTIVITY=?,INSERVICED=? WHERE EMPNO = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMPNO = ?";
	private static final String SELECT = "SELECT * FROM EMPLOYEE WHERE EMPNO = ?";
	private static final String GETALL = "SELECT * FROM EMPLOYEE ";
	private static Connection con = null;
	private static PreparedStatement psmt = null;

	public static void main(String[] args) {
		EmployeeVO newemp = new EmployeeVO();
		Date today = new Date();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ba105g6", "ba105g6");
			System.out.println("success");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		newemp.setEmp_address("123").setEmp_birthday(new java.sql.Date(today.getTime())).setEmp_email("1234")
				.setEmp_hiredate(new java.sql.Date(today.getTime())).setEmp_name("abv").setEmp_phone("sdf123")
				.setEmp_psw("12345").setEmp_sex(1).setEmpno(13009).setInserviced(1);
		 add(newemp);
		// update(newemp);
//		EmployeeVO emp = findByPK(13001);
//		List list = getAll();
//		Iterator it = list.iterator();
//		while (it.hasNext()) {
//			EmployeeVO emp = (EmployeeVO)it.next();
//			System.out.println(emp.getEmp_address());
//			System.out.println(emp.getEmp_email());
//			System.out.println(emp.getEmp_name());
//			System.out.println(emp.getEmp_phone());
//			System.out.println(emp.getEmp_psw());
//			System.out.println(emp.getEmp_birthday());
//			System.out.println(emp.getEmp_hiredate());
//			System.out.println(emp.getEmp_sex());
//			System.out.println(emp.getEmpno());
//			System.out.println(emp.getInserviced());
//			System.out.println(emp.getLast_activity());
//		}

	}

	public static void add(EmployeeVO newmemp) {

		try {
			psmt = con.prepareStatement(INSERT);
			psmt.setString(1, newmemp.getEmp_psw());
			psmt.setString(2, newmemp.getEmp_name());
			psmt.setString(3, newmemp.getEmp_email());
			psmt.setDate(4, newmemp.getEmp_hiredate());
			psmt.setDate(5, newmemp.getEmp_birthday());
			psmt.setString(6, newmemp.getEmp_address());
			psmt.setString(7, newmemp.getEmp_phone());
			psmt.setInt(8, newmemp.getEmp_sex());
			psmt.setTimestamp(9, newmemp.getLast_activity());
			psmt.setInt(10, newmemp.getInserviced());
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

	public static void update(EmployeeVO selectedemp) {
		try {
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, selectedemp.getEmp_psw());
			psmt.setString(2, selectedemp.getEmp_name());
			psmt.setString(3, selectedemp.getEmp_email());
			psmt.setDate(4, selectedemp.getEmp_hiredate());
			psmt.setDate(5, selectedemp.getEmp_birthday());
			psmt.setString(6, selectedemp.getEmp_address());
			psmt.setString(7, selectedemp.getEmp_phone());
			psmt.setInt(8, selectedemp.getEmp_sex());
			psmt.setTimestamp(9, selectedemp.getLast_activity());
			psmt.setInt(10, selectedemp.getInserviced());
			psmt.setInt(11, selectedemp.getEmpno());
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

	public static EmployeeVO findByPK(Integer empno) {
		EmployeeVO emp = null;
		try {
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, empno);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				emp = new EmployeeVO();
				emp.setEmpno(rs.getInt(1));
				emp.setEmp_psw(rs.getString(2));
				emp.setEmp_name(rs.getString(3));
				emp.setEmp_email(rs.getString(4));
				emp.setEmp_hiredate(rs.getDate(5));
				emp.setEmp_birthday(rs.getDate(6));
				emp.setEmp_address(rs.getString(7));
				emp.setEmp_phone(rs.getString(8));
				emp.setEmp_sex(rs.getInt(9));
				emp.setLast_activity(rs.getTimestamp(10));
				emp.setInserviced(rs.getInt(11));
			}

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
		return emp;
	}

	public static List<EmployeeVO> getAll() {
		EmployeeVO emp = null;
		List<EmployeeVO> empList = new ArrayList<>();

		try {
			psmt = con.prepareStatement(GETALL);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				emp = new EmployeeVO();
				emp.setEmpno(rs.getInt(1));
				emp.setEmp_psw(rs.getString(2));
				emp.setEmp_name(rs.getString(3));
				emp.setEmp_email(rs.getString(4));
				emp.setEmp_hiredate(rs.getDate(5));
				emp.setEmp_birthday(rs.getDate(6));
				emp.setEmp_address(rs.getString(7));
				emp.setEmp_phone(rs.getString(8));
				emp.setEmp_sex(rs.getInt(9));
				emp.setLast_activity(rs.getTimestamp(10));
				emp.setInserviced(rs.getInt(11));
				empList.add(emp);
			}
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
		return empList;
	}
}

package com.employee.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDAO implements EmployeeDAO_interface {
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
	private static final String INSERT = "INSERT INTO EMPLOYEE (EMPNO,EMP_PSW,EMP_NAME,EMP_EMAIL,EMP_HIREDATE,EMP_BIRTHDAY,EMP_ADDRESS,EMP_PHONE,EMP_SEX,LAST_ACTIVITY,INSERVICED)"
			+ "VALUES('13'||LPAD(EMPLOYEE_SEQUENCE.NEXTVAL,3,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE EMPLOYEE SET EMP_PSW=?,EMP_NAME=?,EMP_EMAIL=?,EMP_HIREDATE=?,"
			+ "EMP_BIRTHDAY=?,EMP_ADDRESS=?,EMP_PHONE=?,EMP_SEX=?,LAST_ACTIVITY=?,INSERVICED=? WHERE EMPNO = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMPNO = ?";
	private static final String SELECT = "SELECT * FROM EMPLOYEE WHERE EMPNO = ? AND INSERVICED != 2";
	private static final String SELECTBYEMAIL = "SELECT * FROM EMPLOYEE WHERE EMP_EMAIL = ? AND INSERVICED != 2";
	private static final String GETALL = "SELECT * FROM EMPLOYEE ";

	@Override
	public Integer add(EmployeeVO newmemp) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Integer generatedID = null;
		String[] cols = { "EMPNO" };
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT,cols);
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
			rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				generatedID = rs.getInt(1);
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
		return generatedID;		
	}

	@Override
	public void update(EmployeeVO selectedemp) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
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

	@Override
	public void delete(Integer empno) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
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
	public EmployeeVO findByPK(Integer empno) {
		Connection con = null;
		PreparedStatement psmt = null;
		EmployeeVO emp = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(SELECT);
			psmt.setInt(1, empno);
			rs = psmt.executeQuery();

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
		return emp;
	}
	
	@Override
	public EmployeeVO findByEmail(String emp_email) {
		Connection con = null;
		PreparedStatement psmt = null;
		EmployeeVO emp = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(SELECTBYEMAIL);
			psmt.setString(1, emp_email);
			rs = psmt.executeQuery();

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
		return emp;
	}
	
	@Override
	public List<EmployeeVO> getAll() {
		Connection con = null;
		PreparedStatement psmt = null;
		EmployeeVO emp = null;
		ResultSet rs = null;
		List<EmployeeVO> empList = new ArrayList<>();

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();
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
		return empList;
	}
}

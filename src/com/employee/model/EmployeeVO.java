package com.employee.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class EmployeeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer empno;
	private String emp_psw;
	private String emp_name;
	private String emp_email;
	private java.sql.Date emp_hiredate;
	private java.sql.Date emp_birthday;
	private String emp_address;
	private String emp_phone;
	private Integer emp_sex;
	private Timestamp last_activity;
	private Integer inserviced;

	public Integer getEmpno() {
		return empno;
	}

	public EmployeeVO setEmpno(Integer empno) {
		this.empno = empno;
		return this;
	}

	public String getEmp_psw() {
		return emp_psw;
	}

	public EmployeeVO setEmp_psw(String emp_psw) {
		this.emp_psw = emp_psw;
		return this;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public EmployeeVO setEmp_name(String emp_name) {
		this.emp_name = emp_name;
		return this;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public EmployeeVO setEmp_email(String emp_email) {
		this.emp_email = emp_email;
		return this;
	}

	public java.sql.Date getEmp_hiredate() {
		return emp_hiredate;
	}

	public EmployeeVO setEmp_hiredate(java.sql.Date emp_hiredate) {
		this.emp_hiredate = emp_hiredate;
		return this;
	}

	public java.sql.Date getEmp_birthday() {
		return emp_birthday;
	}

	public EmployeeVO setEmp_birthday(java.sql.Date emp_birthday) {
		this.emp_birthday = emp_birthday;
		return this;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public EmployeeVO setEmp_address(String emp_address) {
		this.emp_address = emp_address;
		return this;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public EmployeeVO setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
		return this;
	}

	public Integer getEmp_sex() {
		return emp_sex;
	}

	public EmployeeVO setEmp_sex(Integer emp_sex) {
		this.emp_sex = emp_sex;
		return this;
	}

	public Timestamp getLast_activity() {
		return last_activity;
	}

	public EmployeeVO setLast_activity(Timestamp last_activity) {
		this.last_activity = last_activity;
		return this;
	}

	public Integer getInserviced() {
		return inserviced;
	}

	public EmployeeVO setInserviced(Integer inserviced) {
		this.inserviced = inserviced;
		return this;
	}
}

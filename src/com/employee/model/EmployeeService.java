package com.employee.model;

import java.sql.Timestamp;
import java.util.List;

public class EmployeeService {
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}
	
	public EmployeeVO add(String emp_psw,String emp_name,
	String emp_email,java.sql.Date emp_hiredate,java.sql.Date emp_birthday,
	String emp_address,String emp_phone,Integer emp_sex,
	Timestamp last_activity,Integer inserviced){
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmp_psw(emp_psw)
		.setEmp_name(emp_name)
		.setEmp_email(emp_email)
		.setEmp_hiredate(emp_hiredate)
		.setEmp_birthday(emp_birthday)
		.setEmp_address(emp_address)
		.setEmp_phone(emp_phone)
		.setEmp_sex(emp_sex)
		.setLast_activity(last_activity)
		.setInserviced(inserviced);
		dao.update(employeeVO);
		return employeeVO;		
	}
	
	public EmployeeVO update(Integer empno,String emp_psw,String emp_name,String emp_email,
	java.sql.Date emp_hiredate,java.sql.Date emp_birthday,String emp_address,
	String emp_phone,Integer emp_sex,Timestamp last_activity,
	Integer inserviced){
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpno(empno)
		.setEmp_psw(emp_psw)
		.setEmp_name(emp_name)
		.setEmp_email(emp_email)
		.setEmp_hiredate(emp_hiredate)
		.setEmp_birthday(emp_birthday)
		.setEmp_address(emp_address)
		.setEmp_phone(emp_phone)
		.setEmp_sex(emp_sex)
		.setLast_activity(last_activity)
		.setInserviced(inserviced);
		dao.update(employeeVO);
		return employeeVO;
	}
	
	public void delete(Integer empno){
		dao.delete(empno);
	}
	
	public EmployeeVO findByPK(Integer empno){
		return dao.findByPK(empno);
	}
	
	public List<EmployeeVO> getAll(){
		return dao.getAll();
	}
}

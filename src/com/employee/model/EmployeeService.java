package com.employee.model;

import java.sql.Timestamp;
import java.util.List;

import com.permision.model.PermisionService;

public class EmployeeService {
	private EmployeeDAO_interface dao;
	private PermisionService permiSvc;
	public EmployeeService() {
		dao = new EmployeeDAO();
		permiSvc = new PermisionService();
	}
	
	public EmployeeVO add(String emp_psw,String emp_name,
	String emp_email,java.sql.Date emp_hiredate,java.sql.Date emp_birthday,
	String emp_address,String emp_phone,Integer emp_sex,String[] operation_ids,Integer inserviced){
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmp_psw(emp_psw)
		.setEmp_name(emp_name)
		.setEmp_email(emp_email)
		.setEmp_hiredate(emp_hiredate)
		.setEmp_birthday(emp_birthday)
		.setEmp_address(emp_address)
		.setEmp_phone(emp_phone)
		.setEmp_sex(emp_sex)
		.setInserviced(inserviced);
		Integer empno = dao.add(employeeVO);
		permiSvc.add(empno,operation_ids);
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
	
	public EmployeeVO updateLastActivityTime(EmployeeVO employeeVO,Long thisTime){
		employeeVO.setLast_activity(new Timestamp(thisTime));
		dao.update(employeeVO);
		return employeeVO;		
	}

	
	public void suspend(EmployeeVO employeeVO,Integer inserviced){
		employeeVO.setInserviced(inserviced);
		dao.update(employeeVO);
	}
	
	public void delete(Integer empno){
		dao.delete(empno);
	}
	
	public EmployeeVO findByPK(Integer empno){
		return dao.findByPK(empno);
	}
	
	public EmployeeVO findByEmail(String emp_email) {
		return dao.findByEmail(emp_email);
	}
	
	public List<EmployeeVO> getAll(){
		return dao.getAll();
	}
}

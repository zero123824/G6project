package com.employee.model;

import java.util.List;
public interface EmployeeDAO_interface {
	Integer add(EmployeeVO newmemp);
	void update(EmployeeVO selectedemp);
	void delete(Integer empno);
	EmployeeVO findByPK(Integer empno);
	EmployeeVO findByEmail(String emp_email);
	List<EmployeeVO> getAll();
}

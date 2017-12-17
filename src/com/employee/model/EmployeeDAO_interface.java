package com.employee.model;

import java.util.List;
public interface EmployeeDAO_interface {
	void add(EmployeeVO newmemp);
	void update(EmployeeVO selectedemp);
	void delete(Integer empno);
	EmployeeVO findByPK(Integer empno);
	List<EmployeeVO> getAll();
}

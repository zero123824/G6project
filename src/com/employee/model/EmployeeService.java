package com.employee.model;

public class EmployeeService {
	private EmployeeDAO_interface dao;
	public EmployeeService(){
		dao = new EmployeeDAO();
	}
}

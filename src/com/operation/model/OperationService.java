package com.operation.model;

import java.util.List;

public class OperationService {
	private OperationDAO_interface dao;
	
	public OperationService() {
		dao = new OperationDAO();
	}
	
	public void add(OperationVO newmoperation) {
		dao.add(newmoperation);
	}
	
	public void update(OperationVO selectedoperation) {
		dao.update(selectedoperation);
	}
	
	public OperationVO findByPK(Integer operation_id) {
		return dao.findByPK(operation_id);
	}
	
	public List<OperationVO> getAll() {
		return dao.getAll();
	}
}

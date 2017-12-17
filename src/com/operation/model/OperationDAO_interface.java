package com.operation.model;

import java.util.List;

public interface OperationDAO_interface {
	void add(OperationVO newmoperation);
	void update(OperationVO selectedoperation);
	void delete(Integer operation_id);
	OperationVO findByPK(Integer operation_id);
	List<OperationVO> getAll();
}

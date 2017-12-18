package com.permision.model;

import java.util.List;

public interface PermisionDAO_interface {
	void add(PermisionVO newmpermision);
	void delete(Integer empno, Integer operation_id);
	List<PermisionVO> getOneEmpPermision(Integer empno);
}

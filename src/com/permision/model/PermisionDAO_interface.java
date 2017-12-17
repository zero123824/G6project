package com.permision.model;

import java.util.List;


public interface PermisionDAO_interface {
	void add(PermisionVO newmpermision);
	void update(PermisionVO selectedpermision);
	void delete(Integer empno);
	PermisionVO findByPK(Integer empno);
	List<PermisionVO> getAll();
}

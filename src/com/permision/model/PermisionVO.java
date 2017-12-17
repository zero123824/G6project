package com.permision.model;

import java.io.Serializable;

public class PermisionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer empno;
	private Integer operation_id;

	public Integer getEmpno() {
		return empno;
	}

	public PermisionVO setEmpno(Integer empno) {
		this.empno = empno;
		return this;
	}

	public Integer getOperation_id() {
		return operation_id;
	}

	public PermisionVO setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
		return this;
	}

}

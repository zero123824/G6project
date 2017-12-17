package com.operation.model;

import java.io.Serializable;

public class OperationVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer operation_id;
	private String operation_name;

	public Integer getOperation_id() {
		return operation_id;
	}

	public OperationVO setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
		return this;
	}

	public String getOperation_name() {
		return operation_name;
	}

	public OperationVO setOperation_name(String operation_name) {
		this.operation_name = operation_name;
		return this;
	}

}

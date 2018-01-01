package com.permision.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PermisionService {
	private PermisionDAO_interface dao;
	
	public PermisionService() {
		dao = new PermisionDAO();
	}
	
	public void add(Integer empno,String[] operation_ids) {
		for(String operation_id : operation_ids) {
			PermisionVO permisionVO = new PermisionVO();
			permisionVO.setEmpno(empno);
			permisionVO.setOperation_id(Integer.valueOf(operation_id));
			dao.add(permisionVO);
		}		
	}
	
	public void delete(Integer empno,Integer operation_id){
		dao.delete(empno,operation_id);
	}
	
	public Map<String,String> getOneEmpPermision(Integer empno) {
		List<PermisionVO> list = dao.getOneEmpPermision(empno);
		Map<String,String> keymap = new LinkedHashMap<String,String>();
		for(PermisionVO permisionVO : list) {
			keymap.put(permisionVO.getOperation_id().toString(), permisionVO.getOperation_id().toString());
		}
		return keymap;
	}
}

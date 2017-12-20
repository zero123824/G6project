package com.orderlist.model;

import java.util.List;

public interface OrderListDAO_Interface {
	public long insert(OrderListVO orderListVO);
	public int deleteOneOrder(Long order_id);
	public List<OrderListVO> findAllOrdersByMember(Long member_id);
}

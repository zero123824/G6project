package com.orderlist.model;

import java.util.List;

public class TestOrderList {
	public static void main(String[] args) {
		OrderListDAO dao=new OrderListDAO();
		//新增，並把自增主鍵綁出來		
//		OrderListVO ol=new OrderListVO();
//		ol.setMember_id(1000000000L);	
//		ol.setOrder_amount(777);	
//		ol.setMovie_feedback(0);
//		ol.setTicket_print(1);		
//		System.out.println("自增的票券編號：  "+dao.insert(ol));
		
		//刪除一張訂單
//		System.out.println("已刪除  "+dao.deleteOneOrder(3000000008L)+" 筆訂單");
		
		//以會員ID查訂單
		List<OrderListVO> list= dao.findAllOrdersByMember(1000000001L);		
		for(OrderListVO anOrder:list){
			System.out.println(anOrder.toString());
		 }
	}	
}

package com.ticket_list.model;

import java.util.List;

public class TestTicketList {
	public static void main(String[] args) {
		TicketListDAO dao=new TicketListDAO();
		//新增，並把自增主鍵綁出來
//		TicketListVO tl=new TicketListVO();
//		tl.setOrder_id(3000000003L);	
//		tl.setTicket_type_no(16000);	
//		tl.setSchedule_id(6000000098L);
//		tl.setTicket_seat_index(35);
//		tl.setTicket_seat("C-7");
//		tl.setOrder_enter_state(1);
//		tl.setOrder_food_state(2);
//		System.out.println("自增的票券編號：  "+dao.insert(tl));
		
		//刪除一張票
//		System.out.println("刪除 "+dao.deleteOneTicket(1700000022)+" 筆資料");
		//刪除一筆訂單
//		System.out.println("刪除一筆訂單，內含 "+dao.deleteOneOrder(3000000000L)+" 張票");
		
		//查詢一張票
//		System.out.println(dao.findOneTicket(1700000002).toString());
		
		//查詢一整張訂單
//		List<TicketListVO> list= dao.findOneOrder(3000000001L);		
//		for(TicketListVO aTicket:list){
//			System.out.println(aTicket.toString());
//		 }		
	}
}

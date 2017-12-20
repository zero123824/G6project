package com.ticket_list.model;

import java.util.List;

public interface TicketListDAO_Interface {
	public int insert(TicketListVO ticketListVO);
	public int deleteOneTicket(Integer ticket_id);
	public int deleteOneOrder(Long order_id);
	public TicketListVO findOneTicket(Integer ticket_id);
	public List<TicketListVO> findOneOrder(Long order_id);
}

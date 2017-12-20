package com.ticket_type.model;
import java.util.List;

	public interface TicketTypeDAO_Interface {
		public int insert(TicketTypeVO TicketTypeVO);
		public int update(TicketTypeVO TicketTypeVO);
		public int delete(Integer ticket_type_no);
		public TicketTypeVO findByPrimaryKey(Integer ticket_type_no);
		public List<TicketTypeVO> getAll();
	}

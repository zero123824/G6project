package com.ticket_list.model;

public class TicketListVO {
	private Integer ticket_id;
	private Long order_id;
	private Integer ticket_type_no;
	private Long schedule_id;
	private Integer ticket_seat_index;
	private String ticket_seat;
	private Integer order_enter_state;
	private Integer order_food_state;
	
	public Integer getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Integer getTicket_type_no() {
		return ticket_type_no;
	}
	public void setTicket_type_no(Integer ticket_type_no) {
		this.ticket_type_no = ticket_type_no;
	}
	public Long getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Long schedule_id) {
		this.schedule_id = schedule_id;
	}
	public Integer getTicket_seat_index() {
		return ticket_seat_index;
	}
	public void setTicket_seat_index(Integer ticket_seat_index) {
		this.ticket_seat_index = ticket_seat_index;
	}
	public String getTicket_seat() {
		return ticket_seat;
	}
	public void setTicket_seat(String ticket_seat) {
		this.ticket_seat = ticket_seat;
	}
	public Integer getOrder_enter_state() {
		return order_enter_state;
	}
	public void setOrder_enter_state(Integer order_enter_state) {
		this.order_enter_state = order_enter_state;
	}
	public Integer getOrder_food_state() {
		return order_food_state;
	}
	public void setOrder_food_state(Integer order_food_state) {
		this.order_food_state = order_food_state;
	}
	
	public String toString(){
		return ticket_id+","+
				order_id+","+
				ticket_type_no+","+
				schedule_id+","+
				ticket_seat_index+","+
				ticket_seat+","+
				order_enter_state+","+
				order_food_state;
	}
}

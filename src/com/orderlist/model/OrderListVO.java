package com.orderlist.model;

import java.sql.*;

public class OrderListVO {
	private Long order_id;
	private Long member_id;
	private Integer order_amount;
	private Timestamp order_date;
	private Integer movie_feedback;
	private Integer ticket_print;
	
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public Integer getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Integer order_amount) {
		this.order_amount = order_amount;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public Integer getMovie_feedback() {
		return movie_feedback;
	}
	public void setMovie_feedback(Integer movie_feedback) {
		this.movie_feedback = movie_feedback;
	}
	public Integer getTicket_print() {
		return ticket_print;
	}
	public void setTicket_print(Integer ticket_print) {
		this.ticket_print = ticket_print;
	}
	public String toString(){
		return order_id+","+
				member_id+","+
				order_amount+","+
				order_date+","+
				movie_feedback+","+
				ticket_print;
		}
}

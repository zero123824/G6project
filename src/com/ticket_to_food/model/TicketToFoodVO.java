package com.ticket_to_food.model;
import java.io.Serializable;

public class TicketToFoodVO implements Serializable
{
private Integer ticket_type_no;
private Integer food_id;

public TicketToFoodVO() {
}

public Integer getTicket_type_no() {
	return ticket_type_no;
}

public void setTicket_type_no(Integer ticket_type_no) {
	this.ticket_type_no = ticket_type_no;
}

public Integer getFood_id() {
	return food_id;
}

public void setFood_id(Integer food_id) {
	this.food_id = food_id;
}

public String toString(){
	return ticket_type_no
			+","
			+food_id;
}

}
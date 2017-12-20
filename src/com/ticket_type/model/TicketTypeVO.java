package com.ticket_type.model;
import java.io.*;

public class TicketTypeVO implements Serializable
{
private static final long serialVersionUID = 1L;
private Integer ticket_type_no;
private String ticket_type_name;
private String ticket_type_food;
private Integer ticket_type_price;
private Integer ticket_type_state;
private byte[] ticket_type_pic;


public Integer getTicket_type_no(){
	return ticket_type_no;	
}

public void setTicket_type_no(Integer ticket_type_no){
	this.ticket_type_no=ticket_type_no;
}

public String getTicket_type_name(){
	return ticket_type_name;
}

public void setTicket_type_name(String ticket_type_name){
	this.ticket_type_name=ticket_type_name;
}

public String getTicket_type_food(){
	return ticket_type_food;
}

public void setTicket_type_food(String ticket_type_food){
	this.ticket_type_food=ticket_type_food;
}

public Integer getTicket_type_price(){
	return ticket_type_price;
}

public void setTicket_type_price(Integer ticket_type_price){
	this.ticket_type_price=ticket_type_price;
}

public Integer getTicket_type_state(){
	return ticket_type_state;
}

public void setTicket_type_state(Integer ticket_type_state){
	this.ticket_type_state=ticket_type_state;
}

public byte[] getTicket_type_pic(){
	return ticket_type_pic;
}

public void setTicket_type_pic(byte[] ticket_type_pic){
	this.ticket_type_pic=ticket_type_pic;
}

public String toString(){
	return ticket_type_no
			+","
			+ticket_type_name
			+","
			+ticket_type_food
			+","
			+ticket_type_price
			+","
			+ticket_type_state
			+","
			+ticket_type_pic;			
}

}

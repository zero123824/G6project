package com.ticket_to_food.model;
import java.util.*;
public interface TicketToFood_DAOinterface {
	public int insert(TicketToFoodVO ticketToFoodVO);
	public int delete(Integer ticket_type_no,Integer food_id);
}

package com.ticket_to_food.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ticket_type.model.TicketTypeVO;

public class TicketToFoodDAO implements TicketToFood_DAOinterface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="ba105g6";
	String passwd="ba105g6";
	private static final String INSERT_STMT=
		"INSERT INTO TICKET_TO_FOOD(ticket_type_no,food_id)"
		+"VALUES (?,?)";
	private static final String DELETE="DELETE FROM TICKET_TO_FOOD WHERE ticket_type_no=? and food_id=?";
	private static final String GET_ALL_STMT=
		"SELECT * FROM TICKET_TYPE ORDER BY ticket_type_no";
	public int insert(TicketToFoodVO ticketToFoodVO) {
		int updateCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,userid,passwd);
				pstmt=con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, ticketToFoodVO.getTicket_type_no());
				pstmt.setInt(2, ticketToFoodVO.getFood_id());
				updateCount=pstmt.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		return updateCount;
	}

	public int delete(Integer ticket_type_no, Integer food_id) {
		int updateCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1,ticket_type_no);
			pstmt.setInt(2,food_id);
			updateCount=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;
	}



}


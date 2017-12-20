package com.ticket_list.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ticket_type.model.TicketTypeVO;

public class TicketListDAO implements TicketListDAO_Interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="ba105g6";
	String passwd="ba105g6";
		private static final String INSERT_STMT=
			"INSERT INTO TICKET_LIST VALUES (TICKET_LIST_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		private static final String DELETE_ONE_TICKET_STMT="DELETE FROM TICKET_LIST WHERE ticket_id=?";
		private static final String DELETE_ONE_ORDER_STMT="DELETE FROM TICKET_LIST WHERE ORDER_ID=?";
		private static final String GET_ONE_TICKET_STMT=
			"SELECT * FROM TICKET_LIST WHERE ticket_id=?";
		private static final String GET_ONE_ORDER_STMT=
			"SELECT * FROM TICKET_LIST WHERE ORDER_ID=?";
		
		//新增，並把自增主鍵綁出來
		public int insert(TicketListVO ticketListVO) {
			Connection con=null;
			PreparedStatement pstmt=null;		
			String[] cols = { "TICKET_ID" }; 
			String key=null;
				try {
					Class.forName(driver);
					con=DriverManager.getConnection(url,userid,passwd);
					pstmt=con.prepareStatement(INSERT_STMT, cols);
					
					pstmt.setLong(1, ticketListVO.getOrder_id());
					pstmt.setInt(2, ticketListVO.getTicket_type_no());
					pstmt.setLong(3, ticketListVO.getSchedule_id());
					pstmt.setInt(4, ticketListVO.getTicket_seat_index());
					pstmt.setString(5, ticketListVO.getTicket_seat());
					pstmt.setInt(6, ticketListVO.getOrder_enter_state());
					pstmt.setInt(7, ticketListVO.getOrder_food_state());					
					pstmt.executeUpdate();					
					
					//把自增主鍵綁出來
					ResultSet rs = pstmt.getGeneratedKeys();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					if (rs.next()) {
						do {
							for (int i = 1; i <= columnCount; i++) {
								key = rs.getString(i);
							}
						} while (rs.next());
						return Integer.valueOf(key);
					} else {
						System.out.println("NO KEYS WERE GENERATED.");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return 0;
		}

		//刪除一張票
		public int deleteOneTicket(Integer ticket_id) {
			int updateCount=0;
			Connection con=null;
			PreparedStatement pstmt=null;
				try {
					Class.forName(driver);
					con=DriverManager.getConnection(url,userid,passwd);
					pstmt=con.prepareStatement(DELETE_ONE_TICKET_STMT);
					pstmt.setInt(1,ticket_id);
					updateCount=pstmt.executeUpdate();					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return updateCount;
		}

		//刪除一整筆訂單
		public int deleteOneOrder(Long order_id) {
			int updateCount=0;
			Connection con=null;
			PreparedStatement pstmt=null;
				try {
					Class.forName(driver);
					con=DriverManager.getConnection(url,userid,passwd);
					pstmt=con.prepareStatement(DELETE_ONE_ORDER_STMT);
					pstmt.setLong(1,order_id);
					updateCount=pstmt.executeUpdate();					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return updateCount;
		}

		//查詢一張票
		public TicketListVO findOneTicket(Integer ticket_id) {
			TicketListVO ticketListVO=null;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,userid,passwd);
				pstmt=con.prepareStatement(GET_ONE_TICKET_STMT);
				pstmt.setInt(1,ticket_id);
				rs=pstmt.executeQuery();
				while(rs.next()){
					ticketListVO=new TicketListVO();
					ticketListVO.setTicket_id(rs.getInt("ticket_id"));
					ticketListVO.setOrder_id(rs.getLong("Order_id"));					
					ticketListVO.setTicket_type_no(rs.getInt("Ticket_type_no"));
					ticketListVO.setSchedule_id(rs.getLong("schedule_id"));
					ticketListVO.setTicket_seat_index(rs.getInt("ticket_seat_index"));
					ticketListVO.setTicket_seat(rs.getString("ticket_seat"));
					ticketListVO.setOrder_enter_state(rs.getInt("order_enter_state"));
					ticketListVO.setOrder_food_state(rs.getInt("order_food_state"));	
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return ticketListVO;
		}

		//查詢一整筆訂單
		public List<TicketListVO> findOneOrder(Long order_id) {
			List<TicketListVO> list= new ArrayList<TicketListVO>();
			TicketListVO ticketListVO=null;
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;			
			
				try {
					Class.forName(driver);
					con=DriverManager.getConnection(url,userid,passwd);
					pstmt=con.prepareStatement(GET_ONE_ORDER_STMT);
					pstmt.setLong(1,order_id);
					rs=pstmt.executeQuery();
					
					while (rs.next()){
						ticketListVO=new TicketListVO();
						ticketListVO.setTicket_id(rs.getInt("ticket_id"));
						ticketListVO.setOrder_id(rs.getLong("Order_id"));					
						ticketListVO.setTicket_type_no(rs.getInt("Ticket_type_no"));
						ticketListVO.setSchedule_id(rs.getLong("schedule_id"));
						ticketListVO.setTicket_seat_index(rs.getInt("ticket_seat_index"));
						ticketListVO.setTicket_seat(rs.getString("ticket_seat"));
						ticketListVO.setOrder_enter_state(rs.getInt("order_enter_state"));
						ticketListVO.setOrder_food_state(rs.getInt("order_food_state"));		
						list.add(ticketListVO);
					}
				} catch (ClassNotFoundException e) {				
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					if(rs!=null){					
							try {
								rs.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}					
					}
					if(pstmt!=null){
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(con!=null){
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}				
				}			
			return list;
		}

}

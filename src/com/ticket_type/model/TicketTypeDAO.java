package com.ticket_type.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO implements TicketTypeDAO_Interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="ba105g6";
	String passwd="ba105g6";
	private static final String INSERT_STMT=
		"INSERT INTO TICKET_TYPE(ticket_type_no, ticket_type_name, ticket_type_food, ticket_type_price, ticket_type_state, ticket_type_pic)"
		+"VALUES (TICKET_TYPE_SEQ.NEXTVAL,?,?,?,?,?)";
	private static final String UPDATE_STMT=
		"UPDATE TICKET_TYPE SET ticket_type_name=?, ticket_type_food=?, ticket_type_price=?, ticket_type_state=?, ticket_type_pic=? "
		+"WHERE ticket_type_no=?";
	private static final String DELETE="DELETE FROM TICKET_TYPE WHERE ticket_type_no=?";
	private static final String GET_ONE_STMT=
		"SELECT * FROM TICKET_TYPE WHERE ticket_type_no=?";
	private static final String GET_ALL_STMT=
		"SELECT * FROM TICKET_TYPE ORDER BY ticket_type_no";
	
	public int insert(TicketTypeVO TicketTypeVO){
		int updateCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, TicketTypeVO.getTicket_type_name());
			pstmt.setString(2, TicketTypeVO.getTicket_type_food());
			pstmt.setInt(3, TicketTypeVO.getTicket_type_price());
			pstmt.setInt(4, TicketTypeVO.getTicket_type_state());
			pstmt.setBytes(5, TicketTypeVO.getTicket_type_pic());
			
			updateCount=pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return updateCount;
	};
	
	public int update(TicketTypeVO TicketTypeVO){
		int updateCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, TicketTypeVO.getTicket_type_name());
			pstmt.setString(2, TicketTypeVO.getTicket_type_food());
			pstmt.setInt(3, TicketTypeVO.getTicket_type_price());
			pstmt.setInt(4, TicketTypeVO.getTicket_type_state());
			pstmt.setBytes(5, TicketTypeVO.getTicket_type_pic());
			pstmt.setInt(6, TicketTypeVO.getTicket_type_no());
			
			updateCount=pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}			
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	};
		
	public int delete(Integer ticket_type_no){
		int updateCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1,ticket_type_no);
			updateCount=pstmt.executeUpdate();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}		
		return updateCount;
	};

	public TicketTypeVO findByPrimaryKey(Integer ticket_type_no){
		TicketTypeVO ticketTypeVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,ticket_type_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ticketTypeVO=new TicketTypeVO();
				ticketTypeVO.setTicket_type_no(rs.getInt("ticket_type_no"));
				ticketTypeVO.setTicket_type_name(rs.getString("ticket_type_name"));
				ticketTypeVO.setTicket_type_food(rs.getString("ticket_type_food"));
				ticketTypeVO.setTicket_type_price(rs.getInt("ticket_type_price"));
				ticketTypeVO.setTicket_type_state(rs.getInt("ticket_type_state"));
				ticketTypeVO.setTicket_type_pic(rs.getBytes("ticket_type_pic"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ticketTypeVO;
	};
	
	public List<TicketTypeVO> getAll(){
		List<TicketTypeVO> list= new ArrayList<TicketTypeVO>();
		TicketTypeVO ticketTypeVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,userid,passwd);
				pstmt=con.prepareStatement(GET_ALL_STMT);
				rs=pstmt.executeQuery();
				
				while (rs.next()){
					ticketTypeVO=new TicketTypeVO();
					ticketTypeVO.setTicket_type_no(rs.getInt("ticket_type_no"));
					ticketTypeVO.setTicket_type_name(rs.getString("ticket_type_name"));
					ticketTypeVO.setTicket_type_food(rs.getString("ticket_type_food"));
					ticketTypeVO.setTicket_type_price(rs.getInt("ticket_type_price"));
					ticketTypeVO.setTicket_type_state(rs.getInt("ticket_type_state"));
					ticketTypeVO.setTicket_type_pic(rs.getBytes("ticket_type_pic"));		
					list.add(ticketTypeVO);
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
	};
}


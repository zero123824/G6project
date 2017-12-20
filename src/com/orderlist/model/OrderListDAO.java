package com.orderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket_list.model.TicketListVO;

public class OrderListDAO implements OrderListDAO_Interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="ba105g6";
	String passwd="ba105g6";
		private static final String INSERT_STMT=
			"INSERT INTO ORDER_LIST (ORDER_ID,MEMBER_ID,ORDER_AMOUNT,MOVIE_FEEDBACK,TICKET_PRINT)"
			+ "VALUES(ORDER_LIST_SEQ.NEXTVAL,?,?,?,?)";
		private static final String DELETE_ONE_ORDER_STMT="DELETE FROM ORDER_LIST WHERE ORDER_ID=?";
		private static final String GET_ORDER_OF_A_Member_STMT=
			"SELECT * FROM ORDER_LIST WHERE member_id=?";	
	//新增，並把自增主鍵綁出來
	public long insert(OrderListVO orderListVO) {
		Connection con=null;
		PreparedStatement pstmt=null;		
		String[] cols = { "order_id" }; 
		String key=null;
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,userid,passwd);
				pstmt=con.prepareStatement(INSERT_STMT, cols);
				pstmt.setLong(1, orderListVO.getMember_id());
				pstmt.setInt(2, orderListVO.getOrder_amount());
				pstmt.setLong(3, orderListVO.getMovie_feedback());
				pstmt.setLong(4, orderListVO.getTicket_print());
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
					return Long.valueOf(key);
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

	//刪除一張訂單
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

	//以會員ID查訂單
	public List<OrderListVO> findAllOrdersByMember(Long member_id) {
		List<OrderListVO> list= new ArrayList<OrderListVO>();
		OrderListVO orderListVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;			
		
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url,userid,passwd);
				pstmt=con.prepareStatement(GET_ORDER_OF_A_Member_STMT);
				pstmt.setLong(1,member_id);
				rs=pstmt.executeQuery();
				
				while (rs.next()){
					orderListVO=new OrderListVO();
					orderListVO.setOrder_id(rs.getLong("order_id"));
					orderListVO.setMember_id(rs.getLong("member_id"));					
					orderListVO.setOrder_amount(rs.getInt("order_amount"));
					orderListVO.setOrder_date(rs.getTimestamp("order_date"));
					orderListVO.setMovie_feedback(rs.getInt("movie_feedback"));
					orderListVO.setTicket_print(rs.getInt("ticket_print"));
					list.add(orderListVO);
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

import java.util.List;

import com.member_favor.controller.GetRecommendMovie;
import com.member_favor.model.MemberFavorDAO;
import com.member_favor.model.MemberFavorVO;

public class temp {
	
//	session.setAttribute("orderrecord", getOrderlist(member.getMember_id()));
	getMemberFavor(member.getMember_id());
	GetRecommendMovie grm = new GetRecommendMovie();
	grm.getRecommendMovie_ID(member.getMember_id());
	//取得訂票紀錄
//		private JsonArray getOrderlist(Integer member_id){
//			try {
//				pstmt = con.prepareStatement("SELECT SCHEDULE_DATE,TICKET_LIST.SCHEDULE_ID,MOVIE_NAME_CH FROM MOVIE "
//						+ "JOIN MOVIE_SCHEDULE ON MOVIE.MOVIE_ID = MOVIE_SCHEDULE.MOVIE_ID "
//						+ "JOIN TICKET_LIST ON TICKET_LIST.SCHEDULE_ID = MOVIE_SCHEDULE.SCHEDULE_ID "
//						+ "JOIN ORDER_LIST ON ORDER_LIST.ORDER_ID = TICKET_LIST.ORDER_ID "
//						+ "WHERE MEMBER_ID = ? "
//						+ "GROUP BY SCHEDULE_DATE,TICKET_LIST.SCHEDULE_ID,MOVIE_NAME_CH");
//				pstmt.setInt(1, member_id);
//				ResultSet rs = pstmt.executeQuery();
	////將電影名稱、場次紀錄放入jsonarray			
//				JsonArray ja = new JsonArray();
//				
//				while(rs.next()){				
//					
//					JsonObject jo = new JsonObject();
//					jo.addProperty("date", String.valueOf(rs.getDate("SCHEDULE_DATE")));
//					jo.addProperty("moviename", rs.getString("MOVIE_NAME_CH"));
//					ja.add(jo);
//				}
//				return ja;
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
		
	//取得會員喜好類型
		private List<MemberFavorVO> getMemberFavor(Integer member_id){
			MemberFavorDAO mfd = new MemberFavorDAO();
			List<MemberFavorVO> memfavorList;
			memfavorList = mfd.getOneMemFavor(member_id);
			return memfavorList;
		}
}

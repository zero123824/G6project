

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonArray;

public class GetRecommendMovie {
	// 共用資源連線池
	private static DataSource ds = null;
	private static final String GETMOVIEID = "SELECT MOVIE_GENRE.MOVIE_ID,MOVIE_NAME_CH FROM MEMBER_FAVOR "
			+ "JOIN GENRE ON MEMBER_FAVOR.GENRE_ID = GENRE.GENRE_ID "
			+ "JOIN MOVIE_GENRE ON MOVIE_GENRE.GENRE_ID = GENRE.GENRE_ID "
			+ "JOIN MOVIE ON MOVIE_GENRE.MOVIE_ID = MOVIE.MOVIE_ID " + "WHERE MEMBER_ID = ? "
			+ "GROUP BY MOVIE_GENRE.MOVIE_ID,MOVIE_NAME_CH";
	private static final String GETMOVIEDATA = "SELECT MOVIE_NAME_CH,MOVIE_DATE,MOVIE_INTRODUCE,MOVIE_POSTER "
			+ "FROM MOVIE "
			+ "WHERE MOVIE_NAME_CH = ?";

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 取得推薦電影的ID並呼叫電影資訊方法
	@SuppressWarnings("unused")
	public static void getRecommendMovie_ID(Integer member_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		List<JsonArray> ja = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETMOVIEID);
			psmt.setInt(1, member_id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				rs.getString("MOVIE_NAME_CH");
 				getMovieData(rs.getString("MOVIE_NAME_CH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 取得推薦電影的資訊
	private static void getMovieData(String moviename) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETMOVIEDATA);
			psmt.setString(1, moviename);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("MOVIE_NAME_CH");
				rs.getDate("MOVIE_DATE");
				rs.getString("MOVIE_INTRODUCE");
				System.out.println(String.valueOf(rs.getDate("MOVIE_DATE")));
				System.out.println(rs.getString("MOVIE_INTRODUCE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

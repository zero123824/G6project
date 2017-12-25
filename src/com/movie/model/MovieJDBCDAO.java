package com.movie.model;

import java.util.*;
import java.sql.*;
import java.io.*;

import com.movie_comment.model.Movie_CommentVO;
import com.movie_schedule.model.Movie_ScheduleVO;

public class MovieJDBCDAO implements MovieDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba105g6";
	String passwd = "ba105g6";
	
	private static final String INSERT_STMT = 
		"INSERT INTO movie (movie_id,movie_name_ch,movie_name_en,movie_poster,movie_rating,movie_date,movie_time,movie_company,movie_director,movie_actor,movie_introduce,movie_url,movie_stat) VALUES (movie_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT movie_id,movie_name_ch,movie_name_en,movie_poster,movie_rating,to_char(movie_date,'yyyy-mm-dd') movie_date,movie_time,movie_company,movie_director,movie_actor,movie_introduce,movie_url,movie_stat FROM movie order by movie_id";
	private static final String GET_ONE_STMT = 
		"SELECT movie_id,movie_name_ch,movie_name_en,movie_poster,movie_rating,to_char(movie_date,'yyyy-mm-dd') movie_date,movie_time,movie_company,movie_director,movie_actor,movie_introduce,movie_url,movie_stat FROM movie where movie_id = ?";
	private static final String DELETE = 
		"DELETE FROM movie where movie_id = ?";
	private static final String UPDATE = 
		"UPDATE movie set movie_name_ch=?, movie_name_en=?, movie_poster=?, movie_rating=?, movie_date=?, movie_time=?, movie_company=?, movie_director=?, movie_actor=?, movie_introduce=?, movie_url=?, movie_stat=? where movie_id = ?";
	private static final String GET_SCHEDULE_BYMOVIE_STMT = 
		"SELECT schedule_id,movie_id,hall_id,to_char(schedule_date,'yyyy-mm-dd hh:mi:ss')schedule_date,sold_seat,seat_stat,midnight FROM movie_schedule where movie_id = ? order by schedule_id";
	private static final String GET_COMMENT_BYMOVIE_STMT = 
		"SELECT comment_id,movie_id,member_id,to_char(comment_time,'yyyy-mm-dd hh24:mi:ss')comment_time,comment_content,comment_stat FROM movie_comment where movie_id = ? order by comment_id";
	
	@Override
	public void insert(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, movieVO.getMovie_name_ch());
			pstmt.setString(2, movieVO.getMovie_name_en());
			pstmt.setBytes(3, movieVO.getMovie_poster());
			pstmt.setString(4, movieVO.getMovie_rating());
			pstmt.setDate(5, movieVO.getMovie_date());
			pstmt.setInt(6, movieVO.getMovie_time());
			pstmt.setString(7, movieVO.getMovie_company());
			pstmt.setString(8, movieVO.getMovie_director());
			pstmt.setString(9, movieVO.getMovie_actor());
			pstmt.setString(10, movieVO.getMovie_introduce());
			pstmt.setString(11, movieVO.getMovie_url());
			pstmt.setBoolean(12, movieVO.getMovie_stat());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movieVO.getMovie_name_ch());
			pstmt.setString(2, movieVO.getMovie_name_en());
			pstmt.setBytes(3, movieVO.getMovie_poster());
			pstmt.setString(4, movieVO.getMovie_rating());
			pstmt.setDate(5, movieVO.getMovie_date());
			pstmt.setInt(6, movieVO.getMovie_time());
			pstmt.setString(7, movieVO.getMovie_company());
			pstmt.setString(8, movieVO.getMovie_director());
			pstmt.setString(9, movieVO.getMovie_actor());
			pstmt.setString(10, movieVO.getMovie_introduce());
			pstmt.setString(11, movieVO.getMovie_url());
			pstmt.setBoolean(12, movieVO.getMovie_stat());
			pstmt.setLong(13, movieVO.getMovie_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(Long movie_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setLong(1, movie_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public MovieVO findByPrimaryKey(Long movie_id) {
		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setLong(1, movie_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// movieVo 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getLong("movie_id"));
				movieVO.setMovie_name_ch(rs.getString("movie_name_ch"));
				movieVO.setMovie_name_en(rs.getString("movie_name_en"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_rating(rs.getString("movie_rating"));
				movieVO.setMovie_date(rs.getDate("movie_date"));
				movieVO.setMovie_time(rs.getInt("movie_time"));
				movieVO.setMovie_company(rs.getString("movie_company"));
				movieVO.setMovie_director(rs.getString("movie_director"));
				movieVO.setMovie_actor(rs.getString("movie_actor"));
				movieVO.setMovie_introduce(rs.getString("movie_introduce"));
				movieVO.setMovie_url(rs.getString("movie_url"));
				movieVO.setMovie_stat(rs.getBoolean("movie_stat"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return movieVO;
	}
	
	@Override
	public List<MovieVO> getAll() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getLong("movie_id"));
				movieVO.setMovie_name_ch(rs.getString("movie_name_ch"));
				movieVO.setMovie_name_en(rs.getString("movie_name_en"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_rating(rs.getString("movie_rating"));
				movieVO.setMovie_date(rs.getDate("movie_date"));
				movieVO.setMovie_time(rs.getInt("movie_time"));
				movieVO.setMovie_company(rs.getString("movie_company"));
				movieVO.setMovie_director(rs.getString("movie_director"));
				movieVO.setMovie_actor(rs.getString("movie_actor"));
				movieVO.setMovie_introduce(rs.getString("movie_introduce"));
				movieVO.setMovie_url(rs.getString("movie_url"));
				movieVO.setMovie_stat(rs.getBoolean("movie_stat"));
				list.add(movieVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public Set<Movie_ScheduleVO> getScheduleByMovie(Long movie_id) {
		Set<Movie_ScheduleVO> set = new LinkedHashSet<Movie_ScheduleVO>();
		Movie_ScheduleVO movie_scheduleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SCHEDULE_BYMOVIE_STMT);
			pstmt.setLong(1, movie_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				movie_scheduleVO = new Movie_ScheduleVO();
				movie_scheduleVO.setSchedule_id(rs.getLong("schedule_id"));
				movie_scheduleVO.setMovie_id(rs.getLong("movie_id"));
				movie_scheduleVO.setHall_id(rs.getInt("hall_id"));
				movie_scheduleVO.setSchedule_date(rs.getTimestamp("schedule_date"));
				movie_scheduleVO.setSold_seat(rs.getInt("sold_seat"));
				movie_scheduleVO.setSeat_stat(rs.getString("seat_stat"));
				movie_scheduleVO.setMidnight(rs.getBoolean("midnight"));
				set.add(movie_scheduleVO);
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
	
	@Override
	public Set<Movie_CommentVO> getCommentByMovie(Long movie_id) {
		Set<Movie_CommentVO> set = new LinkedHashSet<Movie_CommentVO>();
		Movie_CommentVO movie_commentVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COMMENT_BYMOVIE_STMT);
			pstmt.setLong(1, movie_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				movie_commentVO = new Movie_CommentVO();
				movie_commentVO.setComment_id(rs.getLong("comment_id"));
				movie_commentVO.setMovie_id(rs.getLong("movie_id"));
				movie_commentVO.setMember_id(rs.getLong("member_id"));
				movie_commentVO.setComment_time(rs.getTimestamp("comment_time"));
				movie_commentVO.setComment_content(rs.getString("comment_content"));
				movie_commentVO.setComment_stat(rs.getBoolean("comment_stat"));
				set.add(movie_commentVO);
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	public static void main(String[] args) {
		
		MovieJDBCDAO dao = new MovieJDBCDAO();
		
		// 新增
		MovieVO movieVO1 = new MovieVO();
//		try {
//			byte[] pic1 = getPictureByteArray("WebContent/movie/images/Elementary.jpg");
//			movieVO1.setMovie_name_ch("親愛的小鬼們");
//			movieVO1.setMovie_name_en("Elementary");
//			movieVO1.setMovie_poster(pic1);
//			movieVO1.setMovie_rating("普遍級");
//			movieVO1.setMovie_date(java.sql.Date.valueOf("2017-12-15"));
//			movieVO1.setMovie_time(106);
//			movieVO1.setMovie_company("車庫娛樂");
//			movieVO1.setMovie_director("艾萊娜安潔兒(Hélène Angel)");
//			movieVO1.setMovie_actor("莎拉佛斯提耶(Sara Forestier) 、 文森艾巴(Vincent Elbaz) 、 派翠克達桑索(Patrick d'Assumçao)");
//			movieVO1.setMovie_introduce("★ 法國師生淚灑電影院，溫馨動人更勝《放牛班的春天》，誠摯感人媲美《山村猶有讀書聲》\n" + "★ 盧卡諾金豹獎得主 寫實執導春風化雨的教育歷程\n" + "★《香水》《愛情戰場》法國影后莎拉佛斯提耶最新動人力作\n" + " \n" + "熱衷於教育事業的國小老師佛羅杭絲（莎拉佛斯提耶 飾）是個單親媽媽，對於學生和兒子都投注同等的關愛和心力。當佛羅杭絲得知自己的學生沙夏遭到母親遺棄，遂下定決心要幫助這個男孩重拾幸福生活，不料沙夏身處的困境加上佛羅杭絲自己遭遇的壓力，讓她的意志力面臨難以想像的考驗，佛羅杭絲雖然不願輕言放棄，她的頑強態度卻讓教職工作和親子關係都陷入搖搖欲墜的危機……");
//			movieVO1.setMovie_url("https://ppt.cc/f6iBQx");
//			movieVO1.setMovie_stat(true);
//			dao.insert(movieVO1);
//			
//			System.out.println("新增成功");
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 修改
//		try {
//			byte[] pic2 = getPictureByteArray("WebContent/movie/images/Along With the Gods- The Two Worlds.jpg");
//			MovieVO movieVO2 = new MovieVO();
//			movieVO2.setMovie_id(4000000001L);
//			movieVO2.setMovie_name_ch("與神同行");
//			movieVO2.setMovie_name_en("Along With the Gods: The Two Worlds");
//			movieVO2.setMovie_poster(pic2);
//			movieVO2.setMovie_rating("保護級");
//			movieVO2.setMovie_date(java.sql.Date.valueOf("2017-12-22"));
//			movieVO2.setMovie_time(0);
//			movieVO2.setMovie_company("采昌國際多媒體");
//			movieVO2.setMovie_director("金容華(KIM Yong-hwa)");
//			movieVO2.setMovie_actor("河正宇(Jung-woo Ha) 、 車太鉉(CHA Tae-hyun) 、 朱智勳(JU Ji-hoon) 、 金香起(KIM Hyang-Gi) 、 李政宰 、 金東昱 、 馬東錫 、 金秀安 、 吳達庶(Oh Dal-su) 、 林元熙 、 鄭海均 、 張光 、 金海淑 、 李璟榮(Lee Kyoung-Young) 、 金荷娜");
//			movieVO2.setMovie_introduce("★ LINE WEBTOON同名人氣獲獎漫畫改編！\n" + "★ 集結南韓最輝煌的演技派卡司，呈現最精彩絕倫的集體演出！\n" + "★ 繼《屍速列車》、《軍艦島》後，南韓最重要的年度話題強片！\n" + "★ 籌備拍攝6年，耗資400億韓圜，南韓近年最高製作預算電影！\n" + "★ 《狄仁傑之神都龍王》、《智取威虎山》特效團隊打造華麗視覺！\n" + "★ 轟動全球話題延燒！國際版權狂銷北美、加拿大等世界百餘國！\n" + " \n" + "這是一個，你不曾見過的世界\n" + "金自鴻（車太鉉飾演）是個正直又盡責的消防員，卻為了拯救一個小女孩而葬身火窟。來自地府的三位陰間使者出現在他面前，指引他通往死後的世界。這三位陰間使者分別是：「領袖」江林公子（河正宇飾演）、「護衛」解怨脈（朱智勳飾演）、「助手」李德春（金香起飾演）。作為19年來的第一位「模範死者」，他們告訴金自鴻，如果想要「轉世」，他就必須在七七四十九天之內，通過七場審判。\n" + " \n" + "三位陰間使者積極幫助金自鴻轉世，因為他們和閻羅王有過協定──如果能在1000年內引渡49個靈魂轉世，他們自己也能輪迴轉世，而金自鴻是他們經手的第48位有機會轉世的備選亡魂。\n" + " \n" + "審判依據七大罪來評斷，包括：「謀殺、怠惰、欺騙、不公正、背叛、暴力、不孝」。金自鴻回想著自己的人生，他覺得自己應該能順利通過大部分的審判。而且他發現，如果能通過這些審判，他就能在轉世之前，託夢給在陽世的人。金自鴻非常希望能託夢給年邁的母親，於是他下定決心，一定要通過七大審判。\n" + " \n" + "就在金自鴻進行審判的同時，他的弟弟蘇鴻（金東昱飾演）竟在軍中服役時意外死亡。因為充滿憤怒與不甘，蘇鴻成為無法超生、徘徊在陽間的冤鬼。這個冤鬼的出現，同時震撼了死後的世界，並影響到金自鴻在陰間的審判。當江林公子來到人間調查蘇鴻冤死實情的同時，金自鴻的人生也不斷被揭露，讓他愈來愈難在審判中證明自己的清白。金自鴻最後的命運會如何？他能否還有機會託夢給母親，並成功轉世為人呢？\n" + " \n" + "【關於電影】\n" + " \n" + "6年籌備拍攝， 400億韓圜製作預算，南韓年度話題強片\n" + "繼《屍速列車》、《軍艦島》之後，又一刷新南韓影史製作預算的年度強片《與神同行》，即將在12月22日於台灣盛大上映！本片改編自漫畫家周浩旻的同名人氣漫畫，故事笑中帶淚，以陰間世界諷刺陽間，強烈翻轉人們對於地府的既定印象，並在神與人之間探討命運的牽連，深受各年齡層讀者喜愛。這也讓本作不僅成為入口網站「Naver」瀏覽次數最高的漫畫作品，推出實體紙本漫畫之後，更狂銷超過45萬冊，並接連榮獲「富川國際漫畫獎」優秀故事漫畫、「韓國漫畫大賞」總統獎、「讀者漫畫大獎」等獎項肯定，在漫畫界享有高度聲譽，原著漫畫並在LINE WEBTOON連載當中。\n" + " \n" + "本作如今翻拍成電影，製作團隊更花費6年時間籌備製作，並斥資400億韓圜（約新台幣10.8億元）預算，先後在首爾、京畿道、釜山、安城、平昌、平澤、益州等150多個地方取景拍攝。此外，更請來曾為《狄仁傑之神都龍王》、《智取威虎山》等片打造特效的南韓第一大特效公司「DexterStudios」，將漫畫中各式各樣的地獄場面，栩栩如生地還原在大銀幕上。這也讓本片強勢超越《屍速列車》的100億韓圜，以及《軍艦島》的220億韓圜，成為南韓影史拍攝預算最高的話題強片。不僅如此，本片演員卡司也堪稱是近年韓片最完美、最整齊陣容。除了河正宇、車太鉉、朱智勳、金香起、李政宰領銜主演之外， EXO成員「D.O.」都敬秀（又譯都暻秀）、金東昱、馬東錫、吳達庶、林元熙、李浚赫、金海淑、金荷娜等人也將加盟演出。\n" + " \n" + "值得一提的是，本片在國際版權銷售上也有亮眼表現，並在各大電影市場展上掀起瘋狂的競價風潮。這也讓本片國際版權狂銷台灣、香港、澳門、新加坡、馬來西亞、印尼、汶萊、菲律賓、柬埔寨、寮國、美國、加拿大、歐洲、拉丁美洲、大洋洲等100多國，更在亞洲各國創出韓片影史對外銷售的最高版權金額。許多海外買家紛紛表示：「這是從來沒出現過、非常有創意的南韓電影，不僅有華麗視覺和演員陣容，還有感人的故事，預計將在亞洲各國、甚至是全世界取得巨大成功。」也讓本片勢必成為今年最重要的南韓電影，值得觀眾進戲院一同震撼！");
//			movieVO2.setMovie_url("https://www.youtube.com/embed/ES4f-wkeTqg");
//			movieVO2.setMovie_stat(true);
//			dao.update(movieVO2);
//			
//			System.out.println("修改成功");
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 查詢
		Long x= 4000000001L;
		MovieVO empVO3 = dao.findByPrimaryKey(x);
		System.out.print(empVO3.getMovie_id() + ",");
		System.out.print(empVO3.getMovie_name_ch() + ",");
		System.out.print(empVO3.getMovie_name_en() + ",");
		System.out.print(empVO3.getMovie_poster() + ",");
		System.out.print(empVO3.getMovie_rating() + ",");
		System.out.print(empVO3.getMovie_date() + ",");
		System.out.print(empVO3.getMovie_time() + ",");
		System.out.print(empVO3.getMovie_company() + ",");
		System.out.print(empVO3.getMovie_director() + ",");
		System.out.print(empVO3.getMovie_actor() + ",");
		System.out.print(empVO3.getMovie_introduce() + ",");
		System.out.print(empVO3.getMovie_url() + ",");
		System.out.println(empVO3.getMovie_stat());
		System.out.println("---------------------");
		
		// 查詢
//		List<MovieVO> list = dao.getAll();
//		for (MovieVO aMovie : list) {
//			System.out.print(aMovie.getMovie_id() + ",");
//			System.out.print(aMovie.getMovie_name_ch() + ",");
//			System.out.print(aMovie.getMovie_name_en() + ",");
//			System.out.print(aMovie.getMovie_poster() + ",");
//			System.out.print(aMovie.getMovie_rating() + ",");
//			System.out.print(aMovie.getMovie_date() + ",");
//			System.out.print(aMovie.getMovie_time() + ",");
//			System.out.print(aMovie.getMovie_company() + ",");
//			System.out.print(aMovie.getMovie_director() + ",");
//			System.out.print(aMovie.getMovie_actor() + ",");
//			System.out.print(aMovie.getMovie_introduce() + ",");
//			System.out.print(aMovie.getMovie_url() + ",");
//			System.out.println(aMovie.getMovie_stat());
//			System.out.println();
//		}
		System.out.println("---------------------");
		
		//查詢某電影的場次
//		Set<Movie_ScheduleVO> set1 = dao.getScheduleByMovie(4000000006L);
//		for (Movie_ScheduleVO amovie_schedule : set1) {
//			System.out.print(amovie_schedule.getSchedule_id() + ",");
//			System.out.print(amovie_schedule.getMovie_id() + ",");
//			System.out.print(amovie_schedule.getHall_id() + ",");
//			System.out.print(amovie_schedule.getSchedule_date() + ",");
//			System.out.print(amovie_schedule.getSold_seat() + ",");
//			System.out.print(amovie_schedule.getSeat_stat() + ",");
//			System.out.print(amovie_schedule.getMidnight());
//			System.out.println();
//		}
//		System.out.println("---------------------");
		
		//查詢某電影的評論
//		Set<Movie_CommentVO> set2 = dao.getCommentByMovie(4000000006L);
//		for (Movie_CommentVO aMovie_Comment : set2) {
//			System.out.print(aMovie_Comment.getComment_id() + ",");
//			System.out.print(aMovie_Comment.getMovie_id() + ",");
//			System.out.print(aMovie_Comment.getMember_id() + ",");
//			System.out.print(aMovie_Comment.getComment_time() + ",");
//			System.out.print(aMovie_Comment.getComment_content() + ",");
//			System.out.print(aMovie_Comment.getComment_stat());
//			System.out.println();
//		}
	}

	// 使用byte[]方式
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
}

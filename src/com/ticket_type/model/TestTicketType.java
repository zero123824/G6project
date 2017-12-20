package com.ticket_type.model;


import java.io.*;
import java.util.List;

public class TestTicketType {
public static void main(String[] args) {
	TicketTypeDAO dao=new TicketTypeDAO();
	byte[] pic2;
	try {
//		新增
//		pic2 = null;
//		TicketTypeVO tt0=new TicketTypeVO();
//		tt0.setTicket_type_name("敬老票");
//		tt0.setTicket_type_food("");
//		tt0.setTicket_type_price(250);
//		tt0.setTicket_type_state(0);
//		tt0.setTicket_type_pic(pic2);
//		dao.insert(tt0);

		
		//修改(建假資料上傳圖片)
		pic2 = getPictureByteArray("DBPicsUpload/16000.jpg");	
		TicketTypeVO tt4=new TicketTypeVO();
		tt4.setTicket_type_no(16000);
		tt4.setTicket_type_name("A套餐");
		tt4.setTicket_type_food("1張電影票+1份爆米花+1杯可樂");
		tt4.setTicket_type_price(380);
		tt4.setTicket_type_state(0);
		tt4.setTicket_type_pic(pic2);
		System.out.println("更新"+dao.update(tt4)+"筆資料");
//		
//		pic2 = getPictureByteArray("DBPicsUpload/16001.jpg");	
//		TicketTypeVO tt5=new TicketTypeVO();
//		tt5.setTicket_type_no(16001);
//		tt5.setTicket_type_name("B套餐");
//		tt5.setTicket_type_food("1張電影票+1份爆米花+1杯可樂+1份吉拿棒");
//		tt5.setTicket_type_price(420);
//		tt5.setTicket_type_state(0);
//		tt5.setTicket_type_pic(pic2);
//		System.out.println("更新"+dao.update(tt5)+"筆資料");
//		
//		pic2 = getPictureByteArray("DBPicsUpload/16002.jpg");	
//		TicketTypeVO tt6=new TicketTypeVO();
//		tt6.setTicket_type_no(16002);
//		tt6.setTicket_type_name("C套餐");
//		tt6.setTicket_type_food("1張電影票+1份爆米花+1杯可樂+1份熱狗堡");
//		tt6.setTicket_type_price(420);
//		tt6.setTicket_type_state(0);
//		tt6.setTicket_type_pic(pic2);
//		System.out.println("更新"+dao.update(tt6)+"筆資料");	

		
		//刪除
//		dao.delete(16004);
		
		//以PK查詢 (印出toString)
//		System.out.println(dao.findByPrimaryKey(16002).toString());
	
		//以PK查詢圖片，寫入資料夾	
//		byte[] x=dao.findByPrimaryKey(16002).getTicket_type_pic();
//		String picName=dao.findByPrimaryKey(16002).getTicket_type_name();
//		try {
//			OutputStream out = new FileOutputStream("DBPicsUpload/"+picName+"download.jpg");
//			out.write(x);
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//查詢全部
		List<TicketTypeVO> list=dao.getAll();
		
		for(TicketTypeVO aTtype:list){
			System.out.println(aTtype.toString());
			//如果有圖片，則輸出到資料夾(之後可改output到別的地方)
			if(aTtype.getTicket_type_pic()!=null){
			byte[] x=aTtype.getTicket_type_pic();
			OutputStream out=new FileOutputStream("DBPicsUpload/"+aTtype.getTicket_type_name()+"TEST.jpg");
			out.write(x);
			out.close();
			}
		 	}
		
	} catch (IOException e) {
		e.printStackTrace();
	}	
}

public static byte[] getPictureByteArray(String path) throws IOException {
	File file = new File(path);
	FileInputStream fis = new FileInputStream(file);
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	byte[] buffer = new byte[10];
	int i;
	while ((i = fis.read(buffer)) != -1) {
		baos.write(buffer, 0, i);
	}
	baos.close();
	fis.close();

	return baos.toByteArray();
}
}


package com.moive.controller;

import java.sql.*;
import java.io.*;

class PhotoWrite {

        static {
            try {
                 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        public static void main(String argv[]) {
              Connection con = null;
              PreparedStatement pstmt = null;
              String url = "jdbc:oracle:thin:@localhost:1521:XE"; //jdbc:oracle:thin:@localhost:1521:XE
              String userid = "ba105g6";
              String passwd = "ba105g6";
              InputStream fin = null;
	        
              try {
                con = DriverManager.getConnection(url, userid, passwd);
                	pstmt = con.prepareStatement("UPDATE MOVIE SET MOVIE_POSTER=? WHERE MOVIE_ID=?"); //SQL指令
              
                	long num = 4000000001L;
                	
                for(int i = 1; i <= 10; i++) {
                	
	                	File pic = new File("C://pic/pos/" + num + ".jpg"); //檔案路徑
	                	System.out.println(pic);
	                fin = new FileInputStream(pic);  
	                
	                	pstmt.setBinaryStream(1, fin, pic.length());
	                	pstmt.setLong(2,num);
	                	pstmt.executeUpdate();
	                	
	                	num++;
                }
                
                System.out.println("新增成功");
                
                fin.close();
                pstmt.close();

              } catch (Exception e) {
                    e.printStackTrace();
              } finally {
                    try {
                      con.close();
                    } catch (SQLException e) {
                    }
             }
      }
}
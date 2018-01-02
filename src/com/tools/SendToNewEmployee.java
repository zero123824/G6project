package com.tools;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.employee.model.EmployeeVO;

public class SendToNewEmployee {

	public SendToNewEmployee() {
	}
	
	public static void SendMail(EmployeeVO employeeVO,String password){
		String sendto = "zero123824@g.ncu.edu.tw";  //employeeVO.getEmp_email();
		String sendfrom = "ba105g6@gmail.com";
		String host = "smtp.gmail.com";
		int port = 587;
		String username  = "ba105g6@gmail.com";
		String psw = "ba105g6iii";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			    return new PasswordAuthentication(username, psw);
			}
		});
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendfrom));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendto));
			msg.setSubject("歡迎加入SEANKER影城");
//			msg.setText("Hello World!!!, \n\n 測試 測試 測試 測試 測試 測試 email !\n 密碼是:"+password);
			String mailcontent = "<h2>你好 "+employeeVO.getEmp_name()+"</h2>"
								+ "<div>歡迎加入SENAKER影城，成為我們的一分子</div>"
								+ "<b style='color:red'>你的密碼是:"+password+"</b>"
								+ "<div>用以登入員工操作後台，請妥善保管!</div><br>"
								+ "<div>By:SEANKER影城團隊</div>";
			msg.setContent(mailcontent,"text/html; charset=utf-8");
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, psw);
			
			Transport.send(msg);
			System.out.println("Success!!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

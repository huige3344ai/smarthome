package com.smarthome.util;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;  
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;  




public class SendEmail {
	public static final String HOST = "smtp.163.com";//服务器地址  
	public static final String PROTOCOL = "smtp";//通讯协议
	public static final int PORT = 25;//端口
	public static final String FROM = "pujj33@163.com";//发件人
	public static final String PWD = "wu6981658";//发件人密码
	
	/*
	 * 获取session
	 * @return
	 */
	private static Session getSession(){
		Properties  proerties = new Properties();
		proerties.put("mail.smtp.host", HOST);//设置服务器地址
		proerties.put("mail.store.protocol", PROTOCOL);//设置协议
		proerties.put("mail.smtp.prot", PORT);//设置端口	
		proerties.put("mail.smtp.auth", true);//是否需要身份证验证
		
		Authenticator authenTicator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(FROM,PWD);
			};
			
		};
		Session session = Session.getInstance(proerties, authenTicator);
		return session;
		}
	
	/*
	 * send msg
	 * 
	 */	
	public static void send (String toEmail,String content,String title){
		
		Session session = getSession();

			System.out.println("send:"+content);
			Message msg = new MimeMessage(session);//初始化一个message
			try {
				msg.setFrom(new InternetAddress(FROM));
				//set msg
				InternetAddress []internetAddress = {new InternetAddress(toEmail)};
				msg.setRecipients(Message.RecipientType.TO, internetAddress);
				msg.setSubject(title);//设置主题
				msg.setSentDate(new Date());
				msg.setContent(content, "text/html;charset=utf-8");
				//send msg
				Transport.send(msg);
				
			} catch (AddressException e) {
				e.printStackTrace();
				System.out.print("地址出错");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.print("信息异常");
				
			}			
			
		
	}
}

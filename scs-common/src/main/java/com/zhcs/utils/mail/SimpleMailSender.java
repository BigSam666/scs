package com.zhcs.utils.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.zhcs.utils.LogUtil;
import com.zhcs.utils.strRandom.RandomStr;

//*****************************************************************************
/**
* <p>Title:SimpleMailSender</p>
* <p>Description:邮件发送器</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
* @author 刘晓东 - Alter
* @version v1.0 2017年2月23日
*/
//*****************************************************************************
public class SimpleMailSender {    
    //*************************************************************************
    /** 
    * 【发送】文本格式邮件
    * @param 	mailInfo				邮件信息对象
    * @return	boolean					true:成功,false:失败
    * @throws 	Exception  				异常对象
    */
    //*************************************************************************
    public boolean sendTextMail(MailSenderInfo mailInfo) {    
    	try {
			//判断是否需要身份认证    
			MyAuthenticator authenticator = null;    
			Properties pro = mailInfo.getProperties();   
			if (mailInfo.isValidate()) {    
				//如果需要身份认证，则创建一个密码验证器    
				authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
			}   
			//根据邮件会话属性和密码验证器构造一个发送邮件的session    
			Session sendMailSession = Session.getDefaultInstance(pro,authenticator); 
			//根据session创建一个邮件消息    
			Message mailMessage = new MimeMessage(sendMailSession);    
			//创建邮件发送者地址    
			Address from = new InternetAddress(mailInfo.getFromAddress());    
			//设置邮件消息的发送者    
			mailMessage.setFrom(from);    
			//创建邮件的接收者地址，并设置到邮件消息中    
			Address to = new InternetAddress(mailInfo.getToAddress());    
			mailMessage.setRecipient(Message.RecipientType.TO,to);    
			//设置邮件消息的主题    
			mailMessage.setSubject(mailInfo.getSubject());    
			//设置邮件消息发送的时间    
			mailMessage.setSentDate(new Date());    
			//设置邮件消息的主要内容    
			String mailContent = mailInfo.getContent();    
			mailMessage.setText(mailContent);    
			//发送邮件    
			Transport.send(mailMessage); 
			LogUtil.info("发送成功！");
		} catch (AddressException e) {
			return false;
		} catch (MessagingException e) {
			return false;
		}
    	return true;    
    }    
       
    //*************************************************************************
    /** 
    * 【发送】HTML格式邮件
    * @param 	mailInfo				邮件信息对象
    * @return	boolean					true:成功，false:失败
     * @throws MessagingException 		消息异常
    */
    //*************************************************************************
    public boolean sendHtmlMail(MailSenderInfo mailInfo) throws MessagingException{    
    	//判断是否需要身份认证    
    	MyAuthenticator authenticator = null;   
    	Properties pro = mailInfo.getProperties();   
    	//如果需要身份认证，则创建一个密码验证器     
    	if (mailInfo.isValidate()) {    
    		authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
    	}    
    	//根据邮件会话属性和密码验证器构造一个发送邮件的session    
    	Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
    	//根据session创建一个邮件消息    
    	Message mailMessage = new MimeMessage(sendMailSession);    
    	//创建邮件发送者地址    
    	Address from = new InternetAddress(mailInfo.getFromAddress());    
    	//设置邮件消息的发送者    
    	mailMessage.setFrom(from);    
    	//创建邮件的接收者地址，并设置到邮件消息中    
    	Address to = new InternetAddress(mailInfo.getToAddress());    
    	//Message.RecipientType.TO属性表示接收者的类型为TO    
    	mailMessage.setRecipient(Message.RecipientType.TO,to);    
    	//设置邮件消息的主题    
    	mailMessage.setSubject(mailInfo.getSubject());    
    	//设置邮件消息发送的时间    
    	mailMessage.setSentDate(new Date());    
    	//MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
    	Multipart mainPart = new MimeMultipart();    
    	//创建一个包含HTML内容的MimeBodyPart    
    	BodyPart html = new MimeBodyPart();    
    	//设置HTML内容    
    	html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
    	mainPart.addBodyPart(html);    
    	//将MiniMultipart对象设置为邮件内容    
    	mailMessage.setContent(mainPart);    
    	//发送邮件    
    	Transport.send(mailMessage);    
    	LogUtil.info("mail Send success...");
    	return true;    
    }    
 
    //*************************************************************************
    /** 
    * 【发送】邮件
    * @param 	smtp					邮件服务器
    * @param 	port					端口
    * @param 	email					本邮箱账号
    * @param 	password				本邮箱密码
    * @param 	tomail					对方箱账号
    * @param 	title					标题
    * @param 	content					内容
    * @param 	type					类型：1：文本格式;2：HTML格式
    * @throws	Exception  				异常对象
    */
    //*************************************************************************
    public static void sendEmail(String smtp, String port, String email, String password, String tomail, String title, String content, String type) throws Exception { 
        //这个类主要是设置邮件   
	    MailSenderInfo mailInfo = new MailSenderInfo();
	    mailInfo.setMailServerHost(smtp);    
	    mailInfo.setMailServerPort(port);    
	    mailInfo.setValidate(true);    
	    mailInfo.setUserName(email);    
	    mailInfo.setPassword(password);   
	    mailInfo.setFromAddress(email);    
	    mailInfo.setToAddress(tomail);    
	    mailInfo.setSubject(title);    
	    mailInfo.setContent(content);    
	    //这个类主要来发送邮件   
	    SimpleMailSender sms = new SimpleMailSender();
	    if ("1".equals(type)) {
	    	sms.sendTextMail(mailInfo);
	    } else {
	    	sms.sendHtmlMail(mailInfo);
	    }
	}
    
    //*************************************************************************
    /** 
    * 【接口】邮件发送主接口。
    * @param Addressee 发送给谁
    * @param title 邮件标题
    * @param content 内容
    * @return
    * @throws Exception  
    */
    //*************************************************************************
    public static boolean sendEmail(String Addressee, String title, String content, Boolean textMail) throws MessagingException {
    	MailSenderInfo mailInfo = new MailSenderInfo();    
 	    mailInfo.setMailServerHost("smtp.163.com");    
 	    mailInfo.setMailServerPort("25");    
 	    mailInfo.setValidate(true);    
 	    mailInfo.setUserName("13027991217");    
 	    mailInfo.setPassword("liuxiaodong1025");//您的邮箱密码    
 	    mailInfo.setFromAddress("13027991217@163.com");    
 	    mailInfo.setToAddress(Addressee);    
 	    mailInfo.setSubject(title);    
 	    mailInfo.setContent(content); 
 	    LogUtil.info("content:{}", content);
 	    //这个类主要来发送邮件   
 	    SimpleMailSender sms = new SimpleMailSender();   
 	    if(textMail){
 	    	sms.sendTextMail(mailInfo);//发送文体格式    
 	    } else {
 	    	sms.sendHtmlMail(mailInfo);//发送html格式   
 	    }
 	    return true;
    }
    
    public static void main(String[] args) throws MessagingException {   
    	SimpleMailSender.sendEmail("404221903@qq.com", "测试邮件！", "<span style='color:red'>您的验证码是：" + RandomStr.randomStr(6) + "</span>" , false);
	}
}   
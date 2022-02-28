package com.buleqr.utils.email;

import com.buleqr.exception.ServiceException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class SimpleMailSender {
	/**
	 * 发送html格式邮件
	 * @param mail
	 * @return
	 */
	public boolean sendHtmlMail(MailSenderInfo mail){
		boolean result=false;
		//身份认证器
		MyAuthenticator authenticator = null;
		if(mail.isValidate()){
		//身份认证
		authenticator=new MyAuthenticator(mail.getUserName(), mail.getPassword());
		}
		Properties props=mail.getProperties();
		props.put("mail.smtp.ssl.enable", "true");//启用ssl
		//props.put("mail.smtp.socketFactory.class", "SSL_FACTORY"); // 使用JSSE的SSL
		// socketfactory来取代默认的socketfactory
		//props.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
		//获取session
		Session sendMailSession=Session.getDefaultInstance(props, authenticator);
		try {
			//根据session获得一个邮件消息
			Message mailMsg=new MimeMessage(sendMailSession);
			//设置邮件发送者
			Address from=new InternetAddress(mail.getFromAddress());
			mailMsg.setFrom(from);
			//设置接受者
			int len=mail.getToAddress().length;//接收者个数
			if (len==0) {
				throw new ServiceException("邮箱不能为空",400);
			}
			Address[] to=new InternetAddress[len];
			for(int i=0;i<len;i++){
				to[i]=new InternetAddress(mail.getToAddress()[i]);
			}
			//Message.RecipientType.TO意思是接收者to的类型
			mailMsg.setRecipients(Message.RecipientType.TO, to);
			//设置邮件主题
			mailMsg.setSubject(mail.getSubject());
			//设置邮件时间
			mailMsg.setSentDate(new Date());
			//创建一个容器
			Multipart mainPart=new MimeMultipart();
			//创建一个包含html内容的MimeBodyPart
			BodyPart html=new MimeBodyPart();
			html.setContent(mail.getContent(),"text/html;charset=utf-8");
			mainPart.addBodyPart(html);
			//设置附件
			//创建一个附件容器
			//创建一个包含附件内容的MimeBodyPart
			String[] attachs=mail.getAttachs();
			if(attachs!=null){
				int size=attachs.length;//获得附件个数
				if(size>0){//如果附件个数大于0
					for(int i=0;i<size;i++){
						BodyPart attach=new MimeBodyPart();
						DataSource source=new FileDataSource(attachs[i]);
						attach.setDataHandler(new DataHandler(source));
						File temp=new File(mail.getAttachs()[i]);
						String fileName=temp.getName();//这里根据全路径获取文件名称
						//设置附件文件名并且编码，否则中文会乱码
						attach.setFileName(MimeUtility.encodeText(fileName));
						mainPart.addBodyPart(attach);
					}
				}
			}
			mailMsg.setContent(mainPart);

			//发送
			Transport.send(mailMsg);
			result=true;
		} catch (AddressException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}


		return result;
	}
}

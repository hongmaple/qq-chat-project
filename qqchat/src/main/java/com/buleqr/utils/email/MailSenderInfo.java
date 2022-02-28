package com.buleqr.utils.email;

import java.util.Properties;

public class MailSenderInfo {
	//邮件服务器地址
	private String mailServiceHost;
	//邮件服务器端口号
	private String mailServicePort;
	 // 是否需要身份验证    
    private boolean validate = true;    
	
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	//发件人
	private String fromAddress;
	//收件人可以多个
	private String[] toAddress;
	//附件可以多个
	private String[] attachs=null;
	//主题
	private String subject;
	//用户名
	private String userName;
	//密码
	private String password;
	//内容
	private String content;
	
	 /**   
     * 获得邮件会话属性   
     */    
   public Properties getProperties(){    
     Properties p = new Properties();    
     p.put("mail.smtp.host", this.mailServiceHost);    
     p.put("mail.smtp.port", this.mailServicePort);    
     p.put("mail.smtp.auth", validate ? "true" : "false");    
     return p;    
   }    
	public String getMailServiceHost() {
		return mailServiceHost;
	}
	public void setMailServiceHost(String mailServiceHost) {
		this.mailServiceHost = mailServiceHost;
	}
	public String getMailServicePort() {
		return mailServicePort;
	}
	public void setMailServicePort(String mailServicePort) {
		this.mailServicePort = mailServicePort;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getToAddress() {
		return toAddress;
	}
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachs() {
		return attachs;
	}
	public void setAttachs(String[] attachs) {
		this.attachs = attachs;
	}
	
}

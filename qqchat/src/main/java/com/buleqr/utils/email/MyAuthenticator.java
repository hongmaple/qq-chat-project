package com.buleqr.utils.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮箱帐号验证类
 * @author Administrator
 *
 */
public class MyAuthenticator extends Authenticator {
	private String userName;
	private String password;
	public MyAuthenticator() {
	
	}
	public MyAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	/**
	 * 重写验证方法
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return new PasswordAuthentication(userName,password);
	}
	
	
}

package com.buleqr.utils;

public enum ErrorMsg {
 
	TEST(1, "test"),
	SUCCESS(200, "请求成功"),
	LOGIN_FAIL(101, "登录失败"),
	LOGOUT_SUCCESS(104, "登出成功"),
	USER_NOT_FOUND(102, "用户不存在"),
	USER_DISABLED(103, "无效用户"),
	USER_PASSWORD_ERROR(107, "密码错误"),
	NO_LOGIN(105, "未登录"),
	VERIFY_CODE_ERROR(106, "验证码错误"),
	SYSTEM_ERROR(100, "系统错误");
	
	private int code;
	private String message;
 
	ErrorMsg(int code, String message) {
		this.code = code;
		this.message = message;
	}
 
	public int getCode() {
		return code;
	}
 
	public String getMessage() {
		return message;
	}
 
}
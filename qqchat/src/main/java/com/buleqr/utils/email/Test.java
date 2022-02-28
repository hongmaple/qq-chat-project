package com.buleqr.utils.email;

public class Test {

	public static void main(String[] args) {
			MailSenderInfo info=new MailSenderInfo();
		info.setMailServiceHost("smtp.exmail.qq.com");//qq邮箱服务器
		info.setMailServicePort("465");//端口号
		info.setFromAddress("team@buleqr.com");
		info.setValidate(true);
		info.setUserName("team@buleqr.com");
		info.setPassword("k8Pvt8aaw6YNBEsk");
		String[] to={"2496155694@qq.com"};
		info.setToAddress(to);
		info.setSubject("邮件发送测试");
		info.setContent("<h4>测试内容<a href='http://www.baidu.com'>百度</a></h4>");
		String[] attach={"D:\\shihuo\\buleqr-project\\buleqr\\uploadPath\\upload\\1.png"};
		info.setAttachs(attach);
		
		SimpleMailSender sender=new SimpleMailSender();
		boolean b=sender.sendHtmlMail(info);
		if(b){
			System.out.println("成功！");
		}else{
			System.out.println("失败！");
		}
	}

}

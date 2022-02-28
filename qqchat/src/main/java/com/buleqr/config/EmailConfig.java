package com.buleqr.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * cms模块相关配置
 * 
 * @author buleqr
 */
@Component
@PropertySource(value = { "classpath:email.properties" })
public class EmailConfig
{

    public static String mailHost;

    public static String mailPort;
    
    /**
     *邮件发送服务
     */
    public static String  fromEmail;
    /**
     * 邮件发送服务
     */
    public static String fromEmailPwd;
    @Value("${cms.email.host}")
    public  void setMailHost(String mailHost) {
        EmailConfig.mailHost = mailHost;
    }
    @Value("${cms.email.port}")
    public  void setMailPort(String mailPort) {
        EmailConfig.mailPort = mailPort;
    }

    @Value("${cms.email.fromEmail}")
    public  void setFromEmail(String fromEmail) {
        EmailConfig.fromEmail = fromEmail;
    }
    @Value("${cms.email.fromEmailPwd}")
    public  void setFromEmailPwd(String fromEmailPwd) {
        EmailConfig.fromEmailPwd = fromEmailPwd;
    }

    public static String getMailHost() {
        return mailHost;
    }



    public static String getMailPort() {
        return mailPort;
    }



    public static String getFromEmail() {
        return fromEmail;
    }

    public static String getFromEmailPwd() {
        return fromEmailPwd;
    }


}

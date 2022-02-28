package com.buleqr.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.buleqr.config.EmailConfig;
import com.buleqr.service.VerificationCodeManager;
import com.buleqr.utils.email.MailSenderInfo;
import com.buleqr.utils.email.SimpleMailSender;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Service
public class VerificationCodeManagerImpl implements VerificationCodeManager {

    private final Duration timeout = Duration.ofMinutes(5);

    private final StringRedisTemplate redisTemplate;

    private final String keyPrefix = "buleqr_email_code";

    public VerificationCodeManagerImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean sendEmailCode(String email) {
        MailSenderInfo info=new MailSenderInfo();
        info.setMailServiceHost(EmailConfig.getMailHost());//qq邮箱服务器
        info.setMailServicePort(EmailConfig.getMailPort());//端口号
        info.setFromAddress(EmailConfig.getFromEmail());
        info.setValidate(true);
        info.setUserName(EmailConfig.getFromEmail());
        info.setPassword(EmailConfig.getFromEmailPwd());
        String [] emails= {email};
        info.setToAddress(emails);
        info.setSubject("邮箱验证码");
        String value = RandomUtil.randomNumbers(6);
        String content="您的邮箱验证码为："+value+" ,5分钟内有效";
        info.setContent(content);
        SimpleMailSender sender=new SimpleMailSender();
        boolean b=sender.sendHtmlMail(info);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = keyPrefix + email;
        valueOperations.set(key, value, timeout);
        return b;
    }

    @Override
    public boolean checkVerificationCode(String target, String vCode) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = keyPrefix + target;
        String value = valueOperations.get(key);
        if(StringUtils.hasText(value) && value.equals(vCode)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }

}

package com.buleqr.service;

public interface VerificationCodeManager {
    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    boolean sendEmailCode(String email);

    boolean checkVerificationCode(String target, String vCode);
}

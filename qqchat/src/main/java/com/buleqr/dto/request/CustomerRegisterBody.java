package com.buleqr.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerRegisterBody {

    /**
     * 用邮箱登录
     */
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String verificationCode ;
}

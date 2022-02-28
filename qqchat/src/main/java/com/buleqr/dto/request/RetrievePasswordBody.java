package com.buleqr.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 找回密码
 * @author buleqr
 */
@Data
public class RetrievePasswordBody {
    /**
     * 用邮箱登录
     */
    @NotNull(message = "邮箱不能为空")
    private String email;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String verificationCode ;

    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    @NotNull(message = "确认密码不能为空")
    private String password1;
}

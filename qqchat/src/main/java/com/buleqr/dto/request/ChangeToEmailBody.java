package com.buleqr.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 换绑邮箱
 * @author buleqr
 */
@Data
public class ChangeToEmailBody {
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
}

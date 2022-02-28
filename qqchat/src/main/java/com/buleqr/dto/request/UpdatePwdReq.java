package com.buleqr.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePwdReq {
    @NotNull(message = "原密码不能为空")
    private String password;
    @NotNull(message = "新密码不能为空")
    private String password1;
    @NotNull(message = "确认密码不能为空")
    private String password2;
}

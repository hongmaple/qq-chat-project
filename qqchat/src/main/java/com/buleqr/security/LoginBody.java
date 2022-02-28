package com.buleqr.security;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户登录对象
 */
@Data
public class LoginBody
{
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "用户密码不能为空")
    private String password;
}
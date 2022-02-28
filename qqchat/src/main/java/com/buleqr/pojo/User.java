package com.buleqr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.buleqr.enums.UserState;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author buleqr
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用邮箱登录
     */
    @NotNull(message = "邮箱不能为空")
    private String email;
    /**
     * 角色
     */
    private String role;
    /**
     * 头像地址
     */
    private String avatarImage;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建者
     */
    public Long creatorId;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Date updateTime;

    /**
     * 总打卡次数
     */
    public Integer clockFrequency;

    /**
     * 用户状态
     * @see UserState
     */
    public UserState status;

    /**
     * 用户类型，0：C端，1：后台
     */
    public Integer userType;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    public Boolean isDeleted;

}
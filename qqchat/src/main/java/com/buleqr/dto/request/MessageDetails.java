package com.buleqr.dto.request;

import lombok.Data;

import java.util.Date;

/**
 * 聊天记录
 */
@Data
public class MessageDetails {

    /**
     * 消息内容
     */
    private String contentText;
    /**
     * 发送人的头像
     */
    private String fromAvatarImage;
    /**
     * 发送人的昵称
     */
    private String fromUserName;
    /**
     * 发送人的id
     */
    private Long fromUserId;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 接收者的id
     */
    private Long toUserId;
    /**
     * 接收者的昵称
     */
    private String toUsername;
    /**
     * 接收者的头像
     */
    private String toAvatarImage;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 文件后缀
     */
    private String exp;

}

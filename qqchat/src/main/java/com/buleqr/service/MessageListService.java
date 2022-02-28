package com.buleqr.service;

import com.buleqr.dto.request.MessageDetails;

import java.util.List;

public interface MessageListService {

    /**
     * 新增保存消息
     * @param messageDetails 消息
     * @return 结果
     */
    Boolean addMessage(MessageDetails messageDetails);

    /**
     * 加载聊天列表
     * @return 聊天列表
     */
    List<MessageDetails> getMessageList();

    /**
     * 加载聊天详情
     * @return 聊天详情
     */
    List<MessageDetails> getMessageDetailsList(Long toUserId);
}

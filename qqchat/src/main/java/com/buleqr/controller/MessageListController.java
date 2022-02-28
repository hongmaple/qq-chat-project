package com.buleqr.controller;

import com.alibaba.fastjson.JSONObject;
import com.buleqr.dto.request.MessageDetails;
import com.buleqr.exception.ServiceException;
import com.buleqr.pojo.AjaxResult;
import com.buleqr.pojo.User;
import com.buleqr.security.JwtUser;
import com.buleqr.service.MessageListService;
import com.buleqr.utils.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 聊天列表
 *
 * @author buleqr
 */
@Slf4j
@RestController
@RequestMapping("/messageList")
public class MessageListController {
    private final MessageListService messageListService;

    public MessageListController(MessageListService messageListService) {
        this.messageListService = messageListService;
    }

    /**
     * 新增聊天对象
     * @param messageDetails 参数
     * @return
     */
    @PostMapping
    public AjaxResult addMessage(@RequestBody MessageDetails messageDetails) {
        AjaxResult ajaxResult = AjaxResult.success("聊天对象保存成功",messageListService.addMessage(messageDetails));
        return ajaxResult;
    }

    /**
     * 加载聊天列表
     * @return
     */
    @GetMapping
    public AjaxResult getMessageList() {
        AjaxResult ajaxResult = AjaxResult.success("加载聊天列表成功",messageListService.getMessageList());
        return ajaxResult;
    }

    /**
     * 加载详情
     * @return
     */
    @GetMapping("/{toUserId}")
    public AjaxResult getMessageDetailsList(@PathVariable Long toUserId) {
        AjaxResult ajaxResult = AjaxResult.success("加载聊天详情",messageListService.getMessageDetailsList(toUserId));
        return ajaxResult;
    }

}

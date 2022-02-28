package com.buleqr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.buleqr.dao.UserDao;
import com.buleqr.dto.request.MessageDetails;
import com.buleqr.exception.ServiceException;
import com.buleqr.pojo.User;
import com.buleqr.security.JwtUser;
import com.buleqr.service.MessageListService;
import com.buleqr.utils.file.FileUploadUtils;
import com.buleqr.utils.security.SecurityUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 消息保存
 */
@Service
public class MessageListServiceImpl implements MessageListService {
    private final StringRedisTemplate redisTemplate;
    private final UserDao userDao;

    public MessageListServiceImpl(StringRedisTemplate redisTemplate, UserDao userDao) {
        this.redisTemplate = redisTemplate;
        this.userDao = userDao;
    }

    @Override
    public Boolean addMessage(MessageDetails messageDetails) {
        JwtUser loginUser = SecurityUtils.getLoginUser();
        if (userDao.lambdaQuery().eq(User::getId,loginUser.getId()).count()==0) {
            throw new ServiceException("请先登录",400);
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        List<MessageDetails> fromUserMessageList= JSONObject.parseArray(
                valueOperations.get("messageDetail:"+messageDetails.getFromUserId()+":"+ messageDetails.getToUserId()),MessageDetails.class);
        if (fromUserMessageList!=null) {
            fromUserMessageList.add(messageDetails);
        }else {
            List<MessageDetails> newFromUserMessageList = new ArrayList<>();
            newFromUserMessageList.add(messageDetails);
            fromUserMessageList = newFromUserMessageList;
        }
        valueOperations.set("messageDetail:"+messageDetails.getFromUserId()+":"+ messageDetails.getToUserId(),JSONObject.toJSONString(fromUserMessageList));

        List<MessageDetails> toUserMessageDetailList= JSONObject.parseArray(
                valueOperations.get("messageDetail:"+messageDetails.getToUserId()+":"+ messageDetails.getFromUserId()),MessageDetails.class);
        if (toUserMessageDetailList!=null) {
            toUserMessageDetailList.add(messageDetails);
        }else {
            List<MessageDetails> newFromUserMessageList = new ArrayList<>();
            newFromUserMessageList.add(messageDetails);
            toUserMessageDetailList = newFromUserMessageList;
        }
        valueOperations.set("messageDetail:"+messageDetails.getToUserId()+":"+ messageDetails.getFromUserId(),JSONObject.toJSONString(toUserMessageDetailList));


        boolean isFile = messageDetails.getMsgType().equals("file");
        if(isFile) {
            String[] split = messageDetails.getContentText().split("/");
            if (Arrays.asList(FileUploadUtils.IMAGEEXTENSION).contains(messageDetails.getExp())) {
                messageDetails.setContentText("[图片]"+split[split.length-1]);
            }else if (Arrays.asList(FileUploadUtils.VIDEOEXTENSION).contains(messageDetails.getExp())) {
                messageDetails.setContentText("[视频]"+split[split.length-1]);
            }else if (Arrays.asList(FileUploadUtils.DOCUMENTEXTENSION).contains(messageDetails.getExp())) {
                messageDetails.setContentText("[文档]"+split[split.length-1]);
            }else {
                messageDetails.setContentText("[文件]"+split[split.length-1]);
            }

        }
        List<MessageDetails> resultMessageList= JSONObject.parseArray(
                valueOperations.get("messagesList:"+loginUser.getId()),MessageDetails.class);
        List<MessageDetails> newMessagesList = new ArrayList<>();
        if(resultMessageList!=null) {
            Boolean isNewLt = false;
            for(int i = 0; i < resultMessageList.size(); i++){
                MessageDetails oldMessage = resultMessageList.get(i);
                if (oldMessage.getFromUserId().equals(messageDetails.getFromUserId())&&
                        oldMessage.getToUserId().equals(messageDetails.getToUserId())) {
                    oldMessage=messageDetails;
                    resultMessageList.remove(i);
                    resultMessageList.add(oldMessage);
                }else {
                    isNewLt = true;
                }
            }
            if (isNewLt) {
                resultMessageList.add(messageDetails);
            }
        }else {
            newMessagesList.add(messageDetails);
            resultMessageList = newMessagesList;
        }
        valueOperations.set("messagesList:"+loginUser.getId(), JSONObject.toJSONString(resultMessageList));

        List<MessageDetails> newToMessageList = new ArrayList<>();
        for (int k = 0;k < resultMessageList.size();k++) {
            MessageDetails loginMessage = resultMessageList.get(k);
            List<MessageDetails> toUserMessageList= JSONObject.parseArray(
                    valueOperations.get("messagesList:"+loginMessage.getToUserId()),MessageDetails.class);
            if(toUserMessageList!=null) {
                Boolean isAddLtduix = false;
                for(int l = 0;l < toUserMessageList.size();l++) {
                    MessageDetails toMessage = toUserMessageList.get(l);
                    if(toMessage.getToUserId().equals(loginMessage.getFromUserId())&&toMessage.getFromUserId().equals(loginMessage.getToUserId())) {
                        toMessage.setContentText(loginMessage.getContentText());
                        toMessage.setSendTime(loginMessage.getSendTime());
                        toMessage.setToUserId(loginMessage.getFromUserId());
                        toMessage.setToUsername(loginMessage.getFromUserName());
                        toMessage.setToAvatarImage(loginMessage.getFromAvatarImage());
                        toUserMessageList.remove(l);
                        toUserMessageList.add(toMessage);
                    }else {
                        isAddLtduix = true;
                    }
                }
                if (isAddLtduix) {
                    MessageDetails newMessageDetails = new MessageDetails();
                    newMessageDetails.setFromUserId(loginMessage.getToUserId());
                    newMessageDetails.setFromUserName(loginMessage.getToUsername());
                    newMessageDetails.setFromAvatarImage(loginMessage.getToAvatarImage());
                    newMessageDetails.setToUserId(loginMessage.getFromUserId());
                    newMessageDetails.setToUsername(loginMessage.getFromUserName());
                    newMessageDetails.setToAvatarImage(loginMessage.getFromAvatarImage());
                    newMessageDetails.setContentText(loginMessage.getContentText());
                    newMessageDetails.setSendTime(loginMessage.getSendTime());
                    toUserMessageList.add(newMessageDetails);
                }
            }else {
                MessageDetails newMessageDetails = new MessageDetails();
                newMessageDetails.setFromUserId(loginMessage.getToUserId());
                newMessageDetails.setFromUserName(loginMessage.getToUsername());
                newMessageDetails.setFromAvatarImage(loginMessage.getToAvatarImage());
                newMessageDetails.setToUserId(loginMessage.getFromUserId());
                newMessageDetails.setToUsername(loginMessage.getFromUserName());
                newMessageDetails.setToAvatarImage(loginMessage.getFromAvatarImage());
                newMessageDetails.setContentText(loginMessage.getContentText());
                newMessageDetails.setSendTime(loginMessage.getSendTime());
                newToMessageList.add(newMessageDetails);
                toUserMessageList = newToMessageList;
            }
            valueOperations.set("messagesList:"+loginMessage.getToUserId(), JSONObject.toJSONString(toUserMessageList));
        }
        return true;
    }

    @Override
    public List<MessageDetails> getMessageList() {
        JwtUser loginUser = SecurityUtils.getLoginUser();
        if (userDao.lambdaQuery().eq(User::getId,loginUser.getId()).count()==0) {
            throw new ServiceException("请先登录",400);
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        List<MessageDetails> resultMessageList= JSONObject.parseArray(
                valueOperations.get("messagesList:"+loginUser.getId()),MessageDetails.class);
        if (resultMessageList==null) {
            return null;
        }
        //单个属性排序，按title字符串排序
        List<MessageDetails> sendTimeSortList = resultMessageList.stream().collect(
                Collectors.toMap(MessageDetails::getToUserId, Function.identity(), (oldObj, newObj) -> {
                    if (newObj.getSendTime().getTime() > oldObj.getSendTime().getTime()) {
                        return newObj;
                    }
                    return oldObj;
                })).values().stream()
                .sorted(Comparator.comparing(MessageDetails::getSendTime))
                .collect(Collectors.toList());
        sendTimeSortList.forEach(messageDetails -> {
            User toUser = userDao.getById(messageDetails.getToUserId());
            User fromUser = userDao.getById(messageDetails.getFromUserId());
            if (Objects.nonNull(toUser)) {
                messageDetails.setToUsername(toUser.getUsername());
                messageDetails.setToAvatarImage(toUser.getAvatarImage());
            }
            if (Objects.nonNull(fromUser)) {
                messageDetails.setFromUserName(messageDetails.getFromUserName());
                messageDetails.setFromAvatarImage(messageDetails.getFromAvatarImage());
            }
        });
        valueOperations.set("messagesList:"+loginUser.getId(), JSONObject.toJSONString(sendTimeSortList));
        return sendTimeSortList;
    }

    @Override
    public List<MessageDetails> getMessageDetailsList(Long toUserId) {
        JwtUser loginUser = SecurityUtils.getLoginUser();
        if (userDao.lambdaQuery().eq(User::getId,loginUser.getId()).count()==0) {
            throw new ServiceException("请先登录",400);
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        List<MessageDetails> resultMessageList= JSONObject.parseArray(
                valueOperations.get("messageDetail:"+loginUser.getId()+":"+toUserId),MessageDetails.class);
        return resultMessageList;
    }
}

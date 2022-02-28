package com.buleqr.pojo;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Objects;

/**
 * 操作消息提醒
 * 
 * @author buleqr
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "status";

    /** 返回内容 */
    public static final String MSG_TAG = "message";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param status 状态码
     * @param message 返回内容
     */
    public AjaxResult(int status, String message)
    {
        super.put(CODE_TAG, status);
        super.put(MSG_TAG, message);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param status 状态码
     * @param message 返回内容
     * @param data 数据对象
     */
    public AjaxResult(int status, String message, Object data)
    {
        super.put(CODE_TAG, status);
        super.put(MSG_TAG, message);
        if (Objects.nonNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param message 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String message)
    {
        return AjaxResult.success(message, null);
    }

    /**
     * 返回成功消息
     * 
     * @param message 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String message, Object data)
    {
        return new AjaxResult(HttpStatus.OK.value(), message, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param message 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String message)
    {
        return AjaxResult.error(message, null);
    }

    /**
     * 返回错误消息
     * 
     * @param message 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String message, Object data)
    {
        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }

    /**
     * 返回错误消息
     * 
     * @param status 状态码
     * @param message 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int status, String message)
    {
        return new AjaxResult(status, message, null);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
package com.buleqr.exception;

/**
 * 业务异常
 * 
 * @author buleqr
 */
public final class ServiceException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer status;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 资源
     */
    private Object data;

    /**
     * 错误明细，内部调试错误
     *
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException()
    {
    }

    /**
     * 封装异常
     * @param message 消息
     */
    public ServiceException(String message)
    {
        this.message = message;
    }

    /**
     * 封装异常
     * @param message 消息
     * @param status 错误码
     */
    public ServiceException(String message, Integer status)
    {
        this.message = message;
        this.status = status;
        this.data=null;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return status;
    }

    public ServiceException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public ServiceException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}
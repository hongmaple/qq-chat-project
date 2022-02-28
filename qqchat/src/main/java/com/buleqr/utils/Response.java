package com.buleqr.utils;

import com.buleqr.utils.ErrorMsg;

import java.util.HashMap;
import java.util.Map;
 
public class Response {
	private static final String OK = "ok";
	private static final String Error = "error";
	private Meta meta;
	private Object data;
 
	/**
	 * 存在复杂组合数据对象或者没有数据对象的成功响应
	 * 
	 * @return
	 */
	public Response success() {
		this.meta = new Meta(true, OK);
		return this;
	}
 
	/**
	 * 存在单个包装数据对象的成功响应
	 * 
	 * @param data
	 * @return
	 */
	public Response success(Object data) {
		this.meta = new Meta(true, OK);
		this.data = data;
		return this;
	}
 
	/**
	 * 没有详情提示的失败响应
	 * 
	 * @return
	 */
	public Response failure() {
		this.meta = new Meta(false, Error);
		return this;
	}
 
	/**
	 * 存在详情提示的失败响应
	 * 
	 * @param message
	 * @return
	 */
	public Response failure(String message) {
		this.meta = new Meta(false, message);
		return this;
	}
 
	/**
	 * 存在详情提示的失败响应
	 * 
	 * @param errorMsg
	 * @return
	 */
	public Response failure(ErrorMsg errorMsg) {
		this.meta = new Meta(false, errorMsg);
		return this;
	}
 
	/**
	 * 向data中放入组合数据结果集（[Object | List | Map | Set | ...]）
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Response put(String key, Object value) {
		if (data == null || !(data instanceof Map)) {
			data = new HashMap<Object, Object>();
		}
		((Map<Object, Object>) data).put(key, value);
		return this;
	}
 
	/**
	 * 针对没有返回数据结果集的及早求值操作
	 * 
	 * @return
	 */
	public Map<Object, Object> toSimpleResult() {
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("meta", meta);
		return result;
	}
 
	/**
	 * 针对有返回数据结果集的及早求值操作
	 * 
	 * @return
	 */
	public Map<Object, Object> toCombineResult() {
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("meta", meta);
		result.put("data", data);
		return result;
	}
 
	class Meta {
		private int code;
		private boolean success;
		private String message;
 
		public Meta(boolean success) {
			this.code = ErrorMsg.SYSTEM_ERROR.getCode();
			this.success = success;
			this.message = ErrorMsg.SYSTEM_ERROR.getMessage();
		}
 
		public Meta(boolean success, String message) {
			this(success);
			this.message = message;
		}
 
		public Meta(boolean success, ErrorMsg errorMsg) {
			this(success, errorMsg.getMessage());
			this.code = errorMsg.getCode();
		}
 
		public int getCode() {
			return code;
		}
 
		public boolean isSuccess() {
			return success;
		}
 
		public String getMessage() {
			return message;
		}
 
		@Override
		public String toString() {
			return "Meta [code=" + code + ", success=" + success + ", message=" + message + "]";
		}
 
	}
 
	@Override
	public String toString() {
		return "Response [meta=" + meta + ", data=" + data + "]";
	}
}
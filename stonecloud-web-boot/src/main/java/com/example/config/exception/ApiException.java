package com.example.config.exception;

import com.alibaba.fastjson.JSONObject;

public class ApiException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private JSONObject result;
	
	public static JSONObject ERROR = new JSONObject();
	static{
		ERROR.put("code", "500");
		ERROR.put("message", "服务器发送未知错误");
		ERROR.put("result", new JSONObject());
	}
	
	public ApiException(JSONObject result) {
		super(result.toString());
		this.setResult(result);
	}
	
	/**
	 * 
	 * @param code		返回码
	 * @param message	返回码描述
	 */
	public ApiException(String code, String message) {
		super(message);
		JSONObject error = new JSONObject();
		error.put("code", code);
		error.put("message", message);
		error.put("result", new JSONObject());
		this.setResult(error);
	}
	
	public ApiException(String message) {
		super(message);
		JSONObject error = new JSONObject();
		error.put("message", message);
		error.put("result", new JSONObject());
		this.setResult(error);
		// TODO Auto-generated constructor stub
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}

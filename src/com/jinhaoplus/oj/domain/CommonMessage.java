package com.jinhaoplus.oj.domain;

public class CommonMessage {
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CommonMessage [code=" + code + ", message=" + message + "]";
	}
	public CommonMessage(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
}

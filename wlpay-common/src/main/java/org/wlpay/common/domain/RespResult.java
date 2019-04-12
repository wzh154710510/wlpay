package org.wlpay.common.domain;

import java.io.Serializable;

public class RespResult<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String message;
	
	private T detail;

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

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}
	
	private RespResult() {
		
	}
	
	private RespResult(String code,String message,T detail) {
		this.code=code;
		this.message=message;
		this.detail=detail;
	}
	
	public static <T>  RespResult<T> buildSuccessMessage(T object) {
		return new RespResult<T>("000000","请求成功",object);
	}
	
	public static <T>  RespResult<T> buildSuccessMessage() {
		return new RespResult<T>("000000","请求成功",null);
	}
	
	public static <T>  RespResult<T> buildErrorMessage() {
		return new RespResult<T>("999999","系统异常",null);
	}
	
	public static <T>  RespResult<T> buildErrorMessage(String message) {
		return new RespResult<T>("999999",message,null);
	}
	
	
}

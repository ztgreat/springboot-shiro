package com.springboot.shiro.base;

/**
 * 后台或者前台返回的统一对象
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class ResponseEntity<T> extends BaseResponseEntity {

	private T data;
	
	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

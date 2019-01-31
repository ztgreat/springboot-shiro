package com.springboot.shiro.base;

/**
 * 后台或者前台返回的统一对象
 * @param <T>
 */
public class ResponseEntity<T> {

	private String code;
	private String msg;
	private T data;
	private String bizCode;

	public ResponseEntity() {
		this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
	}

	public ResponseEntity(String s) {
		this.msg = s;
		this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
	}
	public ResponseEntity self(){
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseEntity setMsg(String msg) {
		this.msg = msg;
		return self();
	}

	public T getData() {
		return data;
	}

	public ResponseEntity setData(T data) {
		this.data = data;
		return self();
	}

	public ResponseEntity success(String msg) {
		this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
		this.msg = msg;
		return self();
	}

	public ResponseEntity success() {
		this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
		return self();
	}

	public ResponseEntity failure(String msg) {
		this.code = CommonConstant.ServerApp.SERVER_RET_FAILURE;
		this.msg = msg;
		return self();
	}

	public String getBizCode() {
		return bizCode;
	}

	public ResponseEntity setBizCode(String bizCode) {
		this.bizCode = bizCode;
		return self();
	}

}

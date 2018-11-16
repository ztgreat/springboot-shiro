package com.springboot.shiro.base;


public abstract class BaseResponseEntity {



	/**
	 * 0：成功 1：失败
	 */
	private String code=CommonConstant.ZERO;
	/**
	 * 返回的消息
	 */
	private String msg;

	public BaseResponseEntity() {
		this.code = CommonConstant.ZERO;
	}

	/**
	 * 成功
	 */
	public void setSuccess() {
		this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
	}

	/**
	 * 成功
	 * 
	 * @param msg
	 */
	public void setSuccess(String msg) {
		this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
		this.msg = msg;
	}

	/**
	 * 失败
	 * 
	 * @param msg
	 */
	public void setFailure(String msg) {
		this.code = CommonConstant.ServerApp.SERVER_RET_FAILURE;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

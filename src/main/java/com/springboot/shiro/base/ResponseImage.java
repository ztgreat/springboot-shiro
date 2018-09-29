package com.springboot.shiro.base;


// 返回上传图片的信息
public class ResponseImage {

	private String code;
	
	private String msg;
	
	private int uid;
	
	private String name;
	
	private String status;
	
	private String url;
	
	

	public ResponseImage() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
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


	public void setSuccess(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.msg = msg;
	}

	public void setFailure(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_FAILER;
		this.msg = msg;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
}

package com.springboot.shiro.base;


/**
 * 后台或者前台返回的统一对象(图片信息)
 */
public class ResponseImage extends BaseResponseEntity{


	private int uid;
	
	private String name;
	
	private String status;
	
	private String url;

	public ResponseImage() {
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

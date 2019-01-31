package com.springboot.shiro.base;


/**
 * 返回上传图片的信息
*/
public class ResponseImage {

	private String code;

	private String msg;

	private String originalName;

	private String name;

	private String path;

	public ResponseImage() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
	}

	public ResponseImage self(){
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseImage setMsg(String msg) {
		this.msg = msg;
		return self();
	}

	public ResponseImage success(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.msg = msg;
		return self();
	}

	public ResponseImage success() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		return self();
	}

	public ResponseImage failure(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_FAILER;
		this.msg = msg;
		return self();
	}

	public String getOriginalName() {
		return originalName;
	}

	public ResponseImage setOriginalName(String originalName) {
		this.originalName = originalName;
		return self();
	}

	public String getName() {
		return name;
	}

	public ResponseImage setName(String name) {
		this.name = name;
		return self();
	}

	public String getPath() {
		return path;
	}

	public ResponseImage setPath(String path) {
		this.path = path;
		return self();
	}

}

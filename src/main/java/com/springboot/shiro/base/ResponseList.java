package com.springboot.shiro.base;

import java.util.List;

/**
 * 列表数据返回
 * @param <T>
 */
public class ResponseList<T> {

	private String code;
	private String msg;
	private List<T> data;

	public ResponseList() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
	}
	public ResponseList(String msg) {
		this.msg=msg;
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
	}
	public ResponseList(List<T> t) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.setData(t);
	}
	public ResponseList self(){
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseList setMsg(String msg) {
		this.msg = msg;
		return self();
	}

	public List<T> getData() {
		return data;
	}

	public ResponseList setData(List<T> data) {
		this.data = data;
		return self();
	}

	public ResponseList success(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.msg = msg;
		return self();
	}
	public ResponseList success() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		return self();
	}

	public ResponseList failure(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_FAILER;
		this.msg = msg;
		return self();
	}
	public ResponseList failure() {
		this.code = CommonConstant.ServerCode.SERVER_RET_FAILER;
		return self();
	}

}

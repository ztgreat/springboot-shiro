package com.springboot.shiro.base;

import java.util.List;

//后台实体列表返回
public class ResponseList<T> extends BaseResponseEntity {

	private Long count;
	private List<T> data;
	private int size;

	public ResponseList() {
		super.setSuccess("");
	}

	public ResponseList(List<T> t, Long total) {
		super.setSuccess("");
		this.setData(t);
		this.setCount(total);
	}

	public ResponseList(List<T> t, Long total, int size) {
		super.setSuccess("");
		this.setData(t);
		this.setCount(total);
		this.setSize(size);
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

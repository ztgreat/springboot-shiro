package com.springboot.shiro.base;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 列表分页对象
 */
public class ResponsePage<T> {

	/**
	 * 0 成功
	 */
	private String code;

	/**
	 * 字符信息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private List<T> data;

	/**
	 * 当前页
	 */
	private Long current;

	/**
	 * 每页大小
	 */
	private Long pageSize;

	/**
	 * 数据总数
	 */
	private Long total;

	/**
	 * 默认是成功状态
	 */
	public ResponsePage() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
	}

	public ResponsePage(IPage page) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.current=page.getCurrent();
		this.pageSize=page.getSize();
		this.total=page.getTotal();
		this.data=page.getRecords();
	}

	/**
	 * 默认是成功状态
	 * @param msg
	 */
	public ResponsePage(String msg) {
		this.msg=msg;
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
	}

	public ResponsePage self(){
		return this;
	}

	public ResponsePage setPage(IPage page) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.current=page.getCurrent();
		this.pageSize=page.getSize();
		this.total=page.getTotal();
		this.data=page.getRecords();
		return self();
	}

	public ResponsePage success(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		this.msg = msg;
		return self();
	}
	public ResponsePage success() {
		this.code = CommonConstant.ServerCode.SERVER_RET_SUSSCESS;
		return self();
	}

	public ResponsePage failure(String msg) {
		this.code = CommonConstant.ServerCode.SERVER_RET_FAILER;
		this.msg = msg;
		return self();
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ResponsePage setMsg(String msg) {
		this.msg = msg;
		return self();
	}

	public List<T> getData() {
		return data;
	}

	public ResponsePage setData(List<T> data) {
		this.data = data;
		return self();
	}

	public Long getCurrent() {
		return current;
	}

	public ResponsePage setCurrent(Long current) {
		this.current = current;
		return self();
	}

	public Long getPageSize() {
		return pageSize;
	}

	public ResponsePage setPageSize(Long pageSize) {
		this.pageSize = pageSize;
		return self();
	}

	public Long getTotal() {
		return total;
	}

	public ResponsePage setTotal(Long total) {
		this.total = total;
		return self();
	}
}

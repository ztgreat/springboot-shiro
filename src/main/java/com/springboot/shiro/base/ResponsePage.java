package com.springboot.shiro.base;

import java.util.List;

/**
 * 前端返回列表分页对象等
 * @param <T>
 */
public class ResponsePage<T> extends BaseResponseEntity {

	private List<T> list;
	private Long total;
	private Integer page;
	private Integer limit;
	private Boolean isNextPage;

	public ResponsePage(List<T> list, Long total, Integer page, Integer limit) {
		this.limit = limit;
		this.list = list;
		this.page = page;
		this.total = total;
		super.setSuccess("查询成功");
	}

	public ResponsePage() {
	}

	public ResponsePage(List<T> list, Long total) {
		this.list = list;
		this.total = total;
		super.setSuccess("查询成功");
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Boolean getIsNextPage() {
		return isNextPage;
	}

	public void setIsNextPage(Boolean isNextPage) {
		this.isNextPage = isNextPage;
	}

}

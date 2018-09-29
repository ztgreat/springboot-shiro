package com.springboot.shiro.entity.ins;

import com.springboot.shiro.base.Tree;

import java.util.Date;

/**
 * 菜单树
 */
public class AuthMenuTreeIns extends Tree<AuthMenuTreeIns> {
	
	
	
	//同id
	private String key;

	private String title;
	
	private String authority;
	
	private String icon;
	 
	//同url 
	private String href;
	
	//同url
	private String path;
	
	/**
	 * 层级
	 */
	private Integer level;

    /**
     * 如果是第一级，父类ID为0；
     */
    private Integer parentId;

    /**
     * 例如admin:delete样式
     */
    private String code;

    /**
     * 0：不可用；1：可用
     */
    private String status;

    /**
     * 第一次创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    /**
     * 连接地址
     */
    private String url;

    /**
     * 菜单栏显示排序
     */
    private Integer sorter;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public void setName(String name) {
		super.setName(name);
		setTitle(name);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}


	@Override
	public void setId(Integer id) {
		super.setId(id);
		setKey(String.valueOf(id));
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		setPath(url);
		setHref(url);
	}

	public Integer getSorter() {
		return sorter;
	}

	public void setSorter(Integer sorter) {
		this.sorter = sorter;
	}
	
}

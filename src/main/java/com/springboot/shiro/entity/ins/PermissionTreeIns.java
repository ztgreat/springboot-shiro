package com.springboot.shiro.entity.ins;


import com.springboot.shiro.base.ResponseTree;

public class PermissionTreeIns extends ResponseTree<PermissionTreeIns> {


	
	private String key;
	
	private String title;
    
    /**
     * url地址
例如/admin/delete样式
     */
    private String code;

    /**
     * 0：不可用；1：可用
     */
    private String status;

    /**
     * 说明
     */
    private String descr;

    /**
     * 1：第一级；2：第二级，以此类推
     */
    private Integer level;

    /**
     * 如果是第一级，父类ID为0；
     */
    private Integer parentId;
    
    public  PermissionTreeIns(){
       
    }
    
    @Override
    public void setId(Integer id) {
    	super.setId(id);
    	setKey(String.valueOf(id));
    }
    
    @Override
    public void setName(String name) {
    	super.setName(name);
    	setTitle(name);
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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
    
}

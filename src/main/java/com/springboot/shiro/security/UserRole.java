package com.springboot.shiro.security;

import com.springboot.shiro.entity.SysRole;

import java.io.Serializable;

public class UserRole implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 198872L;

	private Integer id;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 1:有效，0:禁止登录
     */
    private String status;
    
    
    public UserRole() {
	}
    

    public UserRole(SysRole role) {
    	this.id = role.getId();
    	this.name = role.getName();
    	this.code = role.getCode();
    	this.status = role.getStatus();
	}

	/**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色代码
     *
     * @return code - 角色代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色代码
     *
     * @param code 角色代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取1:有效，0:禁止登录
     *
     * @return status - 1:有效，0:禁止登录
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置1:有效，0:禁止登录
     *
     * @param status 1:有效，0:禁止登录
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
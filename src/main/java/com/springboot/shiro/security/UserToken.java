package com.springboot.shiro.security;


import com.springboot.shiro.entity.SysUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 登录帐号
	 */
	private String username;

	/**
	 * 用户昵称
	 */
	private String nickname;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 联系帐号|登录帐号
	 */
	private String telephone;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 1:有效，0:禁止
	 */
	private String status;

	/**
	 * 用户角色
	 */
	private List<UserRole> roles;

	public UserToken() {
	}

	public UserToken(SysUser user) {

		this.setUsername(user.getUsername());
		this.setEmail(user.getEmail());
		this.setId(user.getId());
		this.setNickname(user.getNickname());
		this.setCreateTime(user.getCreateTime());
		this.setLastLoginTime(user.getLastLoginTime());
		this.setModifyTime(user.getModifyTime());
		this.setStatus(user.getStatus());
		this.setTelephone(user.getTelephone());
	}

	@Override
	public String toString() {
		return getUsername();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
}
package com.springboot.shiro.entity.ins;

import com.springboot.shiro.security.UserRole;
import com.springboot.shiro.security.UserToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户登录后返回实体
 * 
 * @author ztgreat
 *
 */
public class SysUserInfo {


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
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;


	/****
	 * 当前用户角色信息
	 */
	private List<String> currentAuthority;

	public SysUserInfo() {
	}

	public SysUserInfo(UserToken token) {

		List<String> userRole = new ArrayList<String>();
		for (UserRole sr : token.getRoles()) {
			userRole.add(sr.getCode());
		}
		this.setId(token.getId());
		this.setNickname(token.getNickname());
		this.setUsername(token.getUsername());
		this.setEmail(token.getEmail());
		this.setCreateTime(token.getCreateTime());
		this.setLastLoginTime(token.getLastLoginTime());
		this.setCurrentAuthority(userRole);
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

	public List<String> getCurrentAuthority() {
		return currentAuthority;
	}

	public void setCurrentAuthority(List<String> currentAuthority) {
		this.currentAuthority = currentAuthority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}

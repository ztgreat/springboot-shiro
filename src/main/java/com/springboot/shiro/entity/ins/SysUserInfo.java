package com.springboot.shiro.entity.ins;

import com.springboot.shiro.security.UserToken;

import java.util.List;

/**
 * 用户登录后返回实体
 * 
 * @author Administrator
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

	/****
	 * 当前用户角色信息
	 */
	private List<String> currentAuthority;


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


}

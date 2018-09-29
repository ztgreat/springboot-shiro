package com.springboot.shiro.entity.ins;

import com.springboot.shiro.entity.SysRole;
import com.springboot.shiro.entity.SysUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色分配 查询列表BO
 * @author ztgreat
 */
public class UserRoleAllocationBoIns extends SysUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Role Name列转行，以,分割
	private String roleNames ="" ;
	//Role Id列转行，以‘,’分割
	private String roleIds ="";
	
	private List<SysRole> sysRoles =  new ArrayList<>() ;
	
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	/*
	 * 用户角色竖排转横排，以逗号分隔
	 * */
	public void roleVerToHor() {
		if (null != sysRoles && sysRoles.size()>0) {
			roleIds = "";
			roleNames = "";
			for(SysRole sysRole : sysRoles) {
				roleIds += sysRole.getId() + ",";
				roleNames += sysRole.getName() + ",";
			}
			roleIds = roleIds.substring(0, roleIds.length()-1);
			roleNames = roleNames.substring(0, roleNames.length()-1);
		}
	}
	

}

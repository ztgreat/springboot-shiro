package com.springboot.shiro.security;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

public class MyPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		return new MyWildcardPermission(permissionString);
	}
	

}

package com.springboot.shiro.security;

import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.entity.SysPermission;
import com.springboot.shiro.entity.SysRole;
import com.springboot.shiro.entity.SysUser;
import com.springboot.shiro.service.SysPermissionService;
import com.springboot.shiro.service.SysRoleService;
import com.springboot.shiro.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SysUserRealm extends AuthorizingRealm{


    @Autowired
    private SysUserService userService;
    
    @Autowired
    private SysRoleService sysRoleService;
    
    @Autowired
    private SysPermissionService sysPermissionService;
    
    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserToken token= (UserToken) principalCollection.getPrimaryPrincipal();
        Integer userId = TokenManager.getUserId();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        if (userId==null)
            return  authorizationInfo;
        
        //根据用户ID查询角色（role），放入到Authorization里。
        //可以直接从token中获取，这里暂从数据库查询
		List<SysRole> roles = sysRoleService.getRoleByUserId(userId);
		Set<String> sr=new HashSet<String>();
		for (SysRole sysRole : roles) {
			sr.add(sysRole.getCode());
		}
		authorizationInfo.setRoles(sr);
		//根据用户ID查询权限（permission），放入到Authorization里。
		List<SysPermission> permissions = sysPermissionService.getPermissionByUserId(userId);
		Set<String> sp=new HashSet<String>();
		for (SysPermission sysPermission : permissions) {
			sp.add(sysPermission.getCode());
		}
		authorizationInfo.setStringPermissions(sp);
        return authorizationInfo;
        
    }

    /**
     * 获取身份验证相关信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user=userService.getUserByUserName(token.getUsername());

        if (user==null){
            throw new UnknownAccountException();//没找到帐号
        }
        if (user.getStatus().equals(CommonConstant.UserInfo.STATE_LOCK) ){
            throw new LockedAccountException(); //帐号锁定
        }
        
        UserToken principal=new UserToken(user);


        //将用户角色 放到principal中,方便提取
        List<SysRole>roles = sysRoleService.getRoleByUserId(user.getId());

        if(roles!=null && roles.size()>0){
            List<UserRole>userRoles = new ArrayList<>();
            for(SysRole role:roles){
                userRoles.add(new UserRole(role));
            }
            principal.setRoles(userRoles);
        }


        RedisSimpleAuthenticationInfo authenticationInfo = new RedisSimpleAuthenticationInfo(
        		principal, //principal
                user.getPassword(), //密码作为凭证
                getName()  //realm name
        );

        return authenticationInfo;

    }
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
    	super.clearCachedAuthenticationInfo(principals);
    }
    
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
    
    /**
	 * 指定principalCollection 清除
	 */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(
    			principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
    }
    
    

}

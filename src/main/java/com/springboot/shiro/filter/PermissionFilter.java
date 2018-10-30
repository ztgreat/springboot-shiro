package com.springboot.shiro.filter;

import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.base.ResponseEntity;
import com.springboot.shiro.util.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验 Filter
 * 
 */
public class PermissionFilter extends AccessControlFilter {

	private static  String urlPrefix="/api";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// 先判断带参数的权限判断
		Subject subject = getSubject(request, response);
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		String uri = httpRequest.getRequestURI();// 获取URI
		String basePath = httpRequest.getContextPath();// 获取basePath

		//去掉请求 参数
		if(uri!=null){
			int param = uri.indexOf("?");
			if(param>-1){
				uri = uri.substring(0,param);
			}
		}

		if (null != uri && uri.startsWith(basePath)) {
			uri = uri.replaceFirst(basePath, "");
		}

		//去掉前缀
		if(uri.startsWith(this.urlPrefix)){
			uri = uri.replaceFirst(this.urlPrefix, "");
		}else {
			return Boolean.FALSE;
		}


		// 不存在"/"地址不符合地址要求，返回没有权限
		if (uri.indexOf("/") < 0) {
			return Boolean.FALSE;
		}
		if (uri.substring(0, 1).equals("/")) {
			uri = uri.replaceFirst("/", "").replaceAll("/", ":");
		} else {
			uri = uri.replaceAll("/", ":");
		}
		if (StringUtils.isBlank(uri)) {
			return Boolean.FALSE;
		}
		try {
			if (subject.isPermitted(uri)) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "资源权限验证失败:" + e.getMessage());
			return Boolean.FALSE;
		}

		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		Subject subject = getSubject(request, response);
		// 表示没有登录，重定向到登录页面
		if (null == subject.getPrincipal()) {
			saveRequest(request);
			WebUtils.issueRedirect(request, response, ShiroFilterUtils.LOGIN_URL);
		} else {
			// 如果是ajax请求，返回页面提示信息
			if (ShiroFilterUtils.isAjax(request)) {
				ResponseEntity<String> res = new ResponseEntity<String>();
				res.setFailure(CommonConstant.Message.OPTION_FAILURE_NO_PERMISSION);
				ShiroFilterUtils.out401(response, res, HttpServletResponse.SC_FORBIDDEN);
			} else {
//				if (org.apache.shiro.util.StringUtils.hasText(ShiroFilterUtils.UNAUTHORIZED)) {// 如果有未授权页面跳转过去
//					WebUtils.issueRedirect(request, response, ShiroFilterUtils.UNAUTHORIZED);
//				} else {
					// 否则返回401未授权状态码
					WebUtils.toHttp(response).sendError(HttpServletResponse.SC_FORBIDDEN);
//				}
			}
		}
		return Boolean.FALSE;
	}

}
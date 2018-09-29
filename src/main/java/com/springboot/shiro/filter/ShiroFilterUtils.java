package com.springboot.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.springboot.shiro.base.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Shiro Filter 工具类
 */
public class ShiroFilterUtils {
	final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;

	final Logger logger = LoggerFactory.getLogger(ShiroFilterUtils.class);
	// 登录页面
	static final String LOGIN_URL = "/login";
	// 踢出登录提示
	final static String KICKED_OUT = "/kickedOut";
	// 没有权限提醒
	final static String UNAUTHORIZED = "/login";

	/**
	 * 是否是Ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}

	/**
	 * 判断是否是fetch请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isFetch(ServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("m-requested-with"));
	}

	/**
	 * response 输出JSON
	 * 
	 *            是否跨域
	 * @throws IOException
	 */
	public static void out401(ServletResponse response, ResponseEntity<String> resultMap, int status) {

		PrintWriter out = null;
		HttpServletResponse response_ = (HttpServletResponse) response;
		try {
			response_.setCharacterEncoding("UTF-8");
			response_.setContentType("application/json");
			response_.sendError(status);
			out = response_.getWriter();
			out.write(JSON.toJSONString(resultMap));
		} catch (Exception e) {

		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
	}
}

package com.springboot.shiro.base;

/**
 * 常量类 common
 * 
 * @lichee
 */
public class CommonConstant {

	public final static String ZERO = "0";
	public final static String ONE = "1";

	public static final String CharSetName = "utf8";

	/** 业务校验通过 **/
	public static final String SUCCESS = "success";

	/**
	 * 服务器端想app前端返回的代码
	 * 
	 * @author Administrator
	 *
	 */
	public class ServerApp {
		/** 成功 **/
		public final static String SERVER_RET_SUSSCESS = "0";
		/** 失败 **/
		public final static String SERVER_RET_FAILURE = "1";
	}

	public class User {

		/** 0：禁用 */
		public final static String STATUS_ZERO = "0";
		/** 1：活动 */
		public final static String STATUS_ONE = "1";

		public final static String CODE_SUCCESS = "success";
		public final static String CODE_SUCCESS_MSG = "操作成功";
		public final static String CODE_ERROR = "error";
		public final static String CODE_ERROR_MSG = "失败";
		public final static String CODE_NULL = "null";
		public final static String CODE_NULL_MSG = "请不要输出空";

		public final static String CODE_REGISTER_MSG = "注册成功";
		public final static String CODE_LOGIN_MSG = "登录成功";
		public final static String CODE_LOGINOUT_MSG = "退出成功";
		public final static String CODE_LOGINOUT_ERROR_MSG = "退出失败";

		public final static String CODE_SMS_SUCCESS = "手机验证码短信发送成功";
		public final static String CODE_SMS_ERROR = "手机验证码短信发送失败";
		public final static String CODE_SMS_NULL = "请输入手机号";
		public final static String CODE_CAPTCHA_SUCCESS = "图文验证成功";

		public final static String CODE_40001 = "40001";
		public final static String CODE_40001_MSG = "用户已经被禁用";
		public final static String CODE_40002 = "40002";
		public final static String CODE_40002_MSG = "用户存在异常";
		public final static String CODE_40003 = "40003";
		public final static String CODE_40003_MSG = "用户名或密码输入错误";
		public final static String CODE_40004 = "40004";
		public final static String CODE_40004_MSG = "登录过程异常";
		public final static String CODE_40005 = "40005";
		public final static String CODE_40005_MSG = "图片验证码失败";
		public final static String CODE_40006 = "40006";
		public final static String CODE_40006_MSG = "手机号输入错误";
		public final static String CODE_40007 = "40007";
		public final static String CODE_40007_MSG = "用户未登录";
		public final static String CODE_40008 = "40008";
		public final static String CODE_40008_MSG = "密码错误";

		public final static String CODE_50001 = "50001";
		public final static String CODE_50001_MSG = "手机验证码短信发送出错，或发送超频，请稍后再试！";
		public final static String CODE_50002 = "50002";
		public final static String CODE_50002_MSG = "短信发送超频";
		public final static String CODE_50003_1 = "50003_1";
		public final static String CODE_50003_1_MSG = "此手机号已经被注册";
		public final static String CODE_50003_2 = "50003_2";
		public final static String CODE_50003_2_MSG = "此账号已经被注册";
		public final static String CODE_50004 = "50004";
		public final static String CODE_50004_MSG = "两次输入的密码不一样";
		public final static String CODE_50005 = "50005";
		public final static String CODE_50005_MSG = "短信验证码错误";
		public final static String CODE_50006 = "50006";
		public final static String CODE_50006_MSG = "注册保存用户参数失败";
		public final static String CODE_50007 = "50007";
		public final static String CODE_50007_MSG = "注册保存用户信息失败";
		public final static String CODE_50008 = "50008";
		public final static String CODE_50008_MSG = "注册过程异常";
		public final static String CODE_50009 = "50009";
		public final static String CODE_50009_MSG = "修改密码过程异常";
		public final static String CODE_50010 = "50010";
		public final static String CODE_50010_MSG = "修改密码成功";
		public final static String CODE_50011 = "50011";
		public final static String CODE_50011_MSG = "修改密码失败";
		public final static String CODE_50012 = "50012";
		public final static String CODE_50012_MSG = "修改手机过程异常";
		public final static String CODE_50013 = "50013";
		public final static String CODE_50013_MSG = "修改手机成功";
		public final static String CODE_50014 = "50014";
		public final static String CODE_50014_MSG = "修改手机失败";
		public final static String CODE_50015 = "50015";
		public final static String CODE_50015_MSG = "修改头像过程异常";
		public final static String CODE_50016 = "50016";
		public final static String CODE_50016_MSG = "修改头像成功";
		public final static String CODE_50017 = "50017";
		public final static String CODE_50017_MSG = "修改头像失败";
		public final static String CODE_50018 = "50018";
		public final static String CODE_50018_MSG = "上传文件为空";

	}

	public class CacheTime {
		/** app用户登录token缓存时间 **/

		public final static int APP_USER_TOKEN = 2 * 24 * 3600;

		/** app用户登录refeshtoken缓存时间 **/
		public final static int APP_USER_REFRESHTOEKN = 30 * 24 * 3600;

		/** 手机验证码缓存时间 **/
		public static final int SMS_VALID = 60 * 5;
	}

	public class App {
		public static final String PARAM_DIGEST = "sign";
		public static final String PARAM_TOKEN = "uid";
	}

	public class ServerCode {
		/** 服务器通信成功、业务操作成功的状态码 **/
		public final static String SERVER_RET_SUSSCESS = "0";

		/** 服务器通信成功、业务操作失败的状态码 **/
		public final static String SERVER_RET_FAILER = "1";
		/** 未授权 **/
		public final static String SERVER_401 = "401";
	}

	public class UsedStatus {

		/** 状态可用 **/
		public final static String USABLE = "1";
		/** 状态不可用 **/
		public final static String UNUSABLE = "0";

	}

	public class Message {

		/************************
		 * 系统管理或通用消息
		 *************************/
		/** 操作成功 **/
		public final static String OPTION_SUCCESS = "操作成功";
		/** 操作失败 **/
		public final static String OPTION_FAILURE = "操作失败";
		/** 操作失败，没有对应权限 **/
		public final static String OPTION_FAILURE_NO_PERMISSION = "操作失败,没有权限";
		/** 代码或名称已存在 **/
		public final static String ADD_UPDATE_PROP_EXIST = "数据库已存在同名属性";
		/** 角色代码已存在 **/
		public final static String ROLE_CODENAME_EXIST = "角色代码或名称已存在";
		/** 角色已分配权限 **/
		public final static String ROLE_EXIST_PERMISSION = "角色已分配权限,请先解除角色权限关系";
	}

	public class RedisIndex {

		/* session在redis 中 的dbIndex */
		// public final static int SESSION_INDEX=0;

		/* 普通用户登录key 存放在redis中的 dbIndex */
		public final static int USER_LOGIN_INDEX = 1;

		/* 代理用户登录key 存放在redis中的 dbIndex */
		public final static int AGENT_LOGIN_INDEX = 2;
	}

	public class Picture {
		public static final String defaultSplit = ";";
		public static final String semicolonSplit = ",";
	}

	public class BizCode {
		public static final String LOGIN_SUCCESS = "1";
		public static final String LOGIN_FAIL = "0";
	}

	public static class UserInfo {
		public static final String CURRENT_USER = "user";

		public static final String STATE_LOCK="0";
		public static final String STATE_OK="1";
	}
}

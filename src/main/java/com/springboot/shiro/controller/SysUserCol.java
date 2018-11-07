package com.springboot.shiro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.base.ResponseEntity;
import com.springboot.shiro.base.ResponseList;
import com.springboot.shiro.entity.SysUser;
import com.springboot.shiro.entity.ins.SysUserInfo;
import com.springboot.shiro.security.TokenManager;
import com.springboot.shiro.security.UserRole;
import com.springboot.shiro.security.UserToken;
import com.springboot.shiro.service.SysRoleService;
import com.springboot.shiro.service.SysUserService;
import com.springboot.shiro.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户控制层
 *
 * @author ztgreat
 */
@Controller
@RequestMapping(value = "/api/admin")
public class SysUserCol {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "page", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<SysUser> page(@RequestParam(value = "current", defaultValue = "1") int current,
									  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
									  @RequestParam(value = "search", defaultValue = "") String search,
									  @RequestParam(value = "status", defaultValue = "") String status) {
		ResponseList<SysUser> users = new ResponseList<SysUser>();
		try {
			IPage<SysUser> p = sysUserService.page(current, pageSize, search);
			users.setData(p.getRecords());
			users.setCount(p.getTotal());
			return users;
		} catch (RuntimeException e) {
			LoggerUtils.error(getClass(),"[sysUser page]" + e.getMessage());
			users.setFailure("获取失败");
			return users;
		}
	}

	@RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> save(@RequestBody SysUser user) {
		ResponseEntity<String> res = new ResponseEntity<String>();
		try {

			sysUserService.saveUser(user);

			// 关闭权限菜单时，为所有新建用户插入管理员角色
			// sysRoleService.updateUserRole(user.getId().toString(), "1");

			res.setMsg(CommonConstant.Message.OPTION_SUCCESS);
		} catch (RuntimeException e) {
			res.setMsg(CommonConstant.Message.OPTION_FAILURE);
			LoggerUtils.error(getClass(),"[sysUser save]" + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> update(HttpServletRequest request, @RequestBody SysUser user) {
		ResponseEntity<String> res = new ResponseEntity<String>();
		try {
			user.setModifyTime(new Date());
			user.setPassword(null);
			sysUserService.updateById(user);
			res.setMsg(CommonConstant.Message.OPTION_SUCCESS);
		} catch (RuntimeException e) {
			res.setMsg(CommonConstant.Message.OPTION_FAILURE);
			LoggerUtils.error(getClass(),"[sysUser update]" + e.getMessage());
		}
		return res;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody Map<String, Object> param) {
		ResponseEntity<String> res = new ResponseEntity<>();
		try {
			List<Integer> ids = (List<Integer>) param.get("ids");
			String msg = sysUserService.delete(ids);
			res.setSuccess(msg);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[ sysuser delete]" + e.getMessage());
			res.setFailure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;

	}

	/**
	 * 获取登录用户信息
	 * @return
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<SysUserInfo> query() {
		ResponseEntity<SysUserInfo> res = new ResponseEntity<SysUserInfo>();
		try {
			UserToken token =TokenManager.getToken();
			SysUserInfo su = new SysUserInfo(token);
			res.setData(su);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"获取用户登录信息失败");
			res.setFailure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}

}

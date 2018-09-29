package com.springboot.shiro.controller;

import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.base.ResponseEntity;
import com.springboot.shiro.base.ResponseList;
import com.springboot.shiro.entity.SysMenu;
import com.springboot.shiro.entity.ins.AuthMenuTreeIns;
import com.springboot.shiro.security.TokenManager;
import com.springboot.shiro.security.UserToken;
import com.springboot.shiro.service.SysMenuService;
import com.springboot.shiro.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 菜单操作
 *
 */
@Controller
@RequestMapping(value = "/api/menu")
public class SysMenuCol {

	@Autowired
	private SysMenuService sysMenuService;


	// 保存
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveOrUpdate(@RequestBody SysMenu menu) {
		ResponseEntity<String> res = new ResponseEntity<String>();
		try {

			String s = sysMenuService.saveMenu(menu);
			res.setSuccess(s);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "菜单保存失败:" + e.getMessage());
			res.setFailure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}

	// 删除
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(Integer id) {
		ResponseEntity<String> res = new ResponseEntity<String>();
		try {
			boolean flag = sysMenuService.removeById(id);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "菜单删除失败:" + e.getMessage());
			res.setFailure(CommonConstant.Message.OPTION_FAILURE);
		}
		res.setSuccess("删除成功");
		return res;

	}

	/**
	 * 通过用户id获取菜单tree
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserMenuTree")
	@ResponseBody
	public ResponseList<AuthMenuTreeIns> userTree() {
		UserToken token = TokenManager.getToken();
		ResponseList<AuthMenuTreeIns> res = new ResponseList<AuthMenuTreeIns>();
		List<AuthMenuTreeIns> menus = null;
		try {
			menus = sysMenuService.getMenuTreeByUserId(token.getId());
			res.setData(menus);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "通过用户id 获取用户菜单树 失败:" + e.getMessage());
			res.setFailure(e.getMessage());
		}
		return res;
	}

	/**
	 * 获取所有菜单tree
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getMenuTree", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<AuthMenuTreeIns> getMenuTree() {
		ResponseList<AuthMenuTreeIns> res = new ResponseList<AuthMenuTreeIns>();
		List<AuthMenuTreeIns> menus = null;
		try {
			menus = sysMenuService.getMenuTree(0);
			setMenuTree(menus, null);
			res.setData(menus);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "获取菜单树 失败:" + e.getMessage());
			res.setFailure(e.getMessage());
		}
		return res;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteBatch(@RequestBody Map<String, Object> param) {
		ResponseEntity<String> res = new ResponseEntity<String>();
		List<String> ids = (List<String>) param.get("ids");
		try {
			sysMenuService.deleteBatch(ids);
			res.setSuccess(CommonConstant.Message.OPTION_SUCCESS);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "批量删除菜单失败:" + e.getMessage());
			res.setFailure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}

	/**
	 * 设置权限树 状态(设置 是否选中)
	 * 
	 * @param trees
	 *            菜单树
	 * @param mapHasMenusMenus
	 *            具有权限的菜单
	 */
	private boolean setMenuTree(List<AuthMenuTreeIns> trees, Map<Integer, SysMenu> mapHasMenusMenus) {
		if (trees == null || trees.isEmpty())
			return false;
		boolean flag = false;
		for (AuthMenuTreeIns tree : trees) {
			if (mapHasMenusMenus != null && mapHasMenusMenus.containsKey(tree.getId())) {
				tree.setChecked(true);
				flag = true;
			}
			if (tree.getChildren() != null && !tree.getChildren().isEmpty()) {
				tree.setIsParent(true);
				if (setMenuTree(tree.getChildren(), mapHasMenusMenus)) {
					tree.setChecked(true);
				}
			}
		}
		return flag;
	}

}

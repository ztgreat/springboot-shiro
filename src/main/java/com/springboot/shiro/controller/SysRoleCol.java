package com.springboot.shiro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.base.ResponseEntity;
import com.springboot.shiro.base.ResponseList;
import com.springboot.shiro.base.ResponsePage;
import com.springboot.shiro.entity.SysMenu;
import com.springboot.shiro.entity.SysPermission;
import com.springboot.shiro.entity.SysRole;
import com.springboot.shiro.entity.ins.AuthMenuTreeIns;
import com.springboot.shiro.entity.ins.PermissionTreeIns;
import com.springboot.shiro.entity.ins.SingleUserRolesBoIns;
import com.springboot.shiro.entity.ins.UserRoleAllocationBoIns;
import com.springboot.shiro.service.SysMenuService;
import com.springboot.shiro.service.SysPermissionService;
import com.springboot.shiro.service.SysRoleService;
import com.springboot.shiro.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@Controller
@RequestMapping(value="/api/role")
public class SysRoleCol {
	

	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysPermissionService sysPermissionService;

	
	// 查询
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public ResponsePage<SysRole> page(@RequestParam(value = "current", defaultValue = "1") int current,
									  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
									  @RequestParam(value = "search", defaultValue = "") String search) {
		IPage<SysRole> page = sysRoleService.page(current, pageSize, search);
		ResponsePage<SysRole> res = new ResponsePage<SysRole>(page);
		return res;
	}

	// 保存
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveOrUpdate(@RequestBody SysRole role) {
		
		ResponseEntity<String> res = new ResponseEntity<String>();

		try {
			String s = sysRoleService.saveRole(role);
			res.success(s);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[角色更新或者删除]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody Map<String, Object>param) {
		ResponseEntity<String> res = new ResponseEntity<>();
		try {
			List<Integer> ids = (List<Integer>) param.get("ids");
			for(Integer id:ids){
				//删除角色前需确定该角色是否已被分配权限，如已分配需先解除角色权限关系
				int permissionCount = sysRoleService.queryPerAllocationNumOfRole(id.intValue());
				if (permissionCount>0) {
					res.failure(CommonConstant.Message.ROLE_EXIST_PERMISSION);
				}else {
					sysRoleService.removeById(id);
					res.success(CommonConstant.Message.OPTION_SUCCESS);
				}
			}
			
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[role delete]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;

	}
	

	@RequestMapping(value = "/query/{id}")
	@ResponseBody
	public ResponseEntity<SysRole> query(HttpServletRequest request, @PathVariable("id") Integer id) {
		ResponseEntity<SysRole> res = new ResponseEntity<>();
		try {
			SysRole role=sysRoleService.getById(id);
			res.success(CommonConstant.Message.OPTION_SUCCESS);
			res.setData(role);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[role get]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}
	
	
	/**
	 * 分页查询所有用户已分配角色
	 * @param current
	 * @param pageSize
	 * @param search 支持用户名模糊查询,可传null
	 * @param order 支持排序
	 * @return 
	 */
	@RequestMapping(value = "/pageUserRoles", method = RequestMethod.GET)
	@ResponseBody
	public ResponsePage<UserRoleAllocationBoIns> pageUserRoles(@RequestParam(value = "current", defaultValue = "1") int current,
																  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
																  @RequestParam(value = "search", defaultValue = "") String search,
																  @RequestParam(value = "order", defaultValue = "") String order) {
		ResponsePage<UserRoleAllocationBoIns> res = new ResponsePage<UserRoleAllocationBoIns>();
		try {
				IPage<UserRoleAllocationBoIns> page = sysRoleService.queryUserRoleByPage(search, current, pageSize, order);
				for(UserRoleAllocationBoIns userRoleAllocationBo:page.getRecords()){
					userRoleAllocationBo.roleVerToHor();   //查询结果行专列
				}
				res.setPage(page);
				return res;
			} catch (RuntimeException e) {
				LoggerUtils.error(getClass(),"[sysrole pageUserRoles]" + e.getMessage());
			}
		res.failure("获取列表失败");
		return res;
	}
	
	/**
	 * 更新用户角色关系
	 * @return
	 */
	@RequestMapping(value = "/updateUserRoles", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateUserRoles(@RequestBody Map<String, Object>param) {
		
		ResponseEntity<String> res = new ResponseEntity<String>();
		Integer userId = (Integer) param.get("userId");
		List<Integer> roleIds= (List<Integer>) param.get("roleIds");
		try {
			sysRoleService.updateUserRoles(userId, roleIds);
			res.setMsg(CommonConstant.Message.OPTION_SUCCESS);
		} catch (RuntimeException e) {
			res.setMsg(e.getMessage());
			LoggerUtils.error(getClass(),"[sys updateUserRoles]" + e.getMessage());
		}
		return res;
	}
	
	/**
	 * 查询单个用户角色分配情况
	 * @param userId
	 * @return 
	 */
	@RequestMapping(value = "/querySingleUserRole/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<SingleUserRolesBoIns> querySingleUserRole(@PathVariable("userId") String userId) {

		ResponseList<SingleUserRolesBoIns> res=new ResponseList<SingleUserRolesBoIns>();
		try {
			List<SingleUserRolesBoIns> singleUserRoleAllocationBos = sysRoleService.queryRoleAllocationByUserId(userId);
			res.setData(singleUserRoleAllocationBos);
			} catch (RuntimeException e) {
				res.failure(CommonConstant.Message.OPTION_FAILURE);
				LoggerUtils.error(getClass(),"[querySingleUserRole]" + e.getMessage());
			}
		return res;
	}
	
	/**
	 * 清空单个用户角色
	 * @param userId
	 * @return 
	 */
	@RequestMapping(value = "/deleteRoleByUserId/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> deleteRoleByUserId(@PathVariable("userId") Integer userId) {
		
		ResponseEntity<String> res = new ResponseEntity<>();
		try {
			sysRoleService.deleteAllRoleByUserId(userId);
			res.success(CommonConstant.Message.OPTION_SUCCESS);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[role get]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}
	
	
	
	
	/**
	 * 得到某个角色的权限树
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/getPermissionTreeByRoleId")
	@ResponseBody
	public ResponseList<PermissionTreeIns> getPermissionTreeByRoleId(Integer roleId){
		ResponseList<PermissionTreeIns> res = new ResponseList<PermissionTreeIns>();
		try {
			List<SysPermission>permissions=sysPermissionService.getPermissionByRoleId(roleId);
			
			Map<Integer, SysPermission>mapPermissions=new HashMap<Integer, SysPermission>();
			for(SysPermission permission: permissions){
				mapPermissions.put(permission.getId(), permission);
			}
			List<PermissionTreeIns> permissionTrees=sysPermissionService.getPermissionTree(0);
			
			setPermissionTree(permissionTrees,mapPermissions);
			res.setData(permissionTrees);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[role gePermissionTree]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}
	
	/**
	 * 设置权限树 状态(设置 是否选中)
	 * @param trees
	 * @param mapPermissions
	 */
	private void setPermissionTree(List<PermissionTreeIns> trees,Map<Integer, SysPermission>mapPermissions){
		if(trees==null || trees.isEmpty())
			return;
		for(PermissionTreeIns tree: trees){
			if(mapPermissions.containsKey(tree.getId())){
				tree.setChecked(true);
			}
			if(tree.getChildren()!=null && !tree.getChildren().isEmpty()){
				tree.setIsParent(true);
				setPermissionTree(tree.getChildren(),mapPermissions);
			}
			
		}
		return;
	}
	
	
	
	/**
	 * 用户菜单授权 tree
	 * 某个角色的菜单树
	 */
	@RequestMapping(value = "/getMenuTreeByRoleId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<AuthMenuTreeIns> getMenuTree(Integer roleId) {
		ResponseList<AuthMenuTreeIns> res = new ResponseList<AuthMenuTreeIns>();
		try {
			
			List<SysMenu>sysMenus=sysMenuService.getMenuByRoleId(roleId);
			Map<Integer, SysMenu>mapMenus=new HashMap<Integer, SysMenu>();
			for(SysMenu menu: sysMenus){
				mapMenus.put(menu.getId(), menu);
			}
			List<AuthMenuTreeIns> menus = sysMenuService.getMenuTree(0);
			setMenuTree(menus,mapMenus);
			res.setData(menus);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "通过角色id 获取菜单树失败 :"+e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		
		return res;
	}
	
	/**
	 * 设置权限树 状态(设置 是否选中)
	 * @param trees 菜单树
	 * @param mapHasMenusMenus 具有权限的菜单
	 */
	private void setMenuTree(List<AuthMenuTreeIns> trees,Map<Integer, SysMenu>mapHasMenusMenus){
		if(trees==null || trees.isEmpty())
			return;
		for(AuthMenuTreeIns tree: trees){
			if(mapHasMenusMenus!=null && mapHasMenusMenus.containsKey(tree.getId())){
				tree.setChecked(true);
			}
			if(tree.getChildren()!=null && !tree.getChildren().isEmpty()){
				tree.setIsParent(true);
				setMenuTree(tree.getChildren(),mapHasMenusMenus);
			}
		}
		return;
	}
	
	/**
	 * 更新菜单权限
	 * @return
	 */
	@RequestMapping(value = "/updateMenus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateMenus(@RequestBody Map<String, Object>param) {
		
		ResponseEntity<String> res = new ResponseEntity<>();
		Integer roleId = (Integer) param.get("roleId");
		List<Integer> menusIds=  (List<Integer>) param.get("menusIds");
		
		try {
			sysMenuService.updatePermission(roleId,menusIds);
			res.success(CommonConstant.Message.OPTION_SUCCESS);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "更新菜单权限失败 :"+e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}
	
	/**
	 * 更新角色权限
	 * @return
	 */
	@RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updatePermission(@RequestBody Map<String, Object>param) {
		
		ResponseEntity<String> res = new ResponseEntity<>();
		Integer roleId = (Integer) param.get("roleId");
		List<Integer> permissionIds=  (List<Integer>) param.get("permissionIds");
		try {
			sysPermissionService.updatePermission(roleId,permissionIds);
			res.success(CommonConstant.Message.OPTION_SUCCESS);
		} catch (Exception e) {
			LoggerUtils.error(getClass(),"[role updatePermission]" + e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}
	
	
}

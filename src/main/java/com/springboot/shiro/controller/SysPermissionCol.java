package com.springboot.shiro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.base.ResponseEntity;
import com.springboot.shiro.base.ResponseList;
import com.springboot.shiro.base.ResponsePage;
import com.springboot.shiro.entity.SysPermission;
import com.springboot.shiro.entity.ins.PermissionTreeIns;
import com.springboot.shiro.service.SysPermissionService;
import com.springboot.shiro.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 权限资源文件管理
 */
@Controller
@RequestMapping(value = "/api/permission")
public class SysPermissionCol {


	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * 指定资源权限树
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
	public ResponsePage<PermissionTreeIns> getPermissionTree(@RequestParam(value = "current", defaultValue = "1") int current,
															 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
															 @RequestParam(value = "parentId", defaultValue = "0")Integer parentId){
		ResponsePage<PermissionTreeIns> res = new ResponsePage<PermissionTreeIns>();
		try {

			IPage<PermissionTreeIns> page=sysPermissionService.getPermissionTree(current,pageSize,parentId);
			List<PermissionTreeIns> permissionTree=page.getRecords();
			setTree(permissionTree);
			res.setPage(page);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "获取权限树失败:"+e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}


	// 保存或者更新
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveOrUpdate(@RequestBody SysPermission permission) {
		ResponseEntity<String> res = new ResponseEntity<String>();
		try {
			String s = sysPermissionService.savePermission(permission);
			res.success(s);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "权限资源保存失败:"+e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}

	// 删除
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public ResponseEntity<String> deleteBatch(@RequestBody Map<String, Object>param) {
		ResponseEntity<String> res = new ResponseEntity<>();
		List<String> ids = (List<String>)param.get("ids");
		try {
			String msg=sysPermissionService.deleteBatch(ids);
			res.success(msg);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "删除权限资源:"+e.getMessage());
			res.failure("该权限可能存在数据关联,暂时无法删除");
		}
		return res;

	}

	/**
	 * 指定资源权限树
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/getPermissionByParentId")
	@ResponseBody
	public ResponseList<PermissionTreeIns> getPermissionTree(@RequestParam(value = "parentId", defaultValue = "0")Integer parentId){
		ResponseList<PermissionTreeIns> res = new ResponseList<PermissionTreeIns>();
		try {
			
			List<PermissionTreeIns> permissionTree=sysPermissionService.getPermissionTree(parentId);
			res.setData(permissionTree);
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "获取权限树失败:"+e.getMessage());
			res.failure(CommonConstant.Message.OPTION_FAILURE);
		}
		return res;
	}
	
	
	/**
	 * 设置权限树 状态(设置 是否选中)
	 * @param trees 菜单树
	 * @param trees 具有权限的菜单
	 */
	private boolean setTree(List<PermissionTreeIns> trees){
		if(trees==null || trees.isEmpty())
			return false;
		boolean flag=false;
		for(PermissionTreeIns tree: trees){
			if(tree.getChildren()!=null && !tree.getChildren().isEmpty()){
				tree.setIsParent(true);
				if(setTree(tree.getChildren())){
					tree.setChecked(true);
				}
			}
		}
		return flag;
	}
	
}

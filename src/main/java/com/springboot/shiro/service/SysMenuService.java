package com.springboot.shiro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.shiro.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.shiro.entity.ins.AuthMenuTreeIns;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zt
 * @since 2018-09-29
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 保存
     *
     * @param menu
     * @return
     * @throws RuntimeException
     */
    String saveMenu(SysMenu menu) throws RuntimeException;


    /**
     * 通过用户id获取菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> getMenuByUserId(Integer userId);

    /**
     * 通过角色id获取菜单列表
     * @param roleId
     * @return
     */
    List<SysMenu> getMenuByRoleId(Integer roleId);


    /**
     * 通过用户id 查询菜单资源（树结构）
     * @param userId
     * @return
     */
    List<AuthMenuTreeIns> getMenuTreeByUserId(Integer userId);

    /**
     * 获取权限菜单树
     * @param parentId 指定根菜单
     * @return
     */
    List<AuthMenuTreeIns>getMenuTree(Integer parentId);

    /**
     * 更新某个角色的菜单权限
     * @param roleId
     * @param menusId
     * @return
     */
    public boolean updatePermission(Integer roleId, List<Integer> menusId)throws RuntimeException;


    /**
     * 查询
     *
     * @param pageNum
     * @param pageSize
     * @param search
     *            支持名称模糊查询
     * @return
     */
    IPage<SysMenu> page(int pageNum, int pageSize, String search, Integer parentId);


    /**
     * 批量删除菜单
     * @param ids,数组
     * @return
     */
    String deleteBatch(List<String> ids) throws  RuntimeException;
}

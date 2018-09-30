package com.springboot.shiro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.shiro.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.shiro.entity.ins.PermissionTreeIns;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ztgreat
 * @since 2018-09-29
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 通过用户id查询 授权资源
     *
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionByUserId(Integer userId);

    /**
     * 保存
     *
     * @param p
     * @return
     * @throws RuntimeException
     */
    String savePermission(SysPermission p) throws RuntimeException;

    /**
     * 查询
     *
     * @param pageNum
     * @param pageSize
     * @param search
     *            支持名称模糊查询
     * @return
     */
    IPage<SysPermission> page(int pageNum, int pageSize, String search, Integer parentId);


    /**
     * 获取某个权限菜单的子节点
     * @param parentId 如果为null ,则获取所有数据
     * @return
     */
    List<SysPermission> getPermissionByParentId(Integer parentId);


    /**
     * 获取权限菜单树
     * @param parentId 指定根菜单
     * @return
     */
    public IPage<PermissionTreeIns> getPermissionTree(int pageNum, int pageSize, Integer parentId);

    public List<PermissionTreeIns> getPermissionTree(Integer parentId);

    /**
     * 查询某个角色的权限集合
     * @param roleId
     * @return
     */
    public List<SysPermission>getPermissionByRoleId(Integer roleId);

    /**
     * 更新某个角色的权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    public boolean updatePermission(Integer roleId, List<Integer> permissionIds)throws RuntimeException;

    String deleteBatch(List<String> ids) throws  RuntimeException;
}

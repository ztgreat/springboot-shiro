package com.springboot.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.shiro.entity.SysRole;
import com.springboot.shiro.entity.ins.SingleUserRolesBoIns;
import com.springboot.shiro.entity.ins.UserRoleAllocationBoIns;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2018-09-29
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    public List<SysRole>getRoleByUserId(Integer userId);

    /**
     * 新建或编辑角色时查询角色代码已存在
     * @param code
     * @param name
     * @return int
     */
    public int queryRoleCodeNameExist (@Param("code")String code,@Param("name")String name,@Param("id")Integer id);

    /**
     * 分页查询所有用户已分配角色
     * @param search 支持用户名模糊查询,可传null
     * @param order 支持排序,可传null
     * @return UserRoleAllocationBo
     */
    public List<UserRoleAllocationBoIns> queryAllUserRolePage(Page page,@Param("search")String search, @Param("order")String order);

    /**
     * 根据用户ID查询所有角色的分配情况
     * @param userId
     * @return SingleUserRoleAllocationBo
     */
    public List<SingleUserRolesBoIns> queryRoleAllocationByUserId(@Param("userId")String userId);

    /**
     * 根据用户ID删除所有已分配角色
     * @param userId
     * @return
     */
    public void deleteAllRoleByUserId(Integer userId);

    /**
     * 用户角色分配页面，查询翻页总数据量
     * @return int
     */
    public int queryNumOfUser ();


    /**
     * 删除角色前查询角色下是否已分配权限
     * @param roleId
     * @return int
     */
    public int queryPerAllocationNumOfRole(Integer roleId);
}

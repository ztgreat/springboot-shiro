package com.springboot.shiro.mapper;

import com.springboot.shiro.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ztgreat
 * @since 2018-09-29
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 批量插入用户角色关系
     */
    public int batchInsertUserRoles(List<SysUserRole> list);

    /**
     * 删除某个用户的所有角色
     */
    public int deleteUserRoleByUser(Integer userId);
}

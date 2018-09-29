package com.springboot.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.shiro.base.CommonConstant;
import com.springboot.shiro.entity.SysRole;
import com.springboot.shiro.entity.SysUserRole;
import com.springboot.shiro.entity.ins.SingleUserRolesBoIns;
import com.springboot.shiro.entity.ins.UserRoleAllocationBoIns;
import com.springboot.shiro.mapper.SysRoleMapper;
import com.springboot.shiro.mapper.SysUserRoleMapper;
import com.springboot.shiro.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zt
 * @since 2018-09-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {



    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;



    @Override
    public List<SysRole> getRoleByUserId(Integer userId) {
        return sysRoleMapper.getRoleByUserId(userId);
    }

    @Override
    public IPage<SysRole> page(int pageNum, int pageSize, String search) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"name","%" + search.trim() + "%");
        }
        Page<SysRole>page = new Page<>(pageNum,pageSize);
        return sysRoleMapper.selectPage(page,wrapper);
    }

    @Override
    public String saveRole(SysRole role) {

        //判断是否激活
        if(null == role.getStatus() || !CommonConstant.UsedStatus.USABLE.equals(role.getStatus())){
            role.setStatus(CommonConstant.UsedStatus.UNUSABLE);
        }
        if(role.getId()!=null){
            //判断角色代码和名称是否已经存在
            if (!queryRoleCodeNameExist(role.getCode(),role.getName(),role.getId())) {
                throw new RuntimeException( CommonConstant.Message.ROLE_CODENAME_EXIST);
            }
            updateById(role);
        }else {
            //判断角色代码和名称是否已经存在
            if (!queryRoleCodeNameExist(role.getCode(),role.getName(),null)) {
                throw new RuntimeException( CommonConstant.Message.ROLE_CODENAME_EXIST);
            }
            save(role);
        }

        return "操作成功";
    }

    /**
     * 新建或编辑角色时查询角色代码或名称已存在
     *
     * @param code,name,id
     * @return boolean
     */

    public boolean queryRoleCodeNameExist(String code, String name, Integer id) {

        int count = 0;
        count = sysRoleMapper.queryRoleCodeNameExist(code, name, id);

        if (count > 0)
            return false;
        else
            return true;
    }

    /**
     * 分页查询所有用户已分配角色
     *
     * @param pageNum
     * @param pageSize
     * @param search
     *            支持用户名模糊查询,可传null
     * @param order
     *            支持排序,可传null
     * @return UserRoleAllocationBo （可根据需要复写toString方法）
     */
    @Override
    public Page<UserRoleAllocationBoIns> queryUserRoleByPage(String search, Integer pageNum, Integer pageSize,
                                                                 String order) {

        if (StringUtils.isBlank(search)) {
            search = null;
        } else {
            search = "%" + search.trim() + "%";
        }
        if (StringUtils.isBlank(order)) {
            order = null;
        }
        Page<UserRoleAllocationBoIns>page = new Page(pageNum,pageSize);
        List<UserRoleAllocationBoIns> list=  this.sysRoleMapper.queryAllUserRolePage(page,search, order);
        page.setRecords(list);
        return page;
    }

    /**
     * 根据用户ID查询所有角色的分配情况
     *
     * @param userId
     * @return SingleUserRoleAllocationBo
     */
    public List<SingleUserRolesBoIns> queryRoleAllocationByUserId(String userId) {
        List<SingleUserRolesBoIns> singleUserRolesBos = new ArrayList<SingleUserRolesBoIns>();
        singleUserRolesBos = sysRoleMapper.queryRoleAllocationByUserId(userId);
        return singleUserRolesBos;
    }

    /**
     * 更新用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public void updateUserRoles(Integer userId, List<Integer> roleIds) {

        deleteAllRoleByUserId(userId);
        // 事务测试
        // if(roleIdArray.length<=1) throw new RuntimeException("test");
        for (int i = 0; i < roleIds.size(); i++) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleIds.get(i));
            sysUserRoleMapper.insert(sysUserRole);
        }
		/*
		 * 用户角色变更后 此处应考虑添加清空当前缓存的用户角色信息
		 * 使用realm中的clearCachedAuthorizationInfo()方法
		 */
    }

    /**
     * 插入单个角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public void updateUserRole(String userId, String roleId) {

        // 用户角色关系是否存在标志
        boolean existFlag = false;
        List<SingleUserRolesBoIns> singleUserRolesBos = new ArrayList<SingleUserRolesBoIns>();
        singleUserRolesBos = queryRoleAllocationByUserId(userId);
        Iterator<SingleUserRolesBoIns> iterator = singleUserRolesBos.iterator();
        while (iterator.hasNext()) {
            SingleUserRolesBoIns singleUserRolesBoIns = iterator.next();
            if (singleUserRolesBoIns.getRoleId() == roleId) {
                existFlag = true;
                break;
            }
        }
        if (!existFlag) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(Integer.parseInt(userId));
            sysUserRole.setRoleId(Integer.parseInt(roleId));
            sysUserRoleMapper.insert(sysUserRole);
        }

    }

    /**
     * 查询单个用户角色是否存在
     *
     * @param userId
     * @param roleId
     * @return boolean
     */
    public boolean queryExistUserRole(String userId, String roleId) {
		/*
		 * 代码未实现
		 */
        return true;
    }

    /**
     * 根据用户ID删除所有已分配角色
     *
     * @param userId
     * @return
     */
    @Override
    public void deleteAllRoleByUserId(int userId) {
        sysRoleMapper.deleteAllRoleByUserId(userId);
    }

    /**
     * 用户角色分配页面，查询翻页总数据量
     *
     * @return int
     */
    public int queryNumOfUser() {
        return sysRoleMapper.queryNumOfUser();
    }

    /**
     * 删除角色前查询角色下是否已分配权限
     *
     * @param roleId
     * @return int
     */
    public int queryPerAllocationNumOfRole(int roleId) {
        return sysRoleMapper.queryPerAllocationNumOfRole(roleId);
    }

    @Override
    public List<String> getRoleStrByUserId(Integer userId) {
        List<SysRole> roles = sysRoleMapper.getRoleByUserId(userId);
        List<String> userRole = new ArrayList<String>();
        for (SysRole sr : roles) {
            userRole.add(sr.getCode());
        }
        return userRole;
    }
}

package com.springboot.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.shiro.entity.SysPermission;
import com.springboot.shiro.entity.SysRolePermission;
import com.springboot.shiro.entity.ins.PermissionTreeIns;
import com.springboot.shiro.mapper.SysPermissionMapper;
import com.springboot.shiro.mapper.SysRolePermissionMapper;
import com.springboot.shiro.service.SysPermissionService;
import com.springboot.shiro.util.CollectionUtil;
import com.springboot.shiro.util.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zt
 * @since 2018-09-29
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysPermission> getPermissionByUserId(Integer userId) {
        return sysPermissionMapper.getPermissionByUserId(userId);
    }

    @Override
    public String savePermission(SysPermission permission) throws RuntimeException {

        try {

            if(permission.getId()!=null){
                updateById(permission);
            }else {
                save(permission);
            }
        } catch (Exception e) {

            LoggerUtils.error(getClass(), "权限资源保存或者更新失败:" + e.getMessage());
            throw new RuntimeException("资源权限操作失败");
        }
        return "资源权限操作成功";
    }

    @Override
    public IPage<SysPermission> page(int pageNum, int pageSize, String search, Integer parentId) {

        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();

        search= StringUtils.trim(search);
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"name","%" + search + "%");
        }
        if(parentId!=null){
            wrapper.eq(true,"parentId",parentId);
        }
        wrapper.orderBy(true,true,"level");
        wrapper.orderBy(true,true,"id");
        Page<SysPermission>page = new Page<>(pageNum,pageSize);
        return sysPermissionMapper.selectPage(page,wrapper);
    }

    @Override
    public List<SysPermission> getPermissionByParentId(Integer parentId) {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        if (parentId!=null) {
            wrapper.eq(true,"parentId",parentId);
        }
        return sysPermissionMapper.selectList(wrapper);
    }

    @Override
    public Page<PermissionTreeIns> getPermissionTree(int pageNum,int pageSize,Integer parentId) {

        Page<PermissionTreeIns>page = new Page<>(pageNum,pageSize);
        List<PermissionTreeIns> lists = sysPermissionMapper.getPermissionTree(page,parentId);
        page.setRecords(lists);
        return page;
    }

    @Override
    public List<PermissionTreeIns> getPermissionTree(Integer parentId) {

        List<PermissionTreeIns> lists = sysPermissionMapper.getPermissionTree(null,parentId);
        return lists;
    }

    @Override
    public List<SysPermission> getPermissionByRoleId(Integer roleId) {
        return sysPermissionMapper.getPermissionByRoleId(roleId);
    }

    @Override
    public boolean updatePermission(Integer roleId, List<Integer> permissionIds) throws RuntimeException{

        //原来有的权限
        List<SysPermission> permissions=getPermissionByRoleId(roleId);

        // 原权限资源id 集合
        List<Integer>ls=new ArrayList<>();

        // 现权限资源id 集合
        List<Integer>ls2=new ArrayList<>();

        for(SysPermission permission: permissions){
            ls.add(permission.getId());

        }
        for(Integer id: permissionIds){
            ls2.add(id);
        }

        Set<Integer> needDelete= CollectionUtil.diff(ls, ls2);
        Set<Integer> neddAdd = CollectionUtil.diff(ls2, ls);

        //需要添加的权限资源
        List<SysRolePermission>adds=new ArrayList<>();

        for(Integer id:neddAdd){
            SysRolePermission srp=new SysRolePermission();
            srp.setRoleId(roleId);
            srp.setPermId(id);
            adds.add(srp);
        }

        if(!needDelete.isEmpty()){
            QueryWrapper<SysRolePermission> wrapper = new QueryWrapper<>();
            wrapper.eq(true,"roleId", roleId);
            wrapper.eq(true,"permId", needDelete);
            sysRolePermissionMapper.delete(wrapper);
        }
        if(!neddAdd.isEmpty()){
            for(SysRolePermission permission:adds ){
                sysRolePermissionMapper.insert(permission);
            }
        }
        return true;
    }

    @Override
    public String deleteBatch(List<String>ids) {
        try {
            sysPermissionMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "权限资源删除失败:" + e.getMessage());
            throw new RuntimeException("删除失败");
        }
        return "删除成功";
    }
}

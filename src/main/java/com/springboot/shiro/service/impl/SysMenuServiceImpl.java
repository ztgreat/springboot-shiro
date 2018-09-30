package com.springboot.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.shiro.entity.SysMenu;
import com.springboot.shiro.entity.SysRoleMenu;
import com.springboot.shiro.entity.ins.AuthMenuTreeIns;
import com.springboot.shiro.mapper.SysMenuMapper;
import com.springboot.shiro.mapper.SysRoleMenuMapper;
import com.springboot.shiro.service.SysMenuService;
import com.springboot.shiro.util.CollectionUtil;
import com.springboot.shiro.util.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;



    @Override
    public List<SysMenu> getMenuByUserId(Integer userId) {
        return sysMenuMapper.getMenuByUserId(userId);
    }

    @Override
    public String saveMenu(SysMenu menu) throws RuntimeException {

        Date data = new Date();
        try {
            if(menu.getId()==null){
                menu.setCreateTime(data);
                menu.setUpdateTime(data);
                save(menu);
            }else{
                menu.setUpdateTime(data);
                updateById(menu);
            }
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "保存或者更新菜单失败:" + e.getMessage());
            throw new RuntimeException("菜单操作失败");
        }
        return "菜单操作成功";
    }

    @Override
    public List<AuthMenuTreeIns> getMenuTreeByUserId(Integer userId) {
        return sysMenuMapper.getMenuTreeByUserId(userId);
    }
    @Override
    public List<AuthMenuTreeIns> getMenuTree(Integer parentId) {
        return sysMenuMapper.getMenuTree(parentId);
    }
    @Override
    public boolean updatePermission(Integer roleId, List<Integer> menuIds) throws RuntimeException{

        //原来有的权限
        List<SysMenu> menus=getMenuByRoleId(roleId);

        // 原权限资源id 集合
        List<Integer>ls=new ArrayList<>();

        // 现权限资源id 集合
        List<Integer>ls2=new ArrayList<>();

        for(SysMenu menu: menus){
            ls.add(menu.getId());

        }
        for(Integer id: menuIds){
            ls2.add(id);
        }

        Set<Integer> needDelete= CollectionUtil.diff(ls, ls2);
        Set<Integer> neddAdd = CollectionUtil.diff(ls2, ls);

        //需要添加的权限资源
        List<SysRoleMenu>adds=new ArrayList<>();

        for(Integer id:neddAdd){
            SysRoleMenu srm=new SysRoleMenu();
            srm.setRoleId(roleId);
            srm.setMenuId(id);
            adds.add(srm);
        }

        if(!needDelete.isEmpty()){

            for(Integer menuId:needDelete){
                QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
                wrapper.eq(true,"role_id",roleId);
                wrapper.in(true,"menu_id",menuId);
                sysRoleMenuMapper.delete(wrapper);
            }

        }
        if(!neddAdd.isEmpty()){
            for(SysRoleMenu roleMenu:adds){
                sysRoleMenuMapper.insert(roleMenu);
            }

        }
        return true;
    }

    @Override
    public List<SysMenu> getMenuByRoleId(Integer roleId) {
        return sysMenuMapper.getMenuByRoleId(roleId);
    }

    @Override
    public IPage<SysMenu> page(int pageNum, int pageSize, String search, Integer parentId) {


        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        search=StringUtils.trim(search);
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"name","%" + search + "%");
        }
        if(parentId!=null){
            wrapper.eq(true,"parent_id", parentId);
        }
        wrapper.orderBy(true,true,"level");
        wrapper.orderBy(true,true,"id");


        Page<SysMenu>page = new Page<>(pageNum,pageSize);

        return sysMenuMapper.selectPage(page,wrapper);
    }

    @Override
    public String deleteBatch(List<String> ids) throws  RuntimeException {

        try {

            sysMenuMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "批量删除菜单失败:" + e.getMessage());
            throw new RuntimeException("删除菜单失败");
        }
        return "删除菜单成功";
    }
}

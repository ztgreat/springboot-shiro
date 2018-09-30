package com.springboot.shiro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.shiro.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ztgreat
 * @since 2018-09-29
 */
public interface SysUserService extends IService<SysUser> {


    SysUser getUserByUserName(String userName);


    /**
     * 通过关键词搜索分页
     * @param search
     * @return
     */
    IPage<SysUser> page(Integer pageNum, Integer pageSize, String search);

    /**
     * 保存用户
     * @param user
     * @return
     */
    String saveUser(SysUser user) throws  RuntimeException;


    /**
     * 更新 系统用户登录时间
     * @param id
     * @return
     */
    String updateLoginTime(Integer id) throws  RuntimeException;

    /**
     * 修改密码
     * @param id ,password
     * @return
     */
    String updateUserPwd (String password,Integer id) throws  RuntimeException;

    String delete(List<Integer> ids) throws  RuntimeException;
}

package com.example.demo.service;

import com.example.demo.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zt
 * @since 2018-09-30
 */
public interface TestService extends IService<Test> {

    public List<Test> getAll();
}

package com.example.demo.service.impl;

import com.example.demo.entity.Test;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zt
 * @since 2018-09-30
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Override
    public List<Test> getAll() {
        return testMapper.getAll();
    }
}

package com.example.demo.mapper;

import com.example.demo.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2018-09-30
 */
public interface TestMapper extends BaseMapper<Test> {

    public List<Test>getAll();
}

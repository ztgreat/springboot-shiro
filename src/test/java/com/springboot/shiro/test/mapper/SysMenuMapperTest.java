package com.springboot.shiro.test.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.shiro.entity.SysMenu;
import com.springboot.shiro.mapper.SysMenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuMapperTest {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Test
	public void selectListTest() {
		List<SysMenu>sysMenus = sysMenuMapper.selectList(null);
		System.out.println(sysMenus.size());
	}

	@Test
	public void pageTest() {
		Page<SysMenu>page = new Page<>(1,4);
		sysMenuMapper.selectPage(page,null);
		System.out.println("page:"+page.getRecords().size());
	}

}

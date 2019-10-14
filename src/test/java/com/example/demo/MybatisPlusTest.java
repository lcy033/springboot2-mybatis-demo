package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mapper.GspMenuMapper;
import com.example.model.GspMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MybatisPlusTest {

	@Autowired
	private GspMenuMapper gspMenuMapper;

	@Test
	public void testSelect(){
		log.info("----- selectAll method test ------");
		List<GspMenu> list = gspMenuMapper.selectList(null);
		Assert.assertEquals(4, list.size());
		list.forEach(System.out::println);
	}

	@Test
	public void testInsert(){
		GspMenu gspMenu = new GspMenu();
		gspMenu.setMenuName("测试");
		gspMenu.setMenuDesc("测试");
		gspMenu.setMenuFatherId(1);
		gspMenu.setMenuUrl("测试");
		gspMenu.setEnable(true);
		int result = gspMenuMapper.insert(gspMenu);
		log.info("result:{}", result);
	}

	@Test
	public void deleteById(){
		int result = gspMenuMapper.deleteById(9L);
		log.info("result:{}", result);
	}

	@Test
	public void deleteByMap(){
		Map<String, Object> map = new HashMap<>(2);
		map.put("menu_name", "ddd");
		map.put("menu_url", "ddd");
		int result = gspMenuMapper.deleteByMap(map);
		log.info("result:{}", result);
	}

	@Test
	public void delete(){
		GspMenu gspMenu = new GspMenu();
		gspMenu.setMenuName("ssss");
		gspMenu.setMenuUrl("ssss");
		int result = gspMenuMapper.delete(new QueryWrapper<>(gspMenu));
		log.info("result:{}", result);

		int result1 = gspMenuMapper.delete(new QueryWrapper<GspMenu>().lambda()
				.eq(GspMenu::getMenuName, "ssss"));
		log.info("result:{}", result1);
	}

	@Test
	public void deleteBatchIds(){
		List<Long> list = new ArrayList<>();
		list.add(55L);
		list.add(51L);
		list.add(52L);
		int result = gspMenuMapper.deleteBatchIds(list);
		log.info("result:{}", result);
	}

	@Test
	public void updateById(){
		GspMenu gspMenu = new GspMenu();
		gspMenu.setId(17L);
		gspMenu.setMenuName("ssss");
		gspMenu.setMenuUrl("ssss");
		int result = gspMenuMapper.updateById(gspMenu);
		log.info("result:{}", result);
	}

	@Test
	public void update(){
		GspMenu gspMenu = new GspMenu();
		gspMenu.setMenuName("dddd");
		gspMenu.setMenuUrl("ddddd");
		int result = gspMenuMapper.update(gspMenu, new UpdateWrapper<GspMenu>().lambda()
		.eq(GspMenu::getMenuName, "ssss"));
		log.info("result:{}", result);
	}

}

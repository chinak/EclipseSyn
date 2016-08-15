package org.seckill.dao;


import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)

//tell JUNIT the Spring configuration
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	//注入Dao实现类依赖
	@Resource
	private SeckillDao seckillDao;

	/**
	 * 配置SPRING和JUNIT整合，JUNIT启动时加载IOC容器
	 */
	
	
	
	
	@Test
	public void testQueryById() {
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() {
		fail("Not yet implemented");
	}
	@Test
	public void testReduceNumber() {
		
		
		fail("Not yet implemented");
	}



}

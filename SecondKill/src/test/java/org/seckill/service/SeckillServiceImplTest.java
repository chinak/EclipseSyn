package org.seckill.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
	
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		fail("Not yet implemented");
	}

	@Test	
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportSeckillUrl() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteSeckill() {
		fail("Not yet implemented");
	}

}

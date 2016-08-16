package org.seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

//tell JUNIT the Spring configuration
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKill() {
		long id = 1000L;
		long phone = 18017210213L;
		int insertCount = successKilledDao.insertSuccessKill(id, phone);
		System.out.println(insertCount);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long id = 1000L;
		long phone = 18017210213L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
		System.out.println("================"+successKilled);
		System.out.println("================="+successKilled.getSeckill());
	}

}

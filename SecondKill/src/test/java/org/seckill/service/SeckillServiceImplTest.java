package org.seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list:{}",list);
	}

	@Test	
	public void testGetById() {
		long seckillId = 1000L;
		Seckill seckill = seckillService.getById(seckillId);
		logger.info("seckil={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long id = 1000L;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.info("Exposer:{}",exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long seckillId = 1000L;
		long userPhone = 12314654646L;
		String md5 = "a0e8f9d4b56119f5a0bc2dee7b40649d";
		try {
			SeckillExcution seckillExcution = seckillService.executeSeckill(seckillId, userPhone, md5);
			logger.info("Excution:{}",seckillExcution);
		} catch (RepeatKillException e) {
			logger.error(e.getMessage());
		}
		catch (SeckillCloseException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	@Test
	public void testSekillLogic() throws Exception{
		long id = 1000L;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("Exposer:{}",exposer);
			long userPhone = 12314654646L;
			String md5 = exposer.getMd5();
			try {
				SeckillExcution seckillExcution = seckillService.executeSeckill(id, userPhone, md5);
				logger.info("Excution:{}",seckillExcution);
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			}
			catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			}
		}else{
			logger.warn("Exposer={}",exposer);
		}
		
		
		
	}

}










package org.seckill.service;

import java.util.Date;
import java.util.List;


import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	//获取Spring容器中的DAO的实例注入到service下面
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	private final String salt="asdfxczv@#$%^&";
	
	
	
	private String getMD5(long seckillId){
		String base = seckillId + "/"+salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	public List<Seckill> getSeckillList() {
		
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		
		Seckill seckill = seckillDao.queryById(seckillId);
		if(seckill==null){
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if(nowTime.getTime()<startTime.getTime()
				||nowTime.getTime()>endTime.getTime()){
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMD5(seckillId);
		//change the string
		return new Exposer(true, md5, seckillId);
	}

	@Override
	@Transactional
	public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		try {
			if(md5 == null || !md5.equals(getMD5(seckillId))){
				 throw new SeckillException("seckill data rewrite");
			}
			
			//reduce the goods and record the behaviour
			Date nowTime = new Date();
			int uodateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if(uodateCount <= 0 ){
				throw new SeckillCloseException("seckill is closed");
			} else {
				int insertCount = successKilledDao.insertSuccessKill(seckillId, userPhone);
				if(insertCount <= 0){
					throw new RepeatKillException("Repeat kill!");
				}else {
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch(SeckillCloseException e1){
			throw e1;
		}
		catch(RepeatKillException e2){
			throw e2;
		}

		catch (Exception e) {
			// TODO: handle exception
			throw new SeckillCloseException("seckill inner error:"+e.getMessage());
		}
		
		
		
		
		
	}
	
}

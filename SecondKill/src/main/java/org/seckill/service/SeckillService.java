package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * @author Dragon
 * logical interface 
 */
public interface SeckillService {
	
	/**
	 * @return all of the record of the sekill
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * query the single seckill record
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * if the kill begin the output the url or 
	 * the time the kill begin and the time now otherwise
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	
	/**
	 * Execute the seckill
	 * @param sekillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution executeSeckill(long seckillId, long userPhone, String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
	
	
	
	

}

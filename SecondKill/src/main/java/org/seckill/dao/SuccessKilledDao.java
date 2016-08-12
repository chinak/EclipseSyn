package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	
	/**
	 * Insert the detail of the achievement without repeat
	 * @param seckillId
	 * @param userphone
	 * @return the rows inserted
	 */
	int insertSuccessKill(long seckillId, long userphone);
	
	
	/**
	 * Query SuccessKilled By Id with SuccessKilled entity 
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(long seckillId);

}

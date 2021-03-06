package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	
	/**
	 * Insert the detail of the achievement without repeat
	 * @param seckillId
	 * @param userphone
	 * @return the rows inserted
	 */
	int insertSuccessKill(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);
	
	
	/**
	 * Query SuccessKilled By Id with SuccessKilled entity 
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}

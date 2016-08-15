package org.seckill.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	/**
	 * to reduce the storage
	 * @param seckillId
	 * @param killTime
	 * @return  >=1 if update successfully or 0 otherwise 
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	
	/**
	 * Query the commodity by id
	 * @param seckillId
	 * @return 
	 */
	Seckill queryById(long seckillId);
	
	
	/**
	 * Query All by offsets
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);//-->queryAll(arg0,arg1)otherwise
	
	
	
	
	

}

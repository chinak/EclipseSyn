package org.seckill.dao;


import java.util.Date;
import java.util.List;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	/**
	 * to reduce the storage
	 * @param seckillId
	 * @param killTime
	 * @return  >=1 if update successfully or 0 otherwise 
	 */
	int reduceNumber(long seckillId, Date killTime);
	
	
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
	List<Seckill> queryAll(int offset,int limit);
	
	
	
	
	

}

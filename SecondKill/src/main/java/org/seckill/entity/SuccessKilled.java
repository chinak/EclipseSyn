package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
	
	private long secKillId;
	
	private long userphone;
	
	private short state;
	
	private Date createTime;
	
	private Seckill seckill;

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}


	public long getSecKillId() {
		return secKillId;
	}

	public void setSecKillId(long secKillId) {
		this.secKillId = secKillId;
	}

	public long getUserphone() {
		return userphone;
	}

	public void setUserphone(long userphone) {
		this.userphone = userphone;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SuccessKilled [secKillId=" + secKillId + ", userphone=" + userphone + ", state=" + state
				+ ", createTime=" + createTime + "]";
	}
	
	

}

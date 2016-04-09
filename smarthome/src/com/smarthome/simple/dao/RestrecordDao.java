package com.smarthome.simple.dao;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Restrecord;

public interface RestrecordDao extends BaseDao<Restrecord> {
	
	/**
	 * 通过用户id 查询休息表数据
	 * @param id
	 * @return
	 */
	public Restrecord findByUserId(int id);

}

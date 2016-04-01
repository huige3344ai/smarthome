package com.smarthome.simple.dao;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.query.DevicesQuery;
import com.smarthome.util.Page;

public interface DevicesDao extends BaseDao<Devices> {
	
	/**
	 * 动态查询
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public Page getPage(DevicesQuery query,Integer offset ,Integer length);
	

}

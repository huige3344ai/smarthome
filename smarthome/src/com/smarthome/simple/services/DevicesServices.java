package com.smarthome.simple.services;

import java.util.List;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.DevicesQuery;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.util.Page;

public interface DevicesServices extends BaseService<Devices, DevicesQuery> {

	/**
	 * 获取页面数据
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public Page getCurrentPage(DevicesQuery query,Integer offset ,Integer length);

	
	/**
	 * 通过用户id查询房屋信息
	 * @param query
	 * @return
	 */
	public List<Home> findHomeByUid(DevicesQuery query);

	/**
	 * 通过房屋id查询房屋唯一的信息
	 * @param query
	 * @return
	 */
	public Home findHomeByHid(DevicesQuery query);
	
	
}

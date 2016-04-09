package com.smarthome.simple.dao;

import java.util.List;

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
	
	/**
	 * 通过房屋id 获取该用户的所有设备
	 * @param hid
	 * @return
	 */
	public List<Devices> getDevicesList(int hid);
	
	/**
	 * 获取个人所有设备
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public  Page getMyDevices(DevicesQuery query,Integer offset ,Integer length);
	

}

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
	 * 保存 设备
	 * @param model
	 */
	public void saveDevices(Devices model) ;

	/**
	 * 保存 设备
	 * @param model
	 * 
	 */
	public void deleteDevices(Devices model) throws ServiceException ;
	

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
	
	
	/**
	 * 获取用户一个房屋的所有设备
	 * @param query
	 * @return
	 */
	public Page getMyDevices(DevicesQuery query,Integer offset ,Integer length);
	
	/**
	 * 确认该设备是否属于 当前该用户 是否属于返回 不属于返回Null
	 * @param query
	 * @return
	 */
	public Devices confirmDevices(DevicesQuery query);
	
	/**
	 * 关闭  当前用户 选择的住所的所有设备
	 * @param query
	 * @return
	 */
	public String closeAllMyDevices(DevicesQuery query);
		
	
	
	
}

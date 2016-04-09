package com.smarthome.simple.dao;

import java.util.List;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.util.Page;

public interface HomeDao extends BaseDao<Home> {
	/**
	 * 获取设备位置
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public Page getPage(HomeQuery query,Integer offset ,Integer length);
	
	/**
	 * 根据 用户id 获取用户住处
	 * @param uid
	 * @return
	 */
	public List<Home> getHomeList(int uid);

}

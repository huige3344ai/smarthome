package com.smarthome.simple.services;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.util.Page;

public interface HomeServices extends BaseService<Home, HomeQuery> {

	/**
	 * 分页获取首页数据
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public Page getCurrentPage(HomeQuery query,Integer offset ,Integer length);
	
	/**
	 * 删除 住所信息
	 * @param home
	 */
	public void deleteHome(Home home);
	
}

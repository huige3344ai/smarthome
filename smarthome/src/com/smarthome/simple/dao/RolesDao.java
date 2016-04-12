package com.smarthome.simple.dao;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.query.RolesQuery;
import com.smarthome.util.Page;

public interface RolesDao extends BaseDao<Roles> {

	/**
	 * 动态查询
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public Page getPage(RolesQuery query,Integer offset ,Integer length);
	
}

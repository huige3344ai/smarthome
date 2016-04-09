package com.smarthome.simple.dao;

import java.util.List;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Permission;

public abstract interface PermissionDao extends BaseDao<Permission>
{
	
		/**
		 * 查询权限
		 * @param uid
		 * @return
		 */
	  public abstract List<String> hasPower(int uid);

}


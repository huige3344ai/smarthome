package com.smarthome.simple.services;

import java.util.List;

import com.smarthome.base.BaseService;
import com.smarthome.base.Query;
import com.smarthome.simple.entity.Permission;

public interface PermissionServices extends BaseService<Permission, Query> {

	/**
	 * 取得用户权限 信息
	 * @param uid
	 * @return
	 */
  public  List<String>  getPermission(int uid);
	
}

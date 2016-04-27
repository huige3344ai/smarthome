package com.smarthome.simple.services;

import java.util.List;
import java.util.Set;

import com.smarthome.base.BaseService;
import com.smarthome.base.Query;
import com.smarthome.simple.entity.Permission;

public interface PermissionServices extends BaseService<Permission, Query> {

	/**
	 * 取得用户权限 信息
	 * @param uid
	 * @return
	 */
  public  Set<String>  getPermission(int uid);
  
  /**
   * 获取所有 权限列表
   * @return
   */
  public List<Permission> getPermisson();
  
  /**
   * 获取所有 权限列表
   * @param roleId
   * @return
   */
  public List<Permission> getUpdatePermisson(int roleId);
	
}

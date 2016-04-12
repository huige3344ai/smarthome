package com.smarthome.simple.dao;

import java.util.List;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.entity.UserRoles;

public interface UserRolesDao extends BaseDao<UserRoles> {
	
	/**
	 * 查询用户id的所有角色
	 * @param uid
	 * @return
	 */
	public List<Roles> findByUid(int uid);

}

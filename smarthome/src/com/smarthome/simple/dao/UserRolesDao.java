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
	
	
	/**
	 * 更新用户的角色
	 * @param rids
	 * 	角色id集
	 * @param uid
	 * 	用户id
	 * @param adminId
	 * 	操作员id
	 * @return
	 */
	public String updateUserRoles(int []rids,int uid,int adminId);

}

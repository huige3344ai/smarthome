package com.smarthome.simple.services;

import java.util.List;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.query.RolesQuery;
import com.smarthome.util.Page;

public interface RolesServices extends BaseService<Roles, RolesQuery> {

	/**
	 * 获取页面数据
	 * @param query
	 * @param offset
	 * @param length
	 * @return
	 */
	public Page getCurrentPage(RolesQuery query,Integer offset ,Integer length);
	
	/**
	 * 保存 角色管理保存到 角色权限表
	 * @param query
	 * @param roles
	 */
	public String saveRoles(RolesQuery query,Roles roles);
	
	/**
	 * 更新角色信息 和权限列表
	 * @param query
	 * @param roles
	 * @return
	 */
	public String updateRoles(RolesQuery query,Roles roles);
	
	/**
	 * 删除角色
	 * @param query
	 * @return
	 */
	public String deletRoles(RolesQuery query);
	
	/**
	 * 授权时候获取所有角色 并且获取那些角色用户已经具备
	 * @param query
	 * @return
	 */
	public List<Roles> getRolesList(RolesQuery query);
	
	/**
	 * 更新用户角色
	 * @param query
	 * @return
	 */
	public String updateUserRoles(RolesQuery query);
	
}

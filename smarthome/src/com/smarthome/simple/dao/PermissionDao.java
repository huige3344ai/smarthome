package com.smarthome.simple.dao;

import java.util.List;
import java.util.Set;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Permission;

public abstract interface PermissionDao extends BaseDao<Permission>
{
	
		/**
		 * 查询权限
		 * @param uid
		 * @return
		 */
	  public abstract Set<String> hasPower(int uid);
	  
	  /**
	   * 获取所有权限
	   * @return
	   */
	  public List<Permission> getPermisson();
	  
	  /**
	   * 获取 该角色拥有的权限
	   * @param roleId
	   * @return
	   */
	  public List<Permission> getSelectedPermisson(int roleId);
	  
	  /**
	   *  把角色选择的权限保存到权限-角色表里面
	   * @param pid 权限id
	   * @param uid 操作用户id
	   * @param roleId 角色id
	   */
	  public void savePermission(int[] pid,int uid,int roleId);

	  /**
	   *  把角色选择的权限  更新权限-角色表数据
	   * @param pid 权限id
	   * @param uid 操作用户id
	   * @param roleId 角色id
	   */
	  public void updatePermission(int[] pid,int uid,int roleId);	
	  
	  /**
	   * 删除 PermissionRoles表中 改角色的所有数据
	   * @param rolesId
	   */
	  public void deletePermissionRoles(int rolesId);
	  
}


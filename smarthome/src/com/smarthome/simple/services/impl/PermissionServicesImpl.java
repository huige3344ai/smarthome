package com.smarthome.simple.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.base.Query;
import com.smarthome.simple.dao.PermissionDao;
import com.smarthome.simple.entity.Permission;
import com.smarthome.simple.services.PermissionServices;

@Transactional
public class PermissionServicesImpl extends BaseServiceImpl<Permission, Query>
		implements PermissionServices {

	@Resource
	PermissionDao permissionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<String> getPermission(int uid) {
		return permissionDao.hasPower(uid);
	}

	@Override
	public List<Permission> getPermisson() {
		return permissionDao.getPermisson();
	}

	@Override
	public List<Permission> getUpdatePermisson(int roleId) {
		return permissionDao.getSelectedPermisson(roleId);
	}
	
	

}

package com.smarthome.simple.services.impl;

import javax.annotation.Resource;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.PermissionDao;
import com.smarthome.simple.dao.RolesDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.query.RolesQuery;
import com.smarthome.simple.services.RolesServices;
import com.smarthome.util.Constans;
import com.smarthome.util.DateUtil;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

public class RolesServicesImpl extends BaseServiceImpl<Roles, RolesQuery> implements
		RolesServices {
	@Resource
	RolesDao rolesDao;
	@Resource
	PermissionDao permissionDao;
	
	@Override
	public Page getCurrentPage(RolesQuery query, Integer offset, Integer length) {
		return rolesDao.getPage( query,  offset,  length);
	}

	@Override
	public String saveRoles(RolesQuery query, Roles roles) {
			if(!OwnUtil.intIsZero(query.getUid())){
				roles.setRecordTime(DateUtil.getCurrDateStr());
				roles.setUserId(query.getUid());
				save(roles);
				if(!OwnUtil.objectIsEmpty(query.getPermission())&&query.getPermission().length>0){			
					permissionDao.savePermission(query.getPermission(),query.getUid(),roles.getId());
				}
				return Constans.RETRUN_SUCCESS_STATUS;
			}else
				return Constans.RETRUN_FAILED_STATUS;
		
	}

	@Override
	public String updateRoles(RolesQuery query, Roles roles) {
		Roles model = get(roles.getId());
		if(!OwnUtil.objectIsEmpty(roles)&&!OwnUtil.objectIsEmpty(model)){
			model.setRoleName(roles.getRoleName());
			model.setRole(roles.getRole());
			model.setExchangeTime(DateUtil.getCurrDateStr());
			update(model);
			permissionDao.updatePermission(query.getPermission(), query.getUid(), roles.getId());
			return Constans.RETRUN_SUCCESS_STATUS;
		}else
		return  Constans.RETRUN_FAILED_STATUS;
	}

	@Override
	public String deletRoles(RolesQuery query) {
		Roles role = get(query.getId());
		if(!OwnUtil.objectIsEmpty(role)){
			delete(role);
			permissionDao.deletePermissionRoles(query.getId());
		}
		return Constans.RETRUN_SUCCESS_STATUS;
	}

}

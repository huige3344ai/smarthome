package com.smarthome.simple.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.RolesDao;
import com.smarthome.simple.dao.UserRolesDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.entity.UserRoles;
import com.smarthome.util.OwnUtil;

public class UserRolesDaoImpl extends BaseDaoImpl<UserRoles> implements
		UserRolesDao {
	@Resource
	RolesDao rolesDao;
	
	@Override
	public List<Roles> findByUid(int uid) {
		StringBuffer hql = new StringBuffer("from UserRoles where 1=1 and userId="+uid);
		List<UserRoles> urs = (List<UserRoles>) findByhql(hql.toString());
		List<Roles> roles = new ArrayList<Roles>();
		if(OwnUtil.listisNotEmpty(urs)){
			for(UserRoles ur:urs){
				Roles role = rolesDao.findById(ur.getRoleId());
				roles.add(role);
			}
		}
		return roles;
	}

}

package com.smarthome.simple.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.base.Query;
import com.smarthome.simple.dao.PermissionDao;
import com.smarthome.simple.entity.Permission;
import com.smarthome.util.HQLparam;
import com.smarthome.util.OwnUtil;

public class PermissionDaoImpl extends BaseDaoImpl<Permission>
  implements PermissionDao
{

	@Override
	public List<String> hasPower(int uid) {
	    List permissonsList = new ArrayList();
	    //StringBuffer hql = new StringBuffer("select Permission from Permission p, PermissionUser pu where pu.userId = '" + uid + "' and ( p.id=pu.permissionId )";
	    StringBuffer hql = new StringBuffer("select new Permission( p.permission,  p.module) from Permission as p, PermissionUser as pu where 1=1 and  ( p.id=pu.permissionId )");
	    hql = HQLparam.appendAnd("userId", hql, "pu");
	    org.hibernate.Query query =  createEQuery(hql.toString(), uid);
	    List permissons = query.list();
	    if (!OwnUtil.objectIsEmpty(permissons))
	    {
	      Iterator iterator = permissons.iterator();
	      while (iterator.hasNext()) {
	        Permission permisson = (Permission)iterator.next();
	        StringBuffer permission_str = new StringBuffer(permisson.getModule());
	        permission_str.append("_");
	        permission_str.append(permisson.getPermission());
	        permissonsList.add(permission_str.toString());
	      }
	    }
	    return permissonsList;
	}
}
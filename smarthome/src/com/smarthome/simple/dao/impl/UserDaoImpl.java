package com.smarthome.simple.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.PermissionDao;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.entity.Permission;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;

public class UserDaoImpl extends BaseDaoImpl<User>
  implements UserDao
{

  @Resource
  private PermissionDao permissionDao;

  @Transactional(rollbackFor={ServiceException.class})
  public String getAutoLoginStauts(UserQuery query)
  {
    String hql = "from User where userName ='" + query.getUserName() + "' and  sessionId='" + query.getSessionid() + "'";
    List list = findAllByhql(hql);
    if (list.size() > 0) {
      return "on";
    }
    return "off";
  }

  public List<String> hasPower(int uid)
  {
    List permissonsList = new ArrayList();
    String hql = "select Permission from Permission p, PermissionUser pu where pu.userId = '" + uid + "' and ( p.id=pu.permissionId )";
    List permissons = this.permissionDao.findAllByhql(hql);
    if (permissons != null)
    {
      Iterator iterator = permissons.iterator();
      while (iterator.hasNext()) {
        Permission permisson = (Permission)iterator.next();
        permissonsList.add(permisson.getPermission());
      }
    }
    return permissonsList;
  }

  public void setPermissionDao(PermissionDao permissionDao) {
    this.permissionDao = permissionDao;
  }
}
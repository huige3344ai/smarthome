package com.smarthome.simple.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.PermissionDao;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.entity.Permission;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.util.OwnUtil;

public class UserDaoImpl extends BaseDaoImpl<User>
  implements UserDao
{

  @Resource
  private PermissionDao permissionDao;

  @Transactional(rollbackFor={ServiceException.class})
  public String getAutoLoginStauts(UserQuery query)
  {
    String hql = "from User where userName ='" + query.getUserName() + "' and  sessionId='" + query.getSessionid() + "'";
    List list = findByhql(hql);
    if (list.size() > 0) {
      return "on";
    }
    return "off";
  }

  public List<String> hasPower(int uid)
  {
    List permissonsList = new ArrayList();
    String hql = "select Permission from Permission p, PermissionUser pu where pu.userId = '" + uid + "' and ( p.id=pu.permissionId )";
    List permissons = this.permissionDao.findByhql(hql);
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

@Override
public User findBySessionUserName(UserQuery paramUserQuery) {
	User user = null;
	String hql = "from User where userName=? and sessionId=?";
	String[] str ={paramUserQuery.getUserName(),paramUserQuery.getSessionid()};
	Query query = createEQuery(hql,str);
    List<User> list_t = query.list();
    if(OwnUtil.ListisNotEmpty(list_t)){
    	user =  list_t.get(0);
    }   
	return user;
	
}
}
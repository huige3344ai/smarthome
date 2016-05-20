package com.smarthome.simple.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.dao.UserRolesDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.entity.TipNews;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.util.HQLparam;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

public class UserDaoImpl extends BaseDaoImpl<User>
  implements UserDao
{

	@Resource	
	UserRolesDao userRolesDao;
	
  @Transactional(rollbackFor={ServiceException.class})
  public String getAutoLoginStauts(UserQuery query)
  {
    String hql = "from User where userName ='" + query.getUserName() + "' and  sessionId='" + query.getSessionid() + "'";
    List list = findByhql(hql);
    if (OwnUtil.listisNotEmpty(list)) {
      return "on";
    }
    return "off";
  }



@Override
public User findBySessionUserName(UserQuery paramUserQuery) {
	User user = null;
	String hql = "from User where 1=1 and status=1 and userName=? and sessionId=?";
	String[] str ={paramUserQuery.getUserName(),paramUserQuery.getSessionid()};
	Query query = createEQuery(hql,str);
    List<User> list_t = query.list();
    if(OwnUtil.listisNotEmpty(list_t)){
    	user =  list_t.get(0);
    }   
	return user;
	
}

@Override
public Page getUserPage(UserQuery query, Integer offset, Integer length) {
	StringBuffer hql = new StringBuffer("select new User(u.id, u.logoImage, u.userName, u.status, u.phone, u.email, u.registerTime, u.isAdmin) from User as u  where  1=1");
	if(!OwnUtil.stringIsEmpty(query.getUserName())){
		hql = HQLparam.appendAndLike("userName", hql,"u");
	} 
	if(!OwnUtil.stringIsEmpty(query.getPhone())){
		hql = HQLparam.appendAndLike("phone", hql,"u");
	}
	if(!OwnUtil.stringIsEmpty(query.getEmail())){
		hql = HQLparam.appendAndLike("email", hql,"u");
	}
	Object[] objects = HQLparam.returnObjects(query, 3, "userName","phone","email");//动态获取变量
	Page<User> page = new Page<User>();
	if(OwnUtil.objectIsEmpty(objects)){
		hql = HQLparam.appendAndLike("userName", hql,"u");
		hql.append(" order by u.registerTime desc");
		page = findByPage(hql.toString(), offset, length,"");
	}else{
		hql.append(" order by u.registerTime desc");
		page = findByPage(hql.toString(), offset, length,objects);
	}
	if(!OwnUtil.objectIsEmpty(page)){
		for(int index = 0 ; index <page.size();index++){
			int uid = page.get(index).getId();
			List<Roles> roles= userRolesDao.findByUid(uid);
			if(OwnUtil.listisNotEmpty(roles)){
				page.get(index).setRoles(roles);
			}
		}
	}

	return page;
	
}



private void findByUid(int uid) {
	// TODO Auto-generated method stub
	
}



@Override
public List<TipNews> getTipNews() {
	StringBuffer hql = new StringBuffer("from TipNews where 1=1 ");
	List list = findByhql(hql.toString());
	return list;
}


}
package com.smarthome.simple.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.RolesDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.query.RolesQuery;
import com.smarthome.util.HQLparam;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

@Transactional
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao {

	
	@Override
	public Page getPage(RolesQuery query, Integer offset, Integer length) {
		StringBuffer hql = new StringBuffer("from Roles as r where 1=1 ");
		if(!OwnUtil.stringIsEmpty(query.getRoleName())){
			hql = HQLparam.appendAndLike("roleName", hql,"r");
		} 		
		Object[] objects = HQLparam.returnObjects(query, 1, "roleName");//动态获取变量
		if(OwnUtil.objectsIsEmpty(objects)){
			hql = HQLparam.appendAndLike("roleName", hql,"r");
			hql.append(" order by r.exchangeTime desc");
			return findByPage(hql.toString(), offset, length,"");
		}else{
			hql.append(" order by r.exchangeTime desc");
			return findByPage(hql.toString(), offset, length,objects);
		}		
	}
	
	

}

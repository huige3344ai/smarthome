package com.smarthome.simple.dao.impl;

import java.util.List;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.HomeDao;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.util.HQLparam;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

public class HomeDaoImpl extends BaseDaoImpl<Home> implements HomeDao {

	@Override
	public Page getPage(HomeQuery query, Integer offset, Integer length) {
		StringBuffer hql = new StringBuffer("select new Home( h.id, h.country, h.address, u.userName, h.temperature, h.humidity, h.recordTime) from Home as h ,User as u where 1=1 and u.id=h.ownerId ");
		if(!OwnUtil.stringIsEmpty(query.getUserName())){
			HQLparam.appendAndLike("userName", hql, "u");
		}
		if(!OwnUtil.stringIsEmpty(query.getCountry())){
			HQLparam.appendAndLike("country", hql, "h");
		}
		if(!OwnUtil.stringIsEmpty(query.getAddress())){
			HQLparam.appendAndLike("address", hql, "h");
		}
		Object[] objects = HQLparam.returnObjects(query, 3, "userName","country","address");//动态获取变量
		if(OwnUtil.objectsIsEmpty(objects)){
			hql = HQLparam.appendAndLike("userName", hql,"u");
			hql.append(" order by h.recordTime desc");
			return findByPage(hql.toString(), offset, length,"");		
		}else{
			hql.append(" order by h.recordTime desc");
			return findByPage(hql.toString(), offset, length, objects);
		}
		
	}

	@Override
	public List<Home> getHomeList(int uid) {
		StringBuffer hql = new StringBuffer("from Home as d where 1=1 and d.userId='"+uid+"'");
		return (List<Home>) findByhql(hql.toString());
	}

}

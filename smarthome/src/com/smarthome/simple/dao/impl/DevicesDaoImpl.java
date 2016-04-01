package com.smarthome.simple.dao.impl;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.DevicesDao;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.query.DevicesQuery;
import com.smarthome.util.HQLparam;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

public class DevicesDaoImpl extends BaseDaoImpl<Devices> implements DevicesDao {

	@Override
	public Page getPage(DevicesQuery query, Integer offset, Integer length) {
		StringBuffer hql = new StringBuffer("select new Devices(d.id, d.deviceName, u.userName, d.status, d.category,d.recordTime) from Devices as d,User as u where 1=1 and u.id=d.userId ");
		if(!OwnUtil.stringIsEmpty(query.getUserName())){
			hql = HQLparam.appendAndLike("userName", hql);
		} 
		if(!OwnUtil.stringIsEmpty(query.getDeviceName())){
			hql = HQLparam.appendAndLike("deviceName", hql);
		}
		Object[] objects = HQLparam.returnObjects(query, 2, "deviceName","userName");//动态获取变量
	
		if(OwnUtil.objectsIsEmpty(objects)){
			hql = HQLparam.appendAndLike("deviceName", hql);
			hql.append(" order by d.recordTime desc");
			return findByPage(hql.toString(), offset, length,"");
		}else{
			hql.append(" order by d.recordTime desc");
			return findByPage(hql.toString(), offset, length,objects);
		}
	}

}

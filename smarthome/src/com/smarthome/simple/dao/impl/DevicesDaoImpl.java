package com.smarthome.simple.dao.impl;

import java.util.List;

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
		StringBuffer hql = new StringBuffer("select new Devices(d.id, d.deviceName, u.userName, h.address, d.status, d.category,d.recordTime) from Devices as d,User as u ,Home as h where 1=1 and u.id=d.userId and h.id=d.homeId ");
		if(!OwnUtil.stringIsEmpty(query.getUserName())){
			hql = HQLparam.appendAndLike("userName", hql,"u");
		} 
		if(!OwnUtil.stringIsEmpty(query.getDeviceName())){
			hql = HQLparam.appendAndLike("deviceName", hql,"d");
		}
		if(!OwnUtil.stringIsEmpty(query.getAddress())){
			hql = HQLparam.appendAndLike("address", hql,"h");
		}
		Object[] objects = HQLparam.returnObjects(query, 3, "deviceName","userName","address");//动态获取变量
	
		if(OwnUtil.objectsIsEmpty(objects)){
			hql = HQLparam.appendAndLike("deviceName", hql,"d");
			hql.append(" order by d.recordTime desc");
			return findByPage(hql.toString(), offset, length,"");
		}else{
			hql.append(" order by d.recordTime desc");
			return findByPage(hql.toString(), offset, length,objects);
		}
	}

	@Override
	public List<Devices> getDevicesList(int hid) {
		StringBuffer hql = new StringBuffer(" from Devices where 1=1 and homeId= '"+hid+"'");
		return (List<Devices>) findByhql(hql.toString());
	}

	@Override
	public Page getMyDevices(DevicesQuery query, Integer offset, Integer length) {
		StringBuffer hql = new StringBuffer("select new Devices(d.id, d.status,d.deviceName) from Devices as d,User as u ,Home as h where 1=1 ");
		hql.append(" and d.userId=u.id and u.id='"+query.getUid()+"' ");
		if(!OwnUtil.intIsZero(query.getHomeId())){
			hql.append(" and d.homeId=h.id and h.id='"+query.getHomeId()+"'");
		}
		return findByFinallyPage(hql.toString(), offset, length, null);
	}

}

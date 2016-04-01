package com.smarthome.simple.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.DevicesDao;
import com.smarthome.simple.dao.HomeDao;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.DevicesQuery;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.simple.services.DevicesServices;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

@Transactional
public class DevicesServicesImpl extends BaseServiceImpl<Devices, DevicesQuery>
		implements DevicesServices {
	@Resource
	DevicesDao devicesDao;
	@Resource
	HomeDao homeDao;

	@Transactional(readOnly=true)
	@Override
	public Page getCurrentPage(DevicesQuery query,Integer offset ,Integer length) {
		return devicesDao.getPage( query,  offset,  length);
	}
	

	@Override
	public List<Home> findHomeByUid(DevicesQuery query) {
		StringBuffer hql = new StringBuffer("select new Home(h.id,h.country,h.address,h.ownerId) from Home as h where ownerId=");
		hql.append("'"+query.getUid()+"'");
		return (List<Home>) homeDao.findByhql(hql.toString());
	}


	@Override
	public Home findHomeByHid(DevicesQuery query) {
		StringBuffer hql = new StringBuffer("select new Home(h.id,h.country,h.address,h.ownerId) from Home as h where h.id=");
		hql.append("'"+query.getHomeId()+"'");
		List list = homeDao.findByhql(hql.toString());
		return  OwnUtil.listisNotEmpty(list)?null:(Home)list.get(0);
	}
}
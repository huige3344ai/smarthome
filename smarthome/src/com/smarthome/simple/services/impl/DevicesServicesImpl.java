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
import com.smarthome.simple.services.ServiceException;
import com.smarthome.util.Constans;
import com.smarthome.util.DateUtil;
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
	public void saveDevices(Devices model) {
		save(model);
		Home home = homeDao.findById(model.getHomeId());
		Integer num = OwnUtil.returnZero(home.getDeviceNum())+1;
		home.setDeviceNum(num);
		homeDao.update(home);
	}	

	@Override
	public void deleteDevices(Devices model) throws ServiceException {
		Home home = homeDao.findById(model.getHomeId());
		devicesDao.delete(model);
		Integer num = OwnUtil.returnZero(home.getDeviceNum())-1;
		if(num<0){
				throw new ServiceException("后台数据错误");
		}
		home.setDeviceNum(num);
		homeDao.update(home);
	}	
	

	@Override
	public List<Home> findHomeByUid(DevicesQuery query) {
		StringBuffer hql = new StringBuffer("select new Home(h.id,h.country,h.address,h.ownerId) from Home as h where ownerId=");
		hql.append("'"+query.getUid()+"'  order by h.recordTime desc");
		return (List<Home>) homeDao.findByhql(hql.toString());
	}


	@Override
	public Home findHomeByHid(DevicesQuery query) {
		StringBuffer hql = new StringBuffer("select new Home(h.id,h.country,h.address,h.ownerId) from Home as h where h.id=");
		hql.append("'"+query.getHomeId()+"'");
		List list = homeDao.findByhql(hql.toString());
		return  OwnUtil.listisNotEmpty(list)?(Home)list.get(0):null;
	}


	@Override
	public Page getMyDevices(DevicesQuery query,Integer offset ,Integer length) {
		return devicesDao.getMyDevices(query, offset, length);
	}


	@Override
	public Devices confirmDevices(DevicesQuery query) {
		if(!OwnUtil.intIsZero(query.getUid())&&!OwnUtil.intIsZero(query.getId())){
			StringBuffer hql = new StringBuffer(" from Devices as d where d.id='"+query.getId()+"' ");
			hql.append("and d.userId='"+query.getUid()+"'");
			List list = devicesDao.findByhql(hql.toString());
			return  OwnUtil.listisNotEmpty(list)?(Devices)list.get(0):null;
		}else
		return null;
	}


	@Override
	public String closeAllMyDevices(DevicesQuery query) {
		if(!OwnUtil.intIsZero(query.getUid())&&!OwnUtil.intIsZero(query.getHomeId())){
			StringBuffer hql = new StringBuffer(" from Devices as d where d.homeId='"+query.getHomeId()+"' ");
			hql.append("and d.userId='"+query.getUid()+"'");
			List<Devices> list = (List<Devices>) devicesDao.findByhql(hql.toString());
			if( OwnUtil.listisNotEmpty(list)){
				for(Devices device:list){
					if(OwnUtil.stringIsEqual(device.getStatus(),"1")){
						device.setStatus("0");
						device.setExchangeTime(DateUtil.getCurrDateStr());
						update(device);
					}
				}
				return Constans.RETRUN_SUCCESS_STATUS;
			}else
				return Constans.RETRUN_EXCEPTION_STATUS;
		}else
		return Constans.RETRUN_FAILED_STATUS;
	}






}
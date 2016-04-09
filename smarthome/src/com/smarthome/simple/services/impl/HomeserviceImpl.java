package com.smarthome.simple.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.DevicesDao;
import com.smarthome.simple.dao.HomeDao;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.simple.services.HomeServices;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

@Transactional
public class HomeserviceImpl extends BaseServiceImpl<Home, HomeQuery> implements
		HomeServices {
	@Resource
	protected HomeDao homeDao;
	@Resource
	protected DevicesDao  devicesDao;
	
	
	@Override
	public Page getCurrentPage(HomeQuery query, Integer offset, Integer length) {
		return homeDao.getPage(query, offset, length);
	}

	@Transactional(rollbackFor={Exception.class})
	@Override
	public void deleteHome(Home home) {
		if(!OwnUtil.objectIsEmpty(home)){
			List<Devices> devices =  devicesDao.getDevicesList(home.getId());
				if(OwnUtil.listisNotEmpty(devices)){
					for(Devices device:devices){
						devicesDao.delete(device);
					}
				}	
		}
		homeDao.delete(home);
		
	}

	
	


}

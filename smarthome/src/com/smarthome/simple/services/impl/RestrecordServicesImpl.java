package com.smarthome.simple.services.impl;

import javax.annotation.Resource;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.ResetSystemDao;
import com.smarthome.simple.dao.RestrecordDao;
import com.smarthome.simple.entity.ResetSystem;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.simple.query.RestrecordQuery;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.RestrecordServices;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.util.OwnUtil;

public class RestrecordServicesImpl extends
		BaseServiceImpl<Restrecord, RestrecordQuery> implements
		RestrecordServices {

	@Resource
	RestrecordDao restrecordDao;
	@Resource
	ResetSystemDao resetSystemDao;
	
	@Override
	public void saveRecord(Restrecord rest,UserQuery query) throws ServiceException {
		ResetSystem system = resetSystemDao.findById((int)query.getPeroid());
		if(!OwnUtil.objectIsEmpty(system)){
			rest.setRecommendRestTime(system.getStartTime());
			rest.setRecommendWakeTime(system.getRecommend());
		}
		restrecordDao.save(rest);
	}
	
	 

}

package com.smarthome.simple.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.TaskDao;
import com.smarthome.simple.entity.Memoradum;
import com.smarthome.simple.query.MemoradumQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.TaskServices;

@Transactional
public class TaskServicesImpl extends
		BaseServiceImpl<Memoradum, MemoradumQuery>  implements TaskServices{
	@Resource
	TaskDao memoradumDao;
	
	
	@Override
	public List<Memoradum> findByUid(String uid) throws ServiceException {
		return memoradumDao.findAllByProperty("userId", uid, null, true);
	}


	@Override
	public List<Memoradum> findByStart(MemoradumQuery query)
			throws ServiceException {
		return memoradumDao.findByStart(query);
	}

}

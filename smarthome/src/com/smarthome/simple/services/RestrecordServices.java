package com.smarthome.simple.services;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.simple.query.RestrecordQuery;
import com.smarthome.simple.query.UserQuery;

public interface RestrecordServices extends
		BaseService<Restrecord, RestrecordQuery> {
	
	/**
	 * 保存个人的推介休息时间
	 * @param query
	 * @param rest
	 * @throws ServiceException
	 */
	public void saveRecord(Restrecord rest,UserQuery query) throws ServiceException;

}

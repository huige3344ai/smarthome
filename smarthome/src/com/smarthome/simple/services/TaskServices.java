package com.smarthome.simple.services;

import java.util.List;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.Memoradum;
import com.smarthome.simple.query.MemoradumQuery;

public interface TaskServices extends BaseService<Memoradum, MemoradumQuery> {
	
	/**
	 * 通过uid查询该人的所有备忘录
	 * @param uid
	 * @return
	 * @throws ServiceException
	 */
	public List<Memoradum> findByUid(String uid) throws ServiceException;
	
	/**
	 * 通过用户id和开始结束时间(查询时间区间，非表中的开始结束时间)查询备忘录
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	public List<Memoradum> findByStart(MemoradumQuery query)throws ServiceException;

}

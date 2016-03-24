package com.smarthome.simple.dao;

import java.util.List;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.Memoradum;
import com.smarthome.simple.query.MemoradumQuery;

public interface TaskDao extends BaseDao<Memoradum> {
	
	/**
	 * 通过用户id和时间区间查询备忘录
	 * @param query
	 * @return
	 */
	public List<Memoradum> findByStart(MemoradumQuery query);

}

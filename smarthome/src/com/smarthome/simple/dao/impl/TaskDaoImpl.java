package com.smarthome.simple.dao.impl;

import java.util.List;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.TaskDao;
import com.smarthome.simple.entity.Memoradum;
import com.smarthome.simple.query.MemoradumQuery;

public class TaskDaoImpl extends BaseDaoImpl<Memoradum> implements TaskDao {

	@Override
	public List<Memoradum> findByStart(MemoradumQuery query) {
		String hql = "from Memoradum where userId=" +query.getUid()+" and startTime BETWEEN date_format('"+query.getStartTime()+"','%Y-%m-%d') and date_format('"+query.getEndTime()+"','%Y-%m-%d')";
		return (List<Memoradum>) findByhql(hql);
	}

}

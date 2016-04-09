package com.smarthome.simple.dao.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.RestrecordDao;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.util.OwnUtil;

public class RestrecordDaoImpl extends BaseDaoImpl<Restrecord> implements
		RestrecordDao {

	@Transactional(readOnly=true)
	@Override
	public Restrecord findByUserId(int id) {
		StringBuffer hql = new StringBuffer("from Restrecord where userId=");
		hql.append(id);
		List list = findByhql(hql.toString());
		return OwnUtil.listisNotEmpty(list)?(Restrecord)list.get(0):null;
	}

}

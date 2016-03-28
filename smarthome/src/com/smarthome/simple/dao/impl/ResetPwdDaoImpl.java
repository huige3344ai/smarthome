package com.smarthome.simple.dao.impl;

import java.util.List;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.ResetPwdDao;
import com.smarthome.simple.entity.ResetPwd;

public class ResetPwdDaoImpl extends BaseDaoImpl<ResetPwd> implements
		ResetPwdDao {

	@Override
	public List<ResetPwd> findByEmailAndValidate(String email, String validate) {
		String hql = "from ResetPwd as r where emailVer ='"+validate+"' and status=1  and r.uid=(select id from User as u where u.email='"+email+"')";
		return  (List<ResetPwd>) findByhql(hql);
	}

}

package com.smarthome.simple.dao;

import java.util.List;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.ResetPwd;

public interface ResetPwdDao extends BaseDao<ResetPwd> {
	
	/**
	 * 通过 邮箱 和校验码查询数据  status:{1:有效0:无效}
	 * @param email
	 * @param validate
	 * @return
	 */
	public List<ResetPwd> findByEmailAndValidate(String email,String validate);
	

}

package com.smarthome.simple.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.RolesDao;
import com.smarthome.simple.dao.UserRolesDao;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.entity.UserRoles;
import com.smarthome.util.Constans;
import com.smarthome.util.DateUtil;
import com.smarthome.util.OwnUtil;

@Transactional
public class UserRolesDaoImpl extends BaseDaoImpl<UserRoles> implements
		UserRolesDao {
	@Resource
	RolesDao rolesDao;
	@Resource
	UserRolesDao userRolesDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Roles> findByUid(int uid) {
		StringBuffer hql = new StringBuffer("from UserRoles where 1=1 and userId="+uid);
		List<UserRoles> urs = (List<UserRoles>) findByhql(hql.toString());
		List<Roles> roles = new ArrayList<Roles>();
		if(OwnUtil.listisNotEmpty(urs)){
			for(UserRoles ur:urs){
				Roles role = rolesDao.findById(ur.getRoleId());
				roles.add(role);
			}
		}
		return roles;
	}
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public String updateUserRoles(int[] rids,int uid,int adminId) {
		if(!OwnUtil.intIsZero(uid)&&!OwnUtil.intIsZero(adminId)){
			List<Roles> oldRoles = findByUid(uid);
			List<Roles> newRoles = getRids(rids);
			if(OwnUtil.listisNotEmpty(oldRoles)&&OwnUtil.listisNotEmpty(newRoles)){//新旧角色交集
				for(Iterator<Roles> iterOld = oldRoles.iterator();iterOld.hasNext();){
					int rid = iterOld.next().getId();
					for(Iterator<Roles> iterNew = newRoles.iterator();iterNew.hasNext();){
						int rid2 = iterNew.next().getId(); 
						if(rid2==rid){
							iterNew.remove();
							iterOld.remove();
						}
					}
				}
				if(OwnUtil.listisNotEmpty(newRoles)){
					for(Iterator<Roles> iterNew = newRoles.iterator();iterNew.hasNext();){
						saveUserRoles(iterNew.next().getId(),uid,adminId);
					}
				}
				if(OwnUtil.listisNotEmpty(oldRoles)){
					deleteUserRList(oldRoles,uid);//批量删除用户角色
				}
				
				if(OwnUtil.listisNotEmpty(oldRoles)||OwnUtil.listisNotEmpty(newRoles)){
					return Constans.RETRUN_SUCCESS_STATUS;
				}else
					return Constans.RETRUN_NOTHING_STATUS;
				
			}else if(!OwnUtil.listisNotEmpty(newRoles)){//新角色为空 清空该用户的角色
				deleteUserRList(newRoles,uid);//批量删除用户角色
				return Constans.RETRUN_SUCCESS_STATUS;
			}else if(!OwnUtil.listisNotEmpty(oldRoles)&&OwnUtil.listisNotEmpty(newRoles)){//用户之前角色为空 新增角色
				for(Iterator<Roles> iterNew = newRoles.iterator();iterNew.hasNext();){
					saveUserRoles(iterNew.next().getId(),uid,adminId);
				}
				return Constans.RETRUN_EXCEPTION_STATUS;
			}else
				return Constans.RETRUN_FAILED_STATUS;
		}else
			return Constans.RETRUN_FAILED_STATUS;
	
	}
	
	/**
	 * 查询返还 角色集
	 * @param rids
	 * @return
	 */
	public List<Roles> getRids(int[] rids){
		if(rids!=null){
			List<Roles> roles = new ArrayList<Roles>();
			for(int index = 0;index<rids.length;index++){
				roles.add(rolesDao.findById(rids[index]));
			}
			return roles;
		}else
		return null;
	}

	/**
	 * 保存用户角色表数据
	 * @param rid
	 * @param uid
	 * @param adminId
	 */
	public void saveUserRoles(int rid, int uid,int adminId){
		UserRoles model = new UserRoles();
		model.setRoleId(rid);
		model.setUserId(uid);
		model.setAdminId(adminId);
		model.setRecordTime(DateUtil.getCurrDateStr());
		userRolesDao.save(model);
	} 
	
	/**
	 * 通过uid 和 rid 删除 userRoles 表中的数据
	 * @param roles
	 */
	public void deleteUserRList(List<Roles> roles,int uid){
		StringBuffer hql = new StringBuffer("from UserRoles as ur where 1=1 and ur.userId="+uid+" and roleId=");
		for(Iterator<Roles> iterOld = roles.iterator();iterOld.hasNext();){
			hql.append(iterOld.next().getId());
			List<UserRoles> uRoels = (List<UserRoles>) userRolesDao.findByhql(hql.toString());
			if(OwnUtil.listisNotEmpty(uRoels)){
				userRolesDao.deleteByList(uRoels);
			}
		}
		
	}
	
}

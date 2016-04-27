package com.smarthome.simple.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseDaoImpl;
import com.smarthome.simple.dao.PermissionDao;
import com.smarthome.simple.entity.Permission;
import com.smarthome.simple.entity.PermissionRoles;
import com.smarthome.util.DateUtil;
import com.smarthome.util.HQLparam;
import com.smarthome.util.OwnUtil;

@Transactional
public class PermissionDaoImpl extends BaseDaoImpl<Permission>
  implements PermissionDao
{
	@Override
	public Set<String> hasPower(int uid) {
	    List permissonsList = new ArrayList();
	    StringBuffer hql = new StringBuffer("select new Permission( p.permission,  p.module) from  Permission as p,PermissionRoles as pr,UserRoles as ur where 1=1 and  ( p.id=pr.permissionId )");
	    hql = HQLparam.appendAnd("userId", hql, "ur");
	    org.hibernate.Query query =  createEQuery(hql.toString(), uid);
	    List permissions = query.list();	    
	    Set pers = new HashSet();//过滤重复权限 提高权限核对速度
	    if (!OwnUtil.objectIsEmpty(permissions))
	    {
	      Iterator iterator = permissions.iterator();
	      while (iterator.hasNext()) {
	        Permission permisson = (Permission)iterator.next();
	        StringBuffer permission_str = new StringBuffer(permisson.getModule());
	        permission_str.append("_");
	        permission_str.append(permisson.getPermission());
	        pers.add(permission_str.toString());
	      }
	    }
	    return pers;
	}

	@Override
	public List<Permission> getPermisson() {
	    StringBuffer hql = new StringBuffer("from Permission as p where 1=1 ");
		List list = findByhql(hql.toString());
	    return OwnUtil.listisNotEmpty(list)?(List<Permission>)list:null;
	}
	
	@Override
	public List<Permission> getSelectedPermisson(int roleId) {
		if(!OwnUtil.intIsZero(roleId)){
			List<Permission> permission = getPermisson();
			StringBuffer hql = new StringBuffer("select new Permission(p.id,  p.permissionName, p.module) from PermissionRoles as pu ,Permission as p where 1=1 and p.id=pu.permissionId and pu.roleId="+roleId);
			List<Permission> list = (List<Permission>)findByhql(hql.toString());
			if(OwnUtil.listisNotEmpty(permission)&&OwnUtil.listisNotEmpty(list)){
				for(Permission p :permission)
				{
					int pid = p.getId();
					for(Iterator<Permission> p2iterator = list.iterator();p2iterator.hasNext();){
						int pid2 = p2iterator.next().getId();
						if(pid==pid2){
							p.setSelected("1");
							p2iterator.remove();
						}
					}
				}
			}
			return permission;
		}else
		return null;
	}	

	@Override
	public void savePermission(int[] pid,int uid,int roleId) {
			if(pid!=null&&pid.length>0&&!OwnUtil.intIsZero(uid)&&!OwnUtil.intIsZero(roleId)){
				for(int i=0;i<pid.length;i++){
					PermissionRoles pr = new PermissionRoles();
					pr.setAdminId(uid);
					pr.setPermissionId(pid[i]);
					pr.setRecordTime(DateUtil.getCurrDateStr());
					pr.setRoleId(roleId);
					save(pr);
				}
			}
		
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void updatePermission(int[] pids, int uid, int roleId) {
		if(!OwnUtil.intIsZero(uid)&&!OwnUtil.intIsZero(roleId)){
			List<Permission> newPermissions = getPermission(pids);
			List<PermissionRoles> oldPermissions = getPerRoles(roleId);
	
			if(OwnUtil.listisNotEmpty(newPermissions)&&OwnUtil.listisNotEmpty(oldPermissions)){
				for(Iterator<PermissionRoles> piterator = oldPermissions.iterator();piterator.hasNext();)
				{
					int pid = piterator.next().getPermissionId();
					for(Iterator<Permission> p2iterator = newPermissions.iterator();p2iterator.hasNext();){
						int pid2 = p2iterator.next().getId();//permission表id
						if(pid==pid2){
							p2iterator.remove();
							piterator.remove();
						}
					}
				}
				
				if(OwnUtil.listisNotEmpty(oldPermissions)){
					deleteByList(oldPermissions);	
				}
				if(OwnUtil.listisNotEmpty(newPermissions)){
					savePerRoles(newPermissions, uid, roleId);
				}
			}else if(!OwnUtil.listisNotEmpty(newPermissions)){//清空权限
				if(OwnUtil.listisNotEmpty(oldPermissions)){
					for(Iterator<PermissionRoles> piterator = oldPermissions.iterator();piterator.hasNext();){
						delete(piterator.next());
					}
				}
			}else if(OwnUtil.listisNotEmpty(newPermissions)&&!OwnUtil.listisNotEmpty(oldPermissions)){
				savePerRoles(newPermissions, uid, roleId);
			}				
			
		}	
	}
	

	
	/**
	 * 根据pid roleid  获取相应 PermissionRoles 表中的数据
	 * @param roleId
	 * @param pid
	 * 	操作
	 * @return
	 */
	@Transactional(readOnly=true)	
	public List<PermissionRoles> getPerRoles(int roleId){
		List<PermissionRoles> list = new ArrayList<PermissionRoles>();
		StringBuffer hql = new StringBuffer("from PermissionRoles where 1=1 and roleId='"+roleId+"'"); 		
		return (List<PermissionRoles>) findByhql(hql.toString());
	}
	
	/**
	 * 根据pid 获取Permission表中的数据
	 * @param pid
	 * @return
	 */
	@Transactional(readOnly=true)	
	public List<Permission> getPermission(int[] pid){
		if(pid!=null&&pid.length>0){
			StringBuffer hql = new StringBuffer("from Permission where 1=1 and id=?"); 
			List<Permission> list = new ArrayList<Permission>();
			for(int index =0;index<pid.length;index++){
				Query query = getSession().createQuery(hql.toString());
				query.setParameter(0, pid[index]);
				List lt  = query.list();
				Permission per = (Permission) lt.get(0);
				list.add(per);
			}
			return list;
		}else
			return null;
	}

	/**
	 *  保存 PermissionRoles表数据
	 * @param list
	 * @param uid
	 * @param roleId
	 */
	public void savePerRoles(List<Permission> list,int uid,int roleId){
		for(Iterator<Permission> piterator = list.iterator();piterator.hasNext();){
			PermissionRoles pr = new PermissionRoles();
			pr.setAdminId(uid);
			pr.setPermissionId(piterator.next().getId());
			pr.setRecordTime(DateUtil.getCurrDateStr());
			pr.setRoleId(roleId);
			save(pr);						
		}	
	}
	
	
	@Override
	public void deletePermissionRoles(int rolesId) {
		List list = getPerRoles(rolesId);
		if(OwnUtil.listisNotEmpty(list)){
			deleteByList(list);
		}
	}

	
	

}
package com.smarthome.simple.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.smarthome.base.Authority;
import com.smarthome.base.BaseAction;
import com.smarthome.simple.entity.Roles;
import com.smarthome.simple.query.RolesQuery;
import com.smarthome.simple.services.PermissionServices;
import com.smarthome.simple.services.RolesServices;
import com.smarthome.util.Constans;
import com.smarthome.util.JSONSerializer;
import com.smarthome.util.OwnUtil;

public class RolesAction extends BaseAction<Roles, RolesQuery> {
	@Resource
	RolesServices rolesServices;
	@Resource
	PermissionServices permissionServices;
	
	/**
	 * 查询角色列表
	 * @return
	 */
	@Authority(privilege="roleList",module ="role")														
	public String getRoles(){
	    page=rolesServices.getCurrentPage(query,this.pageNum,this.numPerPage);  
        createPage(page);
        if(!OwnUtil.objectIsEmpty(page)&&page.size()>0&&page.getPageNum()>page.getTotalPage())
        	return "direct_getRoles";
		return "getRoles";
	}
	
	/**
	 * 获取权限列表
	 */
	public void getPermisson(){
		try {
			List permissions = permissionServices.getPermisson();
			getResponse().getWriter().write(JSONSerializer.serialize(permissions).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 保存角色
	 */
	public void saveRoles(){
		try {
			query.setUid(getUserId());
			String msg = rolesServices.saveRoles(query,model);
			getResponse().getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * 获取更新角色的权限列表
	 */
	public void getUpdatePermisson(){
		try {
			List permissions = permissionServices.getUpdatePermisson(query.getId());
			getResponse().getWriter().write(JSONSerializer.serialize(permissions).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取更新role的基本信息
	 */
	public void getUpdateRoles(){
		try {
			model = rolesServices.get(query.getId());
			getResponse().getWriter().write(JSONSerializer.serialize(model).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * 更新权限信息
	 */
	public void updateRoles(){
		try {
			
			if(!OwnUtil.objectIsEmpty(model)){
				query.setUid(getUserId());
				String msg = rolesServices.updateRoles(query, model);
				getResponse().getWriter().write(msg);				
			}else{
				getResponse().getWriter().write(Constans.RETRUN_FAILED_STATUS);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	public void deleteRoles(){
		try {
				if(!OwnUtil.objectIsEmpty(query.getId())){
					String msg = rolesServices.deletRoles(query);
					getResponse().getWriter().write(msg);				
				}else{
					getResponse().getWriter().write(Constans.RETRUN_FAILED_STATUS);				
				}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	
	
}

package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class RolesQuery extends Query {
	private String roleName;//roles名称
	private int[] permission;
	private int userId;//用户id
	private int[] rids;//传递权限id
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int[] getPermission() {
		return permission;
	}

	public void setPermission(int[] permission) {
		this.permission = permission;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int[] getRids() {
		return rids;
	}

	public void setRids(int[] rids) {
		this.rids = rids;
	}



}

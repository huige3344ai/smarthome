package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class RolesQuery extends Query {
	private String roleName;//roles名称
	private int[] permission;
	

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

}

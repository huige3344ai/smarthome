package com.smarthome.simple.entity;

/**
 * PermissionRoles entity. @author MyEclipse Persistence Tools
 */

public class PermissionRoles implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private Integer permissionId;
	private String recordTime;
	private String exchangeTime;
	private Integer adminId;

	// Constructors

	/** default constructor */
	public PermissionRoles() {
	}

	/** minimal constructor */
	public PermissionRoles(Integer roleId, Integer permissionId, Integer adminId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
		this.adminId = adminId;
	}

	/** full constructor */
	public PermissionRoles(Integer roleId, Integer permissionId,
			String recordTime, String exchangeTime, Integer adminId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
		this.recordTime = recordTime;
		this.exchangeTime = exchangeTime;
		this.adminId = adminId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getExchangeTime() {
		return this.exchangeTime;
	}

	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
package com.smarthome.simple.entity;

import java.util.Set;

/**
 * UserRoles entity. @author MyEclipse Persistence Tools
 */

public class UserRoles implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer roleId;
	private String recordTime;
	private String exchangeTime;
	private Integer adminId;

	// Constructors

	/** default constructor */
	public UserRoles() {
	}

	/** minimal constructor */
	public UserRoles(Integer userId, Integer roleId, Integer adminId) {
		this.userId = userId;
		this.roleId = roleId;
		this.adminId = adminId;
	}

	/** full constructor */
	public UserRoles(Integer userId, Integer roleId, String recordTime,
			String exchangeTime, Integer adminId) {
		this.userId = userId;
		this.roleId = roleId;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
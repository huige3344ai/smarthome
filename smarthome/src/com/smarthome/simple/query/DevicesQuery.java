package com.smarthome.simple.query;

import com.smarthome.base.Query;

/**
 * 设备 查询集
 * @author Hy
 *
 */
public class DevicesQuery extends Query {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String deviceName;
	private String address;//详细地址
	private String status;//状态
	
	
	private int homeId;
	
	public String getUserName() {
		return userName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}

package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class HomeQuery extends Query {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String country;
	private String address;
	
	
	public String getUserName() {
		return userName;
	}
	public String getCountry() {
		return country;
	}
	public String getAddress() {
		return address;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}

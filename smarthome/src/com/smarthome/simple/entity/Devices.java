package com.smarthome.simple.entity;

import java.io.Serializable;

public class Devices
  implements Serializable
{
  private Integer id;
  private String deviceNum;
  private String deviceName;
  private String status;
  private Integer homeId;
  private String category;
  private Integer userId;
  private String recordTime;
  private String exchangeTime;
  
  
  
  /*
   * 自定义显示属性 用于多表查询结果显示
   */
  private String userName;
  private String address;//详细地址


  public Devices()
  {
  }

  
  //自定义 Devices(id, deviceName, userName, address, status, category,recordTime)
  public Devices(Integer id, String deviceName,String userName,String address, String status,String category,String recordTime){
	  this.id=id;
	  this.deviceName=deviceName;
	  this.userName=userName;
	  this.address=address;
	  this.status=status;
	  this.category=category;
	  this.recordTime=recordTime;
  }
  
  //自定义 Devices(id, status,deviceName)
  public Devices(Integer id,String status,String deviceName){
	   this.id=id;
	   this.status=status;
	   this.deviceName=deviceName;
  }
  
  public Devices(Integer homeId, Integer userId)
  {
    this.homeId = homeId;
    this.userId = userId;
  }

  public Devices(String deviceNum, String deviceName, String status, Integer homeId, String category, Integer userId, String recordTime, String exchangeTime)
  {
    this.deviceNum = deviceNum;
    this.deviceName = deviceName;
    this.status = status;
    this.homeId = homeId;
    this.category = category;
    this.userId = userId;
    this.recordTime = recordTime;
    this.exchangeTime = exchangeTime;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeviceNum() {
    return this.deviceNum;
  }

  public void setDeviceNum(String deviceNum) {
    this.deviceNum = deviceNum;
  }

  public String getDeviceName() {
    return this.deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getHomeId() {
    return this.homeId;
  }

  public void setHomeId(Integer homeId) {
    this.homeId = homeId;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}
}
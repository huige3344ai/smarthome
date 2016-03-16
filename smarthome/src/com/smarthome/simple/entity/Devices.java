package com.smarthome.simple.entity;

import java.io.Serializable;

public class Devices
  implements Serializable
{
  private Integer id;
  private Integer deviceNum;
  private String deviceName;
  private String status;
  private Integer homeId;
  private String category;
  private Integer userId;
  private String recordTime;
  private String exchangeTime;

  public Devices()
  {
  }

  public Devices(Integer homeId, Integer userId)
  {
    this.homeId = homeId;
    this.userId = userId;
  }

  public Devices(Integer deviceNum, String deviceName, String status, Integer homeId, String category, Integer userId, String recordTime, String exchangeTime)
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

  public Integer getDeviceNum() {
    return this.deviceNum;
  }

  public void setDeviceNum(Integer deviceNum) {
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
}
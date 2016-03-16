package com.smarthome.simple.entity;

import java.io.Serializable;

public class Home
  implements Serializable
{
  private Integer id;
  private String country;
  private String address;
  private Integer ownerId;
  private Integer deviceNum;
  private String temperature;
  private String humidity;
  private String recordTime;
  private String exchangeTime;
  private Integer usreId;
  private String logoImage;

  public Home()
  {
  }

  public Home(Integer ownerId)
  {
    this.ownerId = ownerId;
  }

  public Home(String country, String address, Integer ownerId, Integer deviceNum, String temperature, String humidity, String recordTime, String exchangeTime, Integer usreId, String logoImage)
  {
    this.country = country;
    this.address = address;
    this.ownerId = ownerId;
    this.deviceNum = deviceNum;
    this.temperature = temperature;
    this.humidity = humidity;
    this.recordTime = recordTime;
    this.exchangeTime = exchangeTime;
    this.usreId = usreId;
    this.logoImage = logoImage;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getOwnerId() {
    return this.ownerId;
  }

  public void setOwnerId(Integer ownerId) {
    this.ownerId = ownerId;
  }

  public Integer getDeviceNum() {
    return this.deviceNum;
  }

  public void setDeviceNum(Integer deviceNum) {
    this.deviceNum = deviceNum;
  }

  public String getTemperature() {
    return this.temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public String getHumidity() {
    return this.humidity;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
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

  public Integer getUsreId() {
    return this.usreId;
  }

  public void setUsreId(Integer usreId) {
    this.usreId = usreId;
  }

  public String getLogoImage() {
    return this.logoImage;
  }

  public void setLogoImage(String logoImage) {
    this.logoImage = logoImage;
  }
}
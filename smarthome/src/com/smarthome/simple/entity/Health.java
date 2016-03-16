package com.smarthome.simple.entity;

import java.io.Serializable;

public class Health
  implements Serializable
{
  private Integer id;
  private Integer userId;
  private Short health;
  private String recommend;
  private String recordTime;
  private String exchangeTime;

  public Health()
  {
  }

  public Health(Integer userId)
  {
    this.userId = userId;
  }

  public Health(Integer userId, Short health, String recommend, String recordTime, String exchangeTime)
  {
    this.userId = userId;
    this.health = health;
    this.recommend = recommend;
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

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Short getHealth() {
    return this.health;
  }

  public void setHealth(Short health) {
    this.health = health;
  }

  public String getRecommend() {
    return this.recommend;
  }

  public void setRecommend(String recommend) {
    this.recommend = recommend;
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
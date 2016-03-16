package com.smarthome.simple.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Memoradum
  implements Serializable
{
  private Integer id;
  private String theme;
  private Timestamp startTime;
  private Timestamp endTime;
  private String contents;
  private Integer num;
  private String recordTime;
  private String exchangeTime;
  private Integer userId;

  public Memoradum()
  {
  }

  public Memoradum(String theme, Timestamp startTime, Timestamp endTime, String recordTime, Integer userId)
  {
    this.theme = theme;
    this.startTime = startTime;
    this.endTime = endTime;
    this.recordTime = recordTime;
    this.userId = userId;
  }

  public Memoradum(String theme, Timestamp startTime, Timestamp endTime, String contents, Integer num, String recordTime, String exchangeTime, Integer userId)
  {
    this.theme = theme;
    this.startTime = startTime;
    this.endTime = endTime;
    this.contents = contents;
    this.num = num;
    this.recordTime = recordTime;
    this.exchangeTime = exchangeTime;
    this.userId = userId;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTheme() {
    return this.theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }

  public Timestamp getStartTime() {
    return this.startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return this.endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public String getContents() {
    return this.contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public Integer getNum() {
    return this.num;
  }

  public void setNum(Integer num) {
    this.num = num;
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

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
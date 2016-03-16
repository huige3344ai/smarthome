package com.smarthome.simple.entity;

import java.io.Serializable;

public class ResetPwd
  implements Serializable
{
  private Integer id;
  private Integer uid;
  private String emailVer;
  private String recordTime;
  private Short status;

  public ResetPwd()
  {
  }

  public ResetPwd(Integer uid, String emailVer, Short status)
  {
    this.uid = uid;
    this.emailVer = emailVer;
    this.status = status;
  }

  public ResetPwd(Integer uid, String emailVer, String recordTime, Short status)
  {
    this.uid = uid;
    this.emailVer = emailVer;
    this.recordTime = recordTime;
    this.status = status;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUid() {
    return this.uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getEmailVer() {
    return this.emailVer;
  }

  public void setEmailVer(String emailVer) {
    this.emailVer = emailVer;
  }

  public String getRecordTime() {
    return this.recordTime;
  }

  public void setRecordTime(String recordTime) {
    this.recordTime = recordTime;
  }

  public Short getStatus() {
    return this.status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }
}
package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class UserQuery extends Query
{
  private static final long serialVersionUID = 1L;
  private String userName;
  private String pwd;
  private String phone;
  private String sessionid;
  private String autologin;

  public String getUserName()
  {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPwd() {
    return this.pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  public String getPhone() {
    return this.phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getAutologin() {
    return this.autologin;
  }
  public void setAutologin(String autologin) {
    this.autologin = autologin;
  }
  public String getSessionid() {
    return this.sessionid;
  }
  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }
}
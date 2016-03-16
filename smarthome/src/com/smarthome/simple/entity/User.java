package com.smarthome.simple.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User
  implements Serializable
{
  private Integer id;
  private String userName;
  private String pwd;
  private String loginTime;
  private Short status;
  private String registerTime;
  private String email;
  private Timestamp birthday;
  private Integer age;
  private Short period;
  private Timestamp exchangeTime;
  private Integer adminId;
  private String preloginTime;
  private String logoImage;
  private String phone;
  private String sessionId;
  private Short isAdmin;

  public User()
  {
  }

  public User(Short status)
  {
    this.status = status;
  }

  public User(String userName, String pwd, Short status)
  {
    this.userName = userName;
    this.pwd = pwd;
    this.status = status;
  }

  public User(String userName, String pwd, String loginTime, Short status, String registerTime, String email, Timestamp birthday, Integer age, Short period, Timestamp exchangeTime, Integer adminId, String preloginTime, String logoImage, String phone, String sessionId, Short isAdmin)
  {
    this.userName = userName;
    this.pwd = pwd;
    this.loginTime = loginTime;
    this.status = status;
    this.registerTime = registerTime;
    this.email = email;
    this.birthday = birthday;
    this.age = age;
    this.period = period;
    this.exchangeTime = exchangeTime;
    this.adminId = adminId;
    this.preloginTime = preloginTime;
    this.logoImage = logoImage;
    this.phone = phone;
    this.sessionId = sessionId;
    this.isAdmin = isAdmin;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
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

  public String getLoginTime() {
    return this.loginTime;
  }

  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }

  public Short getStatus() {
    return this.status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }

  public String getRegisterTime() {
    return this.registerTime;
  }

  public void setRegisterTime(String registerTime) {
    this.registerTime = registerTime;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Timestamp getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Timestamp birthday) {
    this.birthday = birthday;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Short getPeriod() {
    return this.period;
  }

  public void setPeriod(Short period) {
    this.period = period;
  }

  public Timestamp getExchangeTime() {
    return this.exchangeTime;
  }

  public void setExchangeTime(Timestamp exchangeTime) {
    this.exchangeTime = exchangeTime;
  }

  public Integer getAdminId() {
    return this.adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }

  public String getPreloginTime() {
    return this.preloginTime;
  }

  public void setPreloginTime(String preloginTime) {
    this.preloginTime = preloginTime;
  }

  public String getLogoImage() {
    return this.logoImage;
  }

  public void setLogoImage(String logoImage) {
    this.logoImage = logoImage;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSessionId() {
    return this.sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Short getIsAdmin() {
    return this.isAdmin;
  }

  public void setIsAdmin(Short isAdmin) {
    this.isAdmin = isAdmin;
  }
}
package com.smarthome.simple.entity;

import java.io.Serializable;
import java.util.Set;

public class Roles
  implements Serializable
{
  private Integer id;
  private String roleName;
  private String role;
  private String recordTime;
  private String exchangeTime;
  private Integer userId;
  
  private String selected;//是否需要选择

  
  public Roles()
  {
  }

  public Roles(String roleName, String role, String recordTime, Integer userId)
  {
    this.roleName = roleName;
    this.role = role;
    this.recordTime = recordTime;
    this.userId = userId;
  }

  public Roles(String roleName, String role, String recordTime, String exchangeTime, Integer userId)
  {
    this.roleName = roleName;
    this.role = role;
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

  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
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

public String getSelected() {
	return selected;
}

public void setSelected(String selected) {
	this.selected = selected;
}

}
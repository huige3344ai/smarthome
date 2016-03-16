package com.smarthome.simple.entity;

import java.io.Serializable;

public class PermissionUser
  implements Serializable
{
  private Integer id;
  private Integer userId;
  private Integer roleId;
  private Integer permissionId;
  private String recordTime;
  private String exchangeTime;
  private Integer adminId;

  public PermissionUser()
  {
  }

  public PermissionUser(Integer userId, Integer roleId, Integer permissionId)
  {
    this.userId = userId;
    this.roleId = roleId;
    this.permissionId = permissionId;
  }

  public PermissionUser(Integer userId, Integer roleId, Integer permissionId, String recordTime, String exchangeTime, Integer adminId)
  {
    this.userId = userId;
    this.roleId = roleId;
    this.permissionId = permissionId;
    this.recordTime = recordTime;
    this.exchangeTime = exchangeTime;
    this.adminId = adminId;
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

  public Integer getRoleId() {
    return this.roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public Integer getPermissionId() {
    return this.permissionId;
  }

  public void setPermissionId(Integer permissionId) {
    this.permissionId = permissionId;
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

  public Integer getAdminId() {
    return this.adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }
}
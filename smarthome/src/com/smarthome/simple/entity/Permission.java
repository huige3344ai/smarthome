package com.smarthome.simple.entity;

import java.io.Serializable;

public class Permission
  implements Serializable
{
  private Integer id;
  private String permission;
  private String permissionName;
  private String module;
  private String recordTime;
  private String exchangeTime;
  private Integer userId;

  public Permission()
  {
  }

  public Permission(String permission, String permissionName)
  {
    this.permission = permission;
    this.permissionName = permissionName;
  }

  public Permission(String permission, String permissionName, String module, String recordTime, String exchangeTime, Integer userId)
  {
    this.permission = permission;
    this.permissionName = permissionName;
    this.module = module;
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

  public String getPermission() {
    return this.permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public String getPermissionName() {
    return this.permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }

  public String getModule() {
    return this.module;
  }

  public void setModule(String module) {
    this.module = module;
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
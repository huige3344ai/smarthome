package com.smarthome.simple.services;

import com.smarthome.simple.query.UserQuery;

public abstract interface UserServices
{
  public abstract void login(UserQuery paramUserQuery)
    throws ServiceException;

  public abstract String isAutoLogin(UserQuery paramUserQuery);
}
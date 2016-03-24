package com.smarthome.simple.services;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;

public abstract interface UserServices extends BaseService<User, UserQuery>
{
	/**
	 * 登陆操作
	 * @param paramUserQuery
	 * @throws ServiceException
	 */
  public  String login(UserQuery paramUserQuery)
    throws ServiceException;

  /**
   * 是否自动登陆
   * @param paramUserQuery
   * @return
   */
  public abstract String isAutoLogin(UserQuery paramUserQuery);
  
  /**
   * 退出登录
   * @param query
   */
  public boolean loginOut(User  user) throws ServiceException;
  
  /**
   * 通过用户明和sessionId查询用户
   * @param query
   * @return
   */
  public User findBySessionUserName(UserQuery query);
  
  
}
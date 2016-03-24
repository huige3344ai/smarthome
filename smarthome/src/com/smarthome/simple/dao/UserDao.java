package com.smarthome.simple.dao;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import java.util.List;

public abstract interface UserDao extends BaseDao<User>
{
  public abstract String getAutoLoginStauts(UserQuery paramUserQuery);

  public abstract List<String> hasPower(int paramInt);
  
  public User findBySessionUserName(UserQuery paramUserQuery);
}

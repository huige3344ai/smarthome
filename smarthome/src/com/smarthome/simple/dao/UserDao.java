package com.smarthome.simple.dao;

import java.util.List;

import com.smarthome.base.BaseDao;
import com.smarthome.simple.entity.TipNews;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.util.Page;

public abstract interface UserDao extends BaseDao<User>
{
  public abstract String getAutoLoginStauts(UserQuery paramUserQuery);

  public User findBySessionUserName(UserQuery paramUserQuery);
  
  /**
   * 获取所有用户 分页查询
   * @param query
   * @param offset
   * @param length
   * @return
   */
  public Page getUserPage(UserQuery query, Integer offset, Integer length);
  
  
  /**
   * 获取 tip 即左菜单上面的 new 提示
   * @return
   */
  public TipNews getTipNews();
  
}

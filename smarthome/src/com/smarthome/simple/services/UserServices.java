package com.smarthome.simple.services;

import java.util.List;

import com.smarthome.base.BaseService;
import com.smarthome.simple.entity.ResetPwd;
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
  
  
  /**
   * 通过手机号码或者用户名或者 邮箱进行查询是否存在相应的用户
   * @param user
   * @return
   */
  public User findByPhoneAndUserName(User user);
 
  /**
   * 通过邮箱进行查询是否存在用户
   * @param email
   * @return
   */
  public User findByEmail(String email);
  
  /**
   * 保存找回密码表
   * @param rest
   * @return
   */
  public void saveResetPwd(ResetPwd reset);
  
  
  /**
   * 通过 密码找回表进行信息核对 并进行返回
   * @param query
   * 
   * @return 
   * {-1:记录不存在 0:超过有效期 1:验证通过}
   */
  public int findByEmailAndValidate(UserQuery  query);
  
  
  /**
   * 找回密码之设置密码
   * @param query
   */
  public int updatePwd(UserQuery  query);
  
  
  /**
   * 查询 返回 用户 id和用户名的信息 
   * @return
   */
  public List<User> findAll();
  
  
}
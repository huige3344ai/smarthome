package com.smarthome.simple.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.DateUtil;
import com.smarthome.util.MD5Util;

@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, UserQuery>
  implements UserServices
{

  @Resource
  UserDao baseDao;

  @Transactional(rollbackFor={ServiceException.class})
  public void login(UserQuery query)
    throws ServiceException
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    String userName = query.getUserName();
    String hql = "select status from User as u where userName =  '" + userName + "' or phone ='" + userName + "'";
    List user_status = this.baseDao.findByhql(hql);
    if ((user_status != null) && (user_status.size() == 1))
    {
      if (((Short)user_status.get(0)).shortValue() == 1) {
        String password = MD5Util.encode2hex(query.getPwd());
        hql = "from User where (userName = '" + 
          userName + "' or phone = '" + userName + "') and pwd = '" + password + "'";
        List users = this.baseDao.findAllByhql(hql);
        if ((users != null) && (users.size() > 0)) {
          User user = (User)users.get(0);

          String usersessionid = "";
          if (query.getAutologin().equals("on"))
          {
            Cookie ckUsername = new Cookie("autoLogin", query.getUserName());
            ckUsername.setMaxAge(1209600);
            response.addCookie(ckUsername);

            usersessionid = request.getSession().getId();
            user.setSessionId(usersessionid);

            Cookie ckSessionid = new Cookie("sessionid", usersessionid);
            ckSessionid.setMaxAge(1209600);
            response.addCookie(ckSessionid);
          }

          Date date = new Date();
          String loginTime = DateUtil.dateToString(date);
          user.setLoginTime(loginTime);

          hql = "update from User set loginTime = '" + loginTime + "', sessionId ='" + 
            usersessionid + "' where (email = '" + userName + "' or phone = '" + userName + "') and pwd = '" + password + "'";
          this.baseDao.update(hql);
          request.getSession().setAttribute("user", user);
        }
        else {
          throw new ServiceException("用户名或者密码错误！");
        }
      } else {
        throw new ServiceException("用户名暂时无法登录，请联系客服~");
      }
    }
    else
      throw new ServiceException("用户名或者密码错误！");
  }

  @Transactional(rollbackFor={ServiceException.class})
  public String isAutoLogin(UserQuery query)
  {
    return this.baseDao.getAutoLoginStauts(query);
  }
}
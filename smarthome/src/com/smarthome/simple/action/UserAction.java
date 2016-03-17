package com.smarthome.simple.action;

import javax.annotation.Resource;

import com.smarthome.base.BaseAction;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.StringUtils;

public class UserAction extends BaseAction<User, UserQuery>
{
  private static final long serialVersionUID = 1L;

  @Resource
  protected UserServices userService;

  /**
   * 登陆
   * @return
   */
  public String login()
  {
    try
    {
      String returnStr = this.userService.login(getQuery());
      if(StringUtils.isEmpty(returnStr)){
    	  return LOGIN;
      }else
      return returnStr;
    } catch (ServiceException e) {
      this.request.setAttribute("message", e.getMessage());
      return LOGIN;
    }
  }
  
  /**
   * 注销登录
   * @return
   */
  public  String logout()
  {
	  User user =(User)request.getSession().getAttribute("user");
	  try {
		if(userService.loginOut(user)){
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
			return "logout_sucess";
		}else
		return INPUT;
	} catch (ServiceException e) {
		return INPUT;
	}

  }
}
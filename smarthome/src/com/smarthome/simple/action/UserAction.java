package com.smarthome.simple.action;

import com.smarthome.base.Authority;
import com.smarthome.base.BaseAction;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class UserAction extends BaseAction<User, UserQuery>
{
  private static final long serialVersionUID = 1L;

  @Resource
  protected UserServices userService;

  @Authority(roles="adminB", privilege="login")
  public String login()
  {
    try
    {
      this.userService.login((UserQuery)this.query);
      return "login_sucess";
    } catch (ServiceException e) {
      this.request.setAttribute("message", e.getMessage());
    }return "login_failed";
  }
}
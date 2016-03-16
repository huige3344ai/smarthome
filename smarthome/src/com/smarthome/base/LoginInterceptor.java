package com.smarthome.base;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.entity.User;
import com.smarthome.util.DateUtil;
import com.smarthome.util.OwnUtil;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginInterceptor extends AbstractInterceptor
{
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
  public static final String GOTO_URL_KEY = "GOING_TO";

  @Resource
  private UserDao userDao;

  public String intercept(ActionInvocation invocation)
    throws Exception
  {
    ActionContext acontext = invocation.getInvocationContext();
    HttpServletRequest request = ServletActionContext.getRequest();
    Map session = acontext.getSession();
    User user = (User)session.get("user");

    String methodName = invocation.getProxy().getMethod();
    Class clazz = invocation.getAction().getClass();
    Method currentMethod = clazz.getMethod(methodName, new Class[0]);
    if (OwnUtil.isAdmin(user))
      return invocation.invoke();
    if ((user != null) && (currentMethod.isAnnotationPresent(Authority.class)))
    {
      Authority authority = (Authority)currentMethod.getAnnotation(Authority.class);

      String privilege = authority.privilege();

      if (checkIsNotPower(privilege, user.getId().intValue())) {
        String time = DateUtil.dateToString(new Date());
        log.info("操作员：" + user.getUserName() + "操作了" + privilege + "执行时间：" + time);
        return invocation.invoke();
      }
      acontext.put("message", "你还没有相应的权限，请登录具有相应权限的用户之后再重试！！！");
      return "login";
    }

    acontext.put("message", "你还没有相应的权限，请登录具有相应权限的用户之后再重试！！！");
    return "login";
  }

  public void setUserDao(UserDao userDao)
  {
    this.userDao = userDao;
  }

  public boolean checkIsNotPower(String power, int rid)
  {
    boolean flag = false;
    if (this.userDao.hasPower(rid).size() > 0) {
      List powers = this.userDao.hasPower(rid);
      Iterator iterator = powers.iterator();
      while (iterator.hasNext()) {
        String po = (String)iterator.next();
        if ((power != null) && (po.trim().equals(power))) {
          flag = true;
          System.out.println("flag:" + flag);
          break;
        }
      }
    }
    return flag;
  }
}
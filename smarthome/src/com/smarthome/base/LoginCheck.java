package com.smarthome.base;

import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.CookieManager;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.SpringUtils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginCheck
  implements Filter
{
  private FilterConfig filterConfig;
  private static final Logger log = LoggerFactory.getLogger(LoginCheck.class);

  public String description() {
    return null;
  }

  public void init(FilterConfig config) throws ServletException {
    this.filterConfig = config;
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;
    HttpSession session = req.getSession(true);
    UserServices service = (UserServices)SpringUtils.getBeanById("userService");

    UserQuery query = new UserQuery();

    CookieManager cm = new CookieManager();

    User user = (User)session.getAttribute("user");
    if (OwnUtil.objectIsEmpty(user)) {
      user = new User();
    }

    if (OwnUtil.stringIsEmpty(user.getUserName())) {
      Cookie[] cookies = req.getCookies();
      query.setUserName(cm.getCookieValue(cookies, "autoLogin"));
      query.setSessionid(cm.getCookieValue(cookies, "sessionid"));
      query.setAutologin(service.isAutoLogin(query));
    }

    if (OwnUtil.stringIsEqual(query.getAutologin(), "on")) {
      user.setUserName(query.getUserName());
      user.setSessionId(query.getSessionid());
      session.setAttribute("user", user);
      chain.doFilter(request, response);
    } else if ((OwnUtil.objectIsEmpty(user)) || (OwnUtil.stringIsEmpty(user.getSessionId())) || (OwnUtil.stringIsEmpty(user.getUserName()))) {
      session.setAttribute("message", "你还没有登录，请登录之后再尝试");
      req.getRequestDispatcher("/page/login.jsp").forward(req, res);
    } else {
      chain.doFilter(request, response);
    }
  }

  public void destroy() {
    this.filterConfig = null;
  }
}
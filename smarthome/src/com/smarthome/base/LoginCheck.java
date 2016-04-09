package com.smarthome.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.simple.dao.RestrecordDao;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.CookieManager;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.SpringUtils;

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
    UserServices service = (UserServices)SpringUtils.getBeanById("userService");
    RestrecordDao restrecordDao = (RestrecordDao)SpringUtils.getBeanById("restrecordDao");
    StringBuffer URL  = new StringBuffer(req.getContextPath()); 
    URL.append(req.getServletPath());
    UserQuery query = new UserQuery();


    User user = (User)req.getSession().getAttribute("user");
    
    CookieManager cm = new CookieManager();
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
      user = service.findBySessionUserName(query);
      if(!OwnUtil.objectIsEmpty(user)){
          Restrecord rest=restrecordDao.findByUserId(user.getId());
          if(!OwnUtil.objectIsEmpty(rest)){
        	  user.setRecommendRestTime(rest.getRecommendRestTime());
        	  user.setRecommendWakeTime(rest.getRecommendWakeTime());
        	  req.getSession().setAttribute("user", user);
        	  service.getTipNews(1);
          }else if(OwnUtil.objectIsEmpty(rest)&&OwnUtil.isAdmin(user)){
        	  req.getSession().setAttribute("user", user);
        	  service.getTipNews(1);        	  
          }
          else{
        	  req.setAttribute("message", "自动登录失败，请登录之后再尝试!!!");
        	  req.getRequestDispatcher("/page/login.jsp").forward(req, res);       	  
          }  
    	  //chain.doFilter(request, response);  	  
    	  res.sendRedirect(URL.toString());
      }else{
    	  req.setAttribute("message", "自动登录失败，请登录之后再尝试!!!");
    	  req.getRequestDispatcher("/page/login.jsp").forward(req, res);
      }
    } else if ((OwnUtil.objectIsEmpty(user)) || (OwnUtil.stringIsEmpty(user.getUserName()))) {
    	 req.setAttribute("message", "你还没有登录，请登录之后再尝试");
      req.getRequestDispatcher("/page/login.jsp").forward(req, res);
    } else {    	
      chain.doFilter(request, response);
    }
  }

  public void destroy() {
    this.filterConfig = null;
  }
  
}
package com.smarthome.base;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smarthome.simple.dao.RestrecordDao;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.PermissionServices;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.CookieManager;
import com.smarthome.util.DateUtil;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.SpringUtils;

public class LoginInterceptor extends AbstractInterceptor
{
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
  public static final String GOTO_URL_KEY = "GOING_TO";



  public String intercept(ActionInvocation invocation)
    throws Exception
  {
    ActionContext acontext = invocation.getInvocationContext();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    UserServices service = (UserServices)SpringUtils.getBeanById("userService");
    RestrecordDao restrecordDao = (RestrecordDao)SpringUtils.getBeanById("restrecordDao");
   
    User user = (User)request.getSession().getAttribute("user");
    String methodName = invocation.getProxy().getMethod();
    Class clazz = invocation.getAction().getClass();
    Method currentMethod = clazz.getMethod(methodName, new Class[0]);
    if (OwnUtil.isAdmin(user)){
    	return invocation.invoke();
    }else if ((user != null) ){
     
    	if((currentMethod.isAnnotationPresent(Authority.class))){//是否有注解的
    		
    		Authority authority = (Authority)currentMethod.getAnnotation(Authority.class);
    		
    		String privilege = authority.privilege();
    		String moudel = authority.module();
    		StringBuffer permission = new StringBuffer(moudel);
    		permission.append("_");
    		permission.append(privilege);
    		if (checkIsNotPower(permission.toString(), user.getId().intValue())) {
    			String time = DateUtil.dateToString(new Date());
    			log.info("操作员：" + user.getUserName() + "操作了" + privilege + "执行时间：" + time);
    			return invocation.invoke();
    		}else if(preHandle(request,response,"nopermisson")){//action直接访问非 ajax
    			return null;
    		}else{
    			request.setAttribute("message", "你还没有相应的权限，请登录具有相应权限的用户之后再重试！！！");
    	    	return Action.LOGIN;
    		}
    		
    	}else{
        	return invocation.invoke();
    	}
    	
    	
    }else{
    	
        CookieManager cm = new CookieManager();
        if (OwnUtil.objectIsEmpty(user)) {
          user = new User();
        }
        UserQuery query = new UserQuery();
        StringBuffer URL  = new StringBuffer(request.getContextPath()); 
        URL.append(request.getServletPath());        
        if (OwnUtil.stringIsEmpty(user.getUserName())) {
          Cookie[] cookies = request.getCookies();
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
              	  request.getSession().setAttribute("user", user);
            	  service.getTipNews(1);
                }else if(OwnUtil.objectIsEmpty(rest)&&OwnUtil.isAdmin(user)){
                  request.getSession().setAttribute("user", user);
            	  service.getTipNews(1);        	  
                }else{
                	return Action.LOGIN;
                }       
                
              return invocation.invoke();
            }else
    	    	return Action.LOGIN;
        }else{
        	if(preHandle(request,response,"timeout")){//ajax 响应头
        		return null;
        	}else{//action直接访问非 ajax
        		request.setAttribute("message", "你还没有登录呢~");
        		return Action.LOGIN;
        	}
        }    
    }

  }


	/**
	 * 获取 用户权限表 和 访问当前action进行判断
	 * @param power
	 * @param uid
	 * @return
	 */
  public boolean checkIsNotPower(String power, int uid)
  {
	PermissionServices services = (PermissionServices)SpringUtils.getBeanById("permissionServices");
    boolean flag = false;
    Set powers = services.getPermission(uid);
    if (OwnUtil.listisNotEmpty(powers)) {  	
      Iterator iterator = powers.iterator();
      while (iterator.hasNext()) {
        String po = (String)iterator.next();
        if ((power != null) && (po.trim().equals(power))) {
          flag = true;
          break;
        }
      }
    }
    return flag;
  }
  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, String handler) throws Exception
  {
      if (request.getHeader("x-requested-with") != null
              && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))//如果是ajax请求响应头会有，x-requested-with；
      {
    	  response.setHeader("sessionstatus", handler);
          return true;
      }else
    	  return false;
  }

  
}
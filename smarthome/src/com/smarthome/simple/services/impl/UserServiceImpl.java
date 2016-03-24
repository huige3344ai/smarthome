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
import com.smarthome.base.Query;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.CookieManager;
import com.smarthome.util.DateUtil;
import com.smarthome.util.MD5Util;
import com.smarthome.util.OwnUtil;

@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, UserQuery>
  implements UserServices
{

	
  @Resource
  UserDao baseDao;

  @Transactional(rollbackFor={ServiceException.class})
  public String login(UserQuery query)
    throws ServiceException
  {
	  
    String userName = query.getUserName();
    String hql = "select status from User as u where userName =  '" + userName + "' or phone ='" + userName + "'";
    List user_status = this.baseDao.findByhql(hql);
    if ((user_status != null) && (user_status.size() == 1))
    {
      if (((Short)user_status.get(0)).shortValue() == 1) {
        String password = MD5Util.encode2hex(query.getPwd());
        hql = "from User where (userName = '" + 
          userName + "' or phone = '" + userName + "') and pwd = '" + password + "'";
        List users = this.baseDao.findByhql(hql);
        if ((users != null) && (users.size() > 0)) {
          User user = (User)users.get(0);

          boolean f = OwnUtil.objectIsEqual(query.getIsAdmin(), user.getIsAdmin());
          if(query.getIsAdmin()!=null&&query.getIsAdmin()==1&&OwnUtil.objectIsEqual(query.getIsAdmin(), user.getIsAdmin())){    	  
        	  returnStr="login_admin_sucess";
          }else if(query.getIsAdmin()!=null&&query.getIsAdmin()==1&&query.getIsAdmin()!=user.getIsAdmin()){
        	  throw new ServiceException("非管理员用户无法登录后台的呢！");
          }else
        	  returnStr="login_deault_sucess";         

          String usersessionid = "";

          Date date = new Date();
          String loginTime = DateUtil.dateToString(date);
          user.setLoginTime(loginTime);


          
          if (OwnUtil.stringIsEqual(query.getAutologin(), "on"))
          {
            hql = "update from User set loginTime = '" + loginTime + "', sessionId ='" + 
                      usersessionid + "' where (email = '" + userName + "' or phone = '" + userName + "') and pwd = '" + password + "'";
            this.baseDao.update(hql);      	  
            Cookie ckUsername = new Cookie("autoLogin", query.getUserName());
            ckUsername.setMaxAge(1209600);
            getResponse().addCookie(ckUsername);

            usersessionid = getRequest().getSession().getId();
            user.setSessionId(usersessionid);

            Cookie ckSessionid = new Cookie("sessionid", usersessionid);
            ckSessionid.setMaxAge(1209600);
            getResponse().addCookie(ckSessionid);
          } else{
              hql = "update from User set loginTime = '" + loginTime + "' where (email = '" + userName + "' or phone = '" + userName + "') and pwd = '" + password + "'";
              this.baseDao.update(hql);    	  
          }       
          getRequest().getSession().setAttribute("user", user);
        }
        else {
          throw new ServiceException("用户名或者密码错误！");
        }
      } else {
        throw new ServiceException("用户名暂时无法登录，请联系客服~");
      }
    }else{
    	throw new ServiceException("用户名或者密码错误！");
    }
    
    return returnStr;
  }

  @Transactional(rollbackFor={ServiceException.class})
  public String isAutoLogin(UserQuery query)
  {
    return this.baseDao.getAutoLoginStauts(query);
  }
  
  
	@Transactional(rollbackFor={ServiceException.class})
	@Override
	public boolean loginOut(User user)  throws ServiceException{

		if(!OwnUtil.objectIsEmpty(user)){
			String hql = "update from User set sessionId ='' where (email = '" + user.getUserName() + "' or phone = '" + user.getPhone() + "') and pwd = '" + user.getPwd() + "'";
			this.baseDao.update(hql);	
			
			CookieManager cm = new CookieManager(); 			 
			Cookie[] cookies = getRequest().getCookies();
			//清空cookie
			if(OwnUtil.stringIsEqual(cm.getCookieValue(cookies, "autoLogin"), user.getUserName())){
				Cookie cksessionID =cm.findCookieByName(cookies, "sessionid");
				Cookie ckUsername = cm.findCookieByName(cookies, "autoLogin");
				cksessionID.setMaxAge(0);
				ckUsername.setMaxAge(0);
				getResponse().addCookie(cksessionID);
				getResponse().addCookie(ckUsername);
			}
			 
			return true;
		}else
		return false;		
	}

	@Override
	public User findBySessionUserName(UserQuery query) {
		return baseDao.findBySessionUserName(query);
	}
}
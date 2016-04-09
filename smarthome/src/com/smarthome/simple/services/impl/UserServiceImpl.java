package com.smarthome.simple.services.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.springframework.transaction.annotation.Transactional;

import com.smarthome.base.BaseServiceImpl;
import com.smarthome.simple.dao.DevicesDao;
import com.smarthome.simple.dao.HomeDao;
import com.smarthome.simple.dao.ResetPwdDao;
import com.smarthome.simple.dao.RestrecordDao;
import com.smarthome.simple.dao.UserDao;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.entity.ResetPwd;
import com.smarthome.simple.entity.Restrecord;
import com.smarthome.simple.entity.TipNews;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.CookieManager;
import com.smarthome.util.DateUtil;
import com.smarthome.util.MD5Util;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, UserQuery>
  implements UserServices
{

	
  @Resource
  UserDao baseDao;
  @Resource
  ResetPwdDao resetPwdDao;
  @Resource
  DevicesDao deviesDao;
  @Resource
  HomeDao homeDao;
  @Resource
  RestrecordDao restrecordDao;

  
  @Transactional(rollbackFor={ServiceException.class})
  public String login(UserQuery query)
    throws ServiceException
  {
	  
    String userName = query.getUserName();
    String hql = "select status from User as u where userName =  '" + userName + "' or phone ='" + userName + "' or email ='" + userName + "'";
    List user_status = this.baseDao.findByhql(hql);
    if ((user_status != null) && (user_status.size() == 1))
    {
      if (((Short)user_status.get(0)).shortValue() == 1) {
        String password = MD5Util.encode2hex(query.getPwd());
        hql = "from User where (userName = '" +userName + "' or phone = '" + userName + "' or email ='" + userName + "') and pwd = '" + password + "'";
        List users = this.baseDao.findByhql(hql);
        if ((users != null) && (users.size() > 0)) {
          User user = (User)users.get(0);

         // boolean f = OwnUtil.objectIsEqual(query.getIsAdmin(), user.getIsAdmin());
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
                      usersessionid + "' where (userName = '" +userName + "' or phone = '" + userName + "' or email ='" + userName + "') and pwd = '" + password + "'";
            this.baseDao.update(hql);      	  
            Cookie ckUsername;
			try {
				ckUsername = new Cookie("autoLogin", URLEncoder.encode(query.getUserName(),"UTF-8"));
				ckUsername.setMaxAge(1209600);
				getResponse().addCookie(ckUsername);
				
				usersessionid = getRequest().getSession().getId();
				user.setSessionId(usersessionid);
				
				Cookie ckSessionid = new Cookie("sessionid", URLEncoder.encode(usersessionid,"UTF-8"));
				ckSessionid.setMaxAge(1209600);
				getResponse().addCookie(ckSessionid);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

          } else{
              hql = "update from User set loginTime = '" + loginTime + "' where (userName = '" +userName + "' or phone = '" + userName + "' or email ='" + userName + "') and pwd = '" + password + "'";
              this.baseDao.update(hql);    	  
          }
          Restrecord rest=restrecordDao.findByUserId(user.getId());
          if(!OwnUtil.objectIsEmpty(rest)){
        	  user.setRecommendRestTime(rest.getRecommendRestTime());
        	  user.setRecommendWakeTime(rest.getRecommendWakeTime());
        	  getRequest().getSession().setAttribute("user", user);
        	  getTipNews(1);
          }else if(OwnUtil.objectIsEmpty(rest)&&OwnUtil.isAdmin(user)){
        	  getRequest().getSession().setAttribute("user", user);
        	  getTipNews(1);
          }else{
              throw new ServiceException("后台异常暂时无法登录！");       	  
          }
          
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
			String hql = "update from User set sessionId ='' where (email = '" + user.getUserName() + "' or phone = '" + user.getPhone() + "' or email ='"+user.getEmail()+"') and pwd = '" + user.getPwd() + "'";
			this.baseDao.update(hql);	
			getTipNews(0);
			CookieManager cm = new CookieManager(); 			 
			Cookie[] cookies = getRequest().getCookies();
			//清空cookie
			try {
				if(OwnUtil.stringIsEqual(cm.getCookieValue(cookies, "autoLogin"), user.getUserName())){
					Cookie cksessionID =cm.findCookieByName(cookies, "sessionid");
					Cookie ckUsername = cm.findCookieByName(cookies, "autoLogin");
					cksessionID.setMaxAge(0);
					ckUsername.setMaxAge(0);
					getResponse().addCookie(cksessionID);
					getResponse().addCookie(ckUsername);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			 
			return true;
		}else
		return false;		
	}

	@Override
	public User findBySessionUserName(UserQuery query) {
		return baseDao.findBySessionUserName(query);
	}

	@Override
	public User findByPhoneAndUserName(User user) {

	    String hql = "from User as u where userName =  '" + user.getUserName() + "' or phone ='" + user.getPhone() + "' or email ='"+user.getEmail()+"'";
		List<User> users = (List<User>) baseDao.findByhql(hql);
		if(OwnUtil.listisNotEmpty(users)){
			return users.get(0);
		}else
		return null;
	}

	@Override
	public User findByEmail(String email) {
	    String hql = "from User where email ='"+email+"'";
		List<User> users = (List<User>) baseDao.findByhql(hql);
		if(OwnUtil.listisNotEmpty(users)){
			return users.get(0);
		}else
		return null;
	}

	@Transactional(rollbackFor={Exception.class})
	@Override
	public void saveResetPwd(ResetPwd reset) {
		resetPwdDao.save(reset);
	}

	@Override
	public int findByEmailAndValidate(UserQuery query) {
		int status=0;
		
		List res= resetPwdDao.findByEmailAndValidate(query.getEmail(), query.getEmailVer());
		if(OwnUtil.listisNotEmpty(res)){
			ResetPwd rp = (ResetPwd) res.get(0);
			try {
				if(DateUtil.compareTime(rp.getRecordTime(),30)){//是否在30分钟内
					status=1;
					
				 return status;
				}else
					return status;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else
			status=-1;
		return status;
	}
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public int updatePwd(UserQuery query) {
		int status=0;
		String hql = "from User where userName='"+query.getUserName()+"' and email='"+query.getEmail()+"'";
		List list = baseDao.findByhql(hql);
		status = findByEmailAndValidate(query);
		
		if(OwnUtil.listisNotEmpty(list)&&status==1){
			ResetPwd rp = (ResetPwd) resetPwdDao.findByEmailAndValidate(query.getEmail(), query.getEmailVer()).get(0);
			rp.setStatus((short) 0);
			resetPwdDao.update(rp);//失效该验证码
			
			User user=(User) list.get(0);
			user.setPwd(MD5Util.encode2hex(query.getPwd()));//保存密码
			baseDao.update(user);//更新数据
		}
		
		return status;
		
	}

	@Override
	public List<User> findAll() {
		String hql ="select new User(u.id,u.userName) from User as u";
		return (List<User>) baseDao.findByhql(hql);
	}

	@Override
	public Page getCurrentPage(UserQuery query, Integer offset, Integer length) {
		return baseDao.getUserPage(query, offset, length);
	}

	@Override
	public void deleteUser(User model) {
		List<Home> homes = homeDao.getHomeList(model.getId());
		if(OwnUtil.listisNotEmpty(homes)){//关联删除用户其他信息 设备 和 住所
			for(Home home:homes){
				List<Devices> devices = deviesDao.getDevicesList(home.getId());
				if(OwnUtil.listisNotEmpty(devices)){
					for(Devices device:devices){
						deviesDao.delete(device);
					}
				}
				homeDao.delete(home);
			}
		}
		baseDao.delete(model);
		
	}

	@Override
	public void uploadPic(UserQuery query) {
		User user = get(query.getUid());
		user.setLogoImage(query.getDir());
		user.setExchangeTime(DateUtil.getCurrDateStr());
		update(user);
	}

	@Override
	public void getTipNews(int i) {
		TipNews tips = baseDao.getTipNews();
		if(!OwnUtil.objectIsEmpty(tips)){
			if(i==1){
				getRequest().getSession().setAttribute("tips", tips);
			}else{
				getRequest().getSession().removeAttribute("tips");
			}
		}
	}
}
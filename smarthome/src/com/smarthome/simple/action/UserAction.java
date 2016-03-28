package com.smarthome.simple.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;

import com.smarthome.base.BaseAction;
import com.smarthome.base.PeriodSum;
import com.smarthome.simple.entity.ResetPwd;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.UserQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.DateUtil;
import com.smarthome.util.JSONSerializer;
import com.smarthome.util.MD5Util;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.SendEmail;
import com.smarthome.util.StringUtils;

public class UserAction extends BaseAction<User, UserQuery>
{
  private static final long serialVersionUID = 1L;

  @Resource
  protected UserServices userService;
  private  String message="";
  
  
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
      message=e.getMessage();
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
  
  /**
   * 校验用户名、手机是否存在
   */
  public void userNameIsExist(){
	  User user = userService.findByPhoneAndUserName(model);
		  try {
			  if(!OwnUtil.objectIsEmpty(user)){
				  getResponse().getWriter().write(JSONSerializer.serialize(false));
			  }else{
				  getResponse().getWriter().write(JSONSerializer.serialize(true));
			  }
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
  
  
  /**
   * 新用户注册
   * @return
   * @throws ParseException
 * @throws UnsupportedEncodingException 
   */
  public String registerUser() throws ParseException, UnsupportedEncodingException{
	  //用户名
	  if(OwnUtil.stringIsEmpty(model.getUserName())){
		  message="用户名不能为空";
		  return "register_failed";
	  }
	  //邮箱
	  if(OwnUtil.stringIsEmpty(model.getEmail())){
		  message="邮箱不能为空";
		  return "register_failed";
	  }
	  //密码
	  if(OwnUtil.stringIsEmpty(model.getPwd())){
		  message="密码不能为空";
		  return "register_failed";
	  }
	  
	  model.setStatus((short) 1);//用户状态
	  model.setRegisterTime(DateUtil.getCurrTimestamp());//注册时间
	  model.setPwd(MD5Util.encode2hex(model.getPwd()));
	  Date birthday = DateUtil.strToDate(model.getBirthday(), "yyyy-MM-dd");
	  int age = DateUtil.getAge(birthday);
	  model.setAge(age);
	  model.setPeriod(PeriodSum.getPeriod(birthday,age));//后台自动生成人生阶段，方便日后计算客户生活作息规律表
	  //userService.save(model);
	  message="注册成功,请输入你的帐号密码登录吧。";
	  message=URLEncoder.encode(message, "utf-8");	  
	  System.out.println(message);
	  return "login_redirect";
  }
  
  /**
   * 发送邮件验证码
 * @throws IOException 
   */
  public void sendValidate(){
	  User user = userService.findByEmail(query.getEmail());
	  Boolean msg = false;
	
		  if(!OwnUtil.objectIsEmpty(user)){
			  String code = RandomStringUtils.randomAlphanumeric(6);
			  String validate="您的验证码是:"+code;
			  
			  ResetPwd reset = new ResetPwd();
			  reset.setEmailVer(code);
			  reset.setUid(user.getId());
			  reset.setRecordTime(DateUtil.getCurrDateStr());
			  reset.setStatus((short)1);
			  
			  userService.saveResetPwd(reset);
			
			  SendEmail.send(query.getEmail(), validate, "SMARTHOMEC 密码找回邮件");
			  
			  msg=true;
		  }
		  
	try {
			  getResponse().getWriter().write(JSONSerializer.serialize(msg));
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  
	  
  }
  
  /**
   * 检查验证码是否正确
   * @return
   */
  public String checkCode(){
	  int status  = userService.findByEmailAndValidate(query);
	  if(status==0){
		  message="验证码已经超时,请重新获取。";
	  }else if(status==1){
		   model = userService.findByEmail(query.getEmail());
		   query.setUserName(model.getUserName());
		   query.setEmail(model.getEmail());
		  return "resetpwd";
	  }else{
		  message="验证码不正确或者不存在。";
	  }
	  
	  return "failed_resetPwd";
  }
  

  
  /**
   * 找回密码之设置新密码
   * @return
   */
  public String updatePwd(){
 
	  int status = userService.updatePwd(query);
	  if(status==1){
		  message="修改密码成功，请使用新密码登录";
		  return "login_redirect";
	  }else{//重新开始 找回密码
		  message="密码找回失败，请重新尝试。（原因:对话超时验证码失效等情况...）";
		  return "failed_resetPwd";

	  }
  }

  
  
public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}


  
}
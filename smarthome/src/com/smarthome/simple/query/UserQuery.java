package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class UserQuery extends Query
{
  private static final long serialVersionUID = 1L;
  private String userName;
  private String pwd;//登录密码
  private String confirmpwd;//确定密码
  private String phone;
  private String sessionid;
  private String autologin;
  private String email;
  private String emailVer;//邮箱验证码
  
  /**
   * 是否为管理员的标识  1表示是 0或者空表示不是 或
   */
  private Short isAdmin;
  

  public String getUserName()
  {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPwd() {
    return this.pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  public String getPhone() {
    return this.phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getAutologin() {
    return this.autologin;
  }
  public void setAutologin(String autologin) {
    this.autologin = autologin;
  }
  public String getSessionid() {
    return this.sessionid;
  }
  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }
public Short getIsAdmin() {
	return isAdmin;
}
public void setIsAdmin(Short isAdmin) {
	this.isAdmin = isAdmin;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getEmailVer() {
	return emailVer;
}
public void setEmailVer(String emailVer) {
	this.emailVer = emailVer;
}
public String getConfirmpwd() {
	return confirmpwd;
}
public void setConfirmpwd(String confirmpwd) {
	this.confirmpwd = confirmpwd;
}

}
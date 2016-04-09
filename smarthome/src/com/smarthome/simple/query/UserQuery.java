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
  private Short status;
  private Short period;//时间阶段
  
  
	private String base64;//jq base64编码
	private String dir;//保存文件路径属性  
	private String targetFileName;//保存文件名称
	private String oldFileDir;
  
  
  
  /**
   * 是否为管理员的标识  1表示是 0或者空表示不是 或
   */
  private Short isAdmin;
  

  public String getUserName()
  {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName.trim();
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
    this.phone = phone.trim();
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
	this.email = email.trim();
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
public Short getStatus() {
	return status;
}
public void setStatus(Short status) {
	this.status = status;
}

public String getBase64() {
	return base64;
}

public String getDir() {
	return dir;
}

public void setBase64(String base64) {
	this.base64 = base64;
}

public void setDir(String dir) {
	this.dir = dir;
}
public Short getPeroid() {
	return period;
}
public void setPeroid(Short period) {
	this.period = period;
}
public String getTargetFileName() {
	return targetFileName;
}
public void setTargetFileName(String targetFileName) {
	this.targetFileName = targetFileName;
}
public String getOldFileDir() {
	return oldFileDir;
}
public void setOldFileDir(String oldFileDir) {
	this.oldFileDir = oldFileDir;
}


}
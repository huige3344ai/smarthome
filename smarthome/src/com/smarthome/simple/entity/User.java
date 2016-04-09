package com.smarthome.simple.entity;

import java.sql.Timestamp;

import com.smarthome.util.DateUtil;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String pwd;
	private String loginTime;
	private Short status;
	private Timestamp registerTime;
	private String email;
	private String birthday;
	private Integer age;
	private Short period;
	private String exchangeTime;
	private Integer adminId;
	private String preloginTime;
	private String logoImage;
	private String phone;
	private String sessionId;
	private Short isAdmin;
	private String note;
	
	
	private String recommendRestTime;//休息时间
	private String recommendWakeTime;//休息时长

	// Constructors

	/** default constructor */
	public User() {
	}
	
	/** 自定义*/
   public User(Short status)
   {
     this.status = status;
   }
   
   /**
    * User( id, logoImage, userName, status, phone, email, registerTime)
    */
   public User(Integer id,String logoImage,String userName,Short status,String phone,String email,Object registerTime)
   {
	   this.id = id;
	   this.userName = userName;
	   this.logoImage = logoImage;
	   this.status = status;
	   this.phone = phone;
	   this.email = email;
	   this.registerTime = DateUtil.stringToTimestamp(registerTime.toString());//需要这样子转换，不然无法被注入值导致找不到该虚构函数
   }
   
   
   
   public User(Integer  id, String userName)
   {
	   this.id = id;
	   this.userName = userName;
   }

	/** minimal constructor */
	public User(String userName, String pwd, Short status) {
		this.userName = userName;
		this.pwd = pwd;
		this.status = status;
	}

	/** full constructor */
	public User(String userName, String pwd, String loginTime, Short status,
			Timestamp registerTime, String email, String birthday, Integer age,
			Short period, String exchangeTime, Integer adminId,
			String preloginTime, String logoImage, String phone,
			String sessionId, Short isAdmin, String note) {
		this.userName = userName;
		this.pwd = pwd;
		this.loginTime = loginTime;
		this.status = status;
		this.registerTime = registerTime;
		this.email = email;
		this.birthday = birthday;
		this.age = age;
		this.period = period;
		this.exchangeTime = exchangeTime;
		this.adminId = adminId;
		this.preloginTime = preloginTime;
		this.logoImage = logoImage;
		this.phone = phone;
		this.sessionId = sessionId;
		this.isAdmin = isAdmin;
		this.note = note;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
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

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Short getPeriod() {
		return this.period;
	}

	public void setPeriod(Short period) {
		this.period = period;
	}

	public String getExchangeTime() {
		return this.exchangeTime;
	}

	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public String getRecommendRestTime() {
		return recommendRestTime;
	}

	public void setRecommendRestTime(String recommendRestTime) {
		this.recommendRestTime = recommendRestTime;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getPreloginTime() {
		return this.preloginTime;
	}

	public void setPreloginTime(String preloginTime) {
		this.preloginTime = preloginTime;
	}

	public String getLogoImage() {
		return this.logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Short getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRecommendWakeTime() {
		return recommendWakeTime;
	}

	public void setRecommendWakeTime(String recommendWakeTime) {
		this.recommendWakeTime = recommendWakeTime;
	}

}
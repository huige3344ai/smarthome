package com.smarthome.util;

import javax.servlet.http.Cookie;

public class CookieManager
{
	/**
	 * 查询cookie
	 * @param cookies
	 * @param value
	 * @return
	 */
  public String getCookieValue(Cookie[] cookies, String value)
  {
    String cookieValue = "";
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals(value))
          cookieValue = cookies[i].getValue().split("-")[0];
      }
    }
    return cookieValue;
  }
  
  /**
   * @param cookies数组
   * @param   value 需要寻找的cookie name
   * @return Cookie 
   * 查相应cookie，返回cookie.
   */
  public Cookie  findCookieByName(Cookie[] cookies,String value){
	  Cookie cookie=null;
	    if (cookies != null) {
	        for (int i = 0; i < cookies.length; i++) {
	          if (cookies[i].getName().equals(value))
	        	  cookie=cookies[i];
	        }
	      }
	  return cookie;
  }
  
  
   
}
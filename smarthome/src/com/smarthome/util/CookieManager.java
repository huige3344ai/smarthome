package com.smarthome.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;

public class CookieManager
{
	/**
	 * 查询cookie
	 * @param cookies
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
  public String getCookieValue(Cookie[] cookies, String value) throws UnsupportedEncodingException
  {
    String cookieValue = "";
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals(value))
          cookieValue = cookies[i].getValue().split("-")[0];
      }
    }
    return URLDecoder.decode(cookieValue,"utf-8") ;
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
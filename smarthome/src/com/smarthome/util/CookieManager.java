package com.smarthome.util;

import javax.servlet.http.Cookie;

public class CookieManager
{
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
}
package com.smarthome.util;

import com.smarthome.simple.entity.User;

import java.util.Collection;
import java.util.List;

public class OwnUtil
{
	/**
	 * 判断对象是否为空
	 * @param ob
	 * @return
	 */
  public static boolean objectIsEmpty(Object ob)
  {
    if (ob == null) {
      return true;
    }
    return false;
  }

  /**
   * 判断集合是否为空
   * @param 
   * @return
   */
  public static boolean ListisNotEmpty(Collection<?> list)
  {
    if ((list != null) && (list.size() > 0)) {
      return true;
    }
    return false;
  }

  /**
   * 判断是否为超级管理员
   * @param user
   * @return
   */
  public static boolean isAdmin(User user)
  {
    if ((!objectIsEmpty(user)) && 
      (user.getUserName().equals("admin"))) {
      return true;
    }

    return false;
  }

  
  public static boolean stringIsEmpty(String s)
  {
    return (s == null) || (s.length() == 0);
  }

  
  public static boolean stringIsEqual(String value, String compareString)
  {
    return value != null ? value.equals(compareString) : false;
  }
}
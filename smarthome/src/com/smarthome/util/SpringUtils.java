package com.smarthome.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils
  implements ApplicationContextAware
{
  private static ApplicationContext applicationContext;

  public static ApplicationContext getApplicationContext()
  {
    checkApplicationContextIsNull();
    return applicationContext;
  }

  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException
  {
	  
	  SpringUtils.applicationContext = applicationContext;
  }

  public static <T> T getBeanById(String id)
  {
	 checkApplicationContextIsNull();
	return (T)applicationContext.getBean(id);
  }

  public static <T> T getBeanByClass(Class paramClass)
  {		
	  checkApplicationContextIsNull();
	  return (T)applicationContext.getBean(paramClass);
  }

  public static Map getBean(Class clazz)
  {
	checkApplicationContextIsNull();
    return applicationContext.getBeansOfType(clazz);
  }

  /**
   * 判断applicationContext是否为空
   */
  public static void checkApplicationContextIsNull()
  {
    if (applicationContext == null)
      throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringUtils");
  }
}
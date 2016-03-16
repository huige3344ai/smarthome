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
    applicationContext = applicationContext;
  }

  public static <T> T getBeanById(String paramString)
  {
    throw new Error("Unresolved compilation problem: \n\tType mismatch: cannot convert from Object to T\n");
  }

  public static <T> T getBeanByClass(Class paramClass)
  {
    throw new Error("Unresolved compilation problem: \n\tType mismatch: cannot convert from Object to T\n");
  }

  public static Map getBean(Class clazz)
  {
    return applicationContext.getBeansOfType(clazz);
  }

  /**
   * 判断applicationContext是否为空
   */
  public static void checkApplicationContextIsNull()
  {
    if (applicationContext == null)
      throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
  }
}
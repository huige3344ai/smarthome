package com.smarthome.base;

import com.smarthome.simple.services.ServiceException;
import com.smarthome.util.SpringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends Serializable, Q extends Query>
  implements BaseService<T, Q>
{
  private Class<T> entityClass = null;
  private BaseDao baseDao;
  protected String returnStr;
  

  @Resource
  protected SessionFactory sessionFactory;

  public BaseServiceImpl()
  {
    ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
    this.entityClass = ((Class)type.getActualTypeArguments()[0]);
  }

	/**
	 * 这个方法会在构造函数和spring以来注入之后执行
	 * @Title: init
	 * @Description: TODO(通过反射来实例化baseDao)
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 */
	@PostConstruct
	public void init() throws Exception
	{
		String clazzName = entityClass.getSimpleName();
		String clazzDaoName = clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Dao";//toLowerCase首字母小写
		System.out.println("======Service正在初始化"+clazzDaoName+"=======");
		baseDao = SpringUtils.getBeanById(clazzDaoName);
		System.out.println("======"+clazzDaoName+"初始化完毕=======");

		
	}
  
  
  @Transactional(rollbackFor={ServiceException.class})
  public void save(T t)
  {
    if (t == null) {
      return;
    }
    this.baseDao.save(t);
  }

  @Transactional(rollbackFor={ServiceException.class})
  public void delete(T t)
  {
    this.baseDao.delete(t);
  }

  @Transactional(rollbackFor={ServiceException.class})
  public void update(T t)
  {
    if (t == null) {
      return;
    }
    this.baseDao.update(t);
  }

  @Transactional(readOnly=true)
  public List<T> query()
  {
    return this.baseDao.findAll(this.entityClass);
  }

  @Transactional(readOnly=true)
  public T get(Serializable paramInt)
  {
	  return (T)this.baseDao.findById(paramInt);
  }

  public HttpServletRequest  getRequest(){
	  return  ServletActionContext.getRequest();
  }
  
  public HttpServletResponse  getResponse(){
	  return  ServletActionContext.getResponse();
  }
}
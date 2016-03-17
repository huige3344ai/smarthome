package com.smarthome.base;

import com.smarthome.simple.services.ServiceException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
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

  @Transactional(rollbackFor={ServiceException.class})
  public void save(T t)
  {
    if (t == null) {
      return;
    }
    this.baseDao.save(t);
  }

  @Transactional(rollbackFor={ServiceException.class})
  public void delete(int id)
  {
    this.baseDao.delete(Integer.valueOf(id));
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
  public T get(int paramInt)
  {
    throw new Error("Unresolved compilation problem: \n\tType mismatch: cannot convert from Serializable to T\n");
  }

  public HttpServletRequest  getRequest(){
	  return  ServletActionContext.getRequest();
  }
  
  public HttpServletResponse  getResponse(){
	  return  ServletActionContext.getResponse();
  }
}
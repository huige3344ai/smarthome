package com.smarthome.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

public class BaseDaoImpl<T> extends HibernateDaoSupport
  implements BaseDao<T>
{
  private Class<T> entityClass = null;

  protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

  @Resource
  private SessionFactory sessionFactory;

  public BaseDaoImpl() {
    this.entityClass = ((Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
  }

  protected Class<T> getEntityClass()
  {
    return this.entityClass;
  }

  public List<T> findAll(Class<T> entityClass)
  {
    try
    {
      return getHibernateTemplate().loadAll(entityClass);
    }
    catch (RuntimeException re) {
      log.error("loadAll  failed", re);
      throw re;
    }
  }

  @SuppressWarnings("unchecked")
public Page<T> findByPage(final String hql, final Integer offset,final Integer length,  
          final Object... values)
  {
	  try {    
          if (logger.isDebugEnabled()) {    
              logger.debug("开始查找指定HQL分页数据," + hql);    
          }    
          return (Page<T>) getHibernateTemplate().execute(    
                  new HibernateCallback() {    
                      public Object doInHibernate(Session s)    
                              throws HibernateException, SQLException {  
                          Query query = createQuery(hql, values);  
                          ScrollableResults sr = query.scroll();  
                          sr.last();  
                          int totalCount = sr.getRowNumber();  
                          query.setFirstResult(length*(offset-1)).setMaxResults(length); 
                          Page p=new Page(query.list());  
                          p.setTotalCount(totalCount+1);  
                          logger.info("查找指定HQL分页数据成功："+hql);  
                          return p;    
                      }    
                  });    
      } catch (RuntimeException e) {    
          logger.error("分页查询异常，HQL：" + hql, e);    
          throw e;    
      }
  }

  public Query createQuery(String hql, Object... objects)
  {
    Query query = sessionFactory.getCurrentSession().createQuery(hql);
    if (objects != null) {
      for (int i = 0; i < objects.length; i++) {
        query.setParameter(i, "%" + objects[i] + "%");
      }
    }
    return query;
  }

  public Query createEQuery(String hql, Object... objects)
  {
    Query query = sessionFactory.getCurrentSession().createQuery(hql);
    if (!OwnUtil.objectsIsEmpty(objects)) {
      for (int i = 0; i < objects.length; i++) {
        query.setParameter(i, objects[i]);
      }
    }
    return query;
  }

  public List<T> findAll(String orderBy, boolean isAsc)
  {
    try
    {
      Assert.hasText(orderBy);
      if (isAsc) {
        return getHibernateTemplate().findByCriteria(
          DetachedCriteria.forClass(this.entityClass).addOrder(Order.asc(orderBy)));
      }
      return getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(this.entityClass).addOrder(Order.desc(orderBy)));
    }
    catch (RuntimeException re) {
      log.error("findByCriteria  failed", re);
      throw re;
    }
  }



  public List<?> findByhql(String hql)
  {
    List list_t = new ArrayList();
    try {
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      List list = query.list();
      if (OwnUtil.listisNotEmpty(list)) {
        return list;
      }
      return null;
    } catch (RuntimeException e) {
      log.error("findAllByenable  failed");
      e.printStackTrace();
    }

    return list_t;
  }

  public List<T> findByExample(Object o)
  {
    try
    {
      return getHibernateTemplate().findByExample(o);
    }
    catch (RuntimeException re) {
      log.error("findByExample  failed", re);
      throw re;
    }
  }

  public T findById(Serializable id)
  {
    try
    {
      return getHibernateTemplate().get(this.entityClass, id);
    } catch (RuntimeException re) {
      log.error("findById  failed", re);
      throw re;
    }
  }

  public List<T> findAllByCondition(String hql, Object... values)
  {
    Query query = createQuery(hql, values);
    List list = query.list();
    return list;
  }
  
  @Override
  public List<T> findAllByProperty(String param, Object object,String orderBy,boolean isAsc) {
	  String hql = "from "+entityClass+" where "+param+"=? ";
	  if(orderBy!=null&&!isAsc){
		  hql =hql+" order by "+orderBy+" desc";
	  }
	  Query query = createEQuery(hql, object);	  
  	return query.list();
  }
  

  public void deleteById(Serializable id)
  {
    try {
      delete(findById(id));
    } catch (RuntimeException re) {
      log.error("deleteById  failed", re);
    }
  }

  public void delete(Object o)
  {
    try {
      getHibernateTemplate().delete(o);
    } catch (RuntimeException re) {
      log.error("delete  failed", re);
    }
  }

  public void save(Object o)
  {
    try {
      getHibernateTemplate().save(o);
    } catch (RuntimeException re) {
      log.error("save  failed", re);
      re.printStackTrace();
    }
  }

  public void saveOrUpdate(Object o)
  {
    try {
      getHibernateTemplate().saveOrUpdate(o);
    } catch (RuntimeException re) {
      log.error("saveOrUpdate  failed", re);
    }
  }

  public void update(Object o)
  {
    try {
      getHibernateTemplate().update(o);
    } catch (RuntimeException re) {
      log.error("update  failed", re);
      re.printStackTrace();
    }
  }

  public void merge(Object o) {
    try {
      getHibernateTemplate().merge(o);
    }
    catch (RuntimeException re) {
      log.error("merge  failed", re);
      re.printStackTrace();
    }
  }

  public void update(String hql)
  {
    log.debug(hql);
    try {
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.executeUpdate();
    }
    catch (RuntimeException e) {
      e.printStackTrace();
      log.error(hql + "：更新失败！！！");
    }
  }

  public void handle(String sql)
  {
    log.debug(sql);
    try {
      Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
      query.executeUpdate();
    }
    catch (RuntimeException e) {
      e.printStackTrace();
      log.error(sql + "：操作失败！！！");
    }
  }

@SuppressWarnings({ "unchecked", "rawtypes" })
@Override
public Page<T> findByFinallyPage(final String hql, final Integer offset,final Integer length,  
        final Object... values){
	  try {    
          if (logger.isDebugEnabled()) {    
              logger.debug("开始查找指定HQL分页数据," + hql);    
          }    
          return (Page<T>) getHibernateTemplate().execute(    
                  new HibernateCallback() {    
                      public Object doInHibernate(Session s)    
                              throws HibernateException, SQLException {  
                          Query query = createEQuery(hql, values);  
                    	  ScrollableResults sr = query.scroll();  
                          sr.last();  
                          int totalCount = sr.getRowNumber();  
                          query.setFirstResult(length*(offset-1)).setMaxResults(length); 
                          Page p=new Page(query.list());  
                          p.setTotalCount(totalCount+1);  
                          logger.info("查找指定HQL分页数据成功："+hql);  
                          return p;    
                      }    
                  });    
      } catch (RuntimeException e) {    
          logger.error("分页查询异常，HQL：" + hql, e);    
          throw e;    
      }
}


}
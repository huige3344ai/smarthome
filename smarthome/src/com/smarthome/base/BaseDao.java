package com.smarthome.base;

import com.smarthome.util.Page;

import java.io.Serializable;
import java.util.List;

public abstract interface BaseDao<T>
{
  public abstract void save(Object paramObject);

  public abstract void delete(Object paramObject);

  public abstract void deleteById(Serializable paramSerializable);

  public abstract void update(Object paramObject);

  public abstract void update(String paramString);

  public abstract void handle(String paramString);

  public abstract void merge(Object paramObject);

  public abstract void saveOrUpdate(Object paramObject);

  /**
   * 通过主键去查下
   * @param paramSerializable
   * @return
   */
  public abstract T findById(Serializable paramSerializable);

  /**
   * 加载所有映射文件
   * @param paramClass
   * @return
   */
  public abstract List<T> findAll(Class<T> paramClass);

	/**
	 *  为了service层方便直接编写简单的hql语句 
	 * @param hql
	 * @return
	 */
  public abstract List<?> findByhql(String hql);

  /**
   * 通过一个属性值进行查询全部数据
   * @param param 
   * @param object
   * @param orderBy
   * @param isAsc
   * @return
   */
  public abstract List<T> findAllByProperty(String param,Object object,String orderBy,boolean isAsc);
  
  /**
   * 根据 (?)占位符 查询相应的内容
   * @param paramString
   * @param paramArrayOfObject
   * @return
   */
  public abstract List<T> findAllByCondition(String paramString, Object... paramArrayOfObject);

  /**
   * 直接查询相应实体类的所有数据 可以选择排序方式
   * @param orderBy
   * @param isAsc
   * @return
   */
  public abstract List<T> findAll(String orderBy, boolean isAsc);

  public abstract List<T> findByExample(Object paramObject);

  public abstract Page<T> findByPage(String paramString, Integer paramInteger1, Integer paramInteger2, Object[] paramArrayOfObject);


}


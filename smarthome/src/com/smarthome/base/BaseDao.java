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

  public abstract T findById(Serializable paramSerializable);

  public abstract List<T> findAll(Class<T> paramClass);

  public abstract List<T> findAllByhql(String paramString);

  public abstract List<?> findByhql(String paramString);

  public abstract List<T> findAllByCondition(String paramString, Object[] paramArrayOfObject);

  public abstract List<T> findAll(String paramString, boolean paramBoolean);

  public abstract List<T> findByExample(Object paramObject);

  public abstract Page<T> findByPage(String paramString, Integer paramInteger1, Integer paramInteger2, Object[] paramArrayOfObject);
}


package com.smarthome.base;

import java.io.Serializable;
import java.util.List;

public abstract interface BaseService<T, Q>
{
  public abstract void save(T paramT);

  public abstract void delete(T paramT);

  public abstract void update(T paramT);

  public abstract List<T> query();

  public abstract T get(Serializable paramInt);
}


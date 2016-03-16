package com.smarthome.base;

import java.io.Serializable;

public class Query
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected Integer id;
  protected String URI;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getURI() {
    return this.URI;
  }

  public void setURI(String uRI) {
    this.URI = uRI;
  }
}
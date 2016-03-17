package com.smarthome.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.smarthome.util.OwnUtil;
import com.smarthome.util.Page;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class BaseAction<T extends Serializable, Q extends Query> extends ActionSupport
  implements SessionAware, ServletRequestAware, ModelDriven<T>
{
  private static final long serialVersionUID = 6230751116897773145L;
  public static final Log log = LogFactory.getLog(BaseAction.class);
  protected Map<String, Object> session;
  HttpServletResponse response;
  protected HttpServletRequest request;
  protected T model;
  protected Q query;
  protected int pageNum;
  protected int numPerPage;
  protected int totalCount;
  protected int currentPage;
  protected int totalPage;
  protected Page<T> page;
  int i;

  public Q getQuery()
  {
	 checkQuery();
    return this.query;
  }

  public void setQuery(Q query) {	
    this.query = query;
  }

  public <entity> Page<entity> createPage(Page page)
  {
    page.setNumPerPage(getNumPerPage());
    page.setPageNum(getPageNum());
    page.setTotalPage(gainTotalPage(page.getTotalCount(), getNumPerPage()));
    setPage(page);
    return page;
  }

  public int gainTotalPage(int totalCount, int rowsperpage) {
    int pages = 0;
    if (totalCount == 0) {
      pages = 0;
    } else {
      if (totalCount <= rowsperpage)
        pages = 1;
      if ((totalCount > rowsperpage) && (totalCount % rowsperpage == 0))
        pages = totalCount / rowsperpage;
      if ((totalCount > rowsperpage) && (totalCount % rowsperpage != 0)) {
        pages = totalCount / rowsperpage + 1;
      }
    }
    return pages;
  }

  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
  }

  protected HttpServletRequest getRequest()
  {
    return ServletActionContext.getRequest();
  }

  protected HttpSession getSession() {
    return ServletActionContext.getRequest().getSession();
  }

  public int getPageNum() {
    return this.pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getNumPerPage() {
    return this.numPerPage;
  }

  public void setNumPerPage(int numPerPage) {
    this.numPerPage = numPerPage;
  }

  public int getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getTotalPage() {
    return this.totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public Page getPage() {
    return this.page;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public void setRequest(HttpServletRequest request)
  {
    this.request = request;
  }

  public T getModel()
  {
    return this.model;
  }
  
  public void checkQuery(){
	  if(OwnUtil.objectIsEmpty(query)){
		  try {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class Q = (Class) type.getActualTypeArguments()[1];
				query = (Q) Q.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
			}
	  }
  }
}
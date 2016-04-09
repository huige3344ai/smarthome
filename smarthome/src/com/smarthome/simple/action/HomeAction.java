package com.smarthome.simple.action;

import java.io.IOException;

import javax.annotation.Resource;

import com.smarthome.base.BaseAction;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.query.HomeQuery;
import com.smarthome.simple.services.HomeServices;
import com.smarthome.util.DateUtil;
import com.smarthome.util.JSONSerializer;
import com.smarthome.util.OwnUtil;

public class HomeAction extends BaseAction<Home, HomeQuery> {
	@Resource
	protected HomeServices homeServices;
	
	
	/**
	 * 房屋管理首页 分页数据 
	 * @return
	 */
	public String homesList(){
		page = homeServices.getCurrentPage(query,this.pageNum,this.numPerPage);
		createPage(page);
        if(!OwnUtil.objectIsEmpty(page)&&page.size()>0&&page.getPageNum()>page.getTotalPage())
        	return "direct_homesList";
		return "homesList";
	}
	
	/**
	 * 根据homeId  获取唯一的房屋信息
	 */
	public void getHome(){
		try {
			model = homeServices.get(query.getId());
			getResponse().getWriter().write(JSONSerializer.serialize(model).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 添加 住所信息
	 */
	public void addHome(){
		try {
			model.setRecordTime(DateUtil.getCurrDateStr());//设置添加时间
			model.setUsreId(getUserId());//设置添加住所的用户
			homeServices.save(model);
			getResponse().getWriter().write(JSONSerializer.serialize(true).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 更新住所信息
	 */
	public void updateHome(){
		try {
			Home home = homeServices.get(query.getId());
			home.setUsreId(getUserId());//设置添加住所的用户
			home.setExchangeTime(DateUtil.getCurrDateStr());
			home.setOwnerId(model.getOwnerId());
			home.setCountry(model.getCountry());
			home.setAddress(model.getAddress());
			homeServices.update(home);
			getResponse().getWriter().write(JSONSerializer.serialize(true).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void deleteHome(){
		try {
			model = homeServices.get(query.getId());
			homeServices.deleteHome(model);
			getResponse().getWriter().write(JSONSerializer.serialize(true).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	

}

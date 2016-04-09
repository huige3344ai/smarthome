package com.smarthome.simple.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.smarthome.base.BaseAction;
import com.smarthome.simple.entity.Devices;
import com.smarthome.simple.entity.Home;
import com.smarthome.simple.entity.User;
import com.smarthome.simple.query.DevicesQuery;
import com.smarthome.simple.services.DevicesServices;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.UserServices;
import com.smarthome.util.Constans;
import com.smarthome.util.DateUtil;
import com.smarthome.util.JSONSerializer;
import com.smarthome.util.OwnUtil;

public class DevicesAction extends
		BaseAction<Devices, DevicesQuery> {
	@Resource
	protected DevicesServices devicesService;
    @Resource
    protected UserServices userService;

    /**
     * 根据id获取对象  通过json方式返回
     */
	public void getDevice(){
		try {
			model=devicesService.get(query.getId());
			getResponse().getWriter().write(JSONSerializer.serialize(model).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
    
	/**
	 * 设备查询 设备首页
	 */
	public String devicesList(){
	    page=devicesService.getCurrentPage(query,this.pageNum,this.numPerPage);  
        createPage(page);
        if(!OwnUtil.objectIsEmpty(page)&&page.size()>0&&page.getPageNum()>page.getTotalPage())
        	return "direct_devicesList";
		return "devicesList";
        	
	}
	
	/**
	 * 获取所有用户
	 */
	public void getAllUserList(){
		try {
			List<User> list = userService.findAll();
			getResponse().getWriter().write(JSONSerializer.serialize(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * 根据用户id获取用户所有住所
	 */
	public void getHomeList(){
		try {
			List<Home> list = devicesService.findHomeByUid(query);
			getResponse().getWriter().write(JSONSerializer.serialize(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	/**
	 * 保存 设备
	 */
	public void saveDevices(){
		
		try {
			model.setRecordTime(DateUtil.getCurrDateStr());
			model.setStatus("0");
			devicesService.saveDevices(model);
			getResponse().getWriter().write(JSONSerializer.serialize(true).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 更新 设备信息
	 */
	public void updateDevices(){
		try {

			Devices devices = devicesService.get(query.getId());
			
			devices.setDeviceNum(model.getDeviceNum());
			devices.setDeviceName(model.getDeviceName());
			devices.setUserId(model.getUserId());
			devices.setHomeId(model.getHomeId());
			devices.setStatus(model.getStatus());
			devicesService.update(devices);
			getResponse().getWriter().write(JSONSerializer.serialize(true).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除设备
	 */
	public void deleteDevices(){
		try {
			model = devicesService.get(query.getId());
			try {
				devicesService.deleteDevices(model);
			} catch (ServiceException e) {
				getResponse().getWriter().write(JSONSerializer.serialize(e).toString());
				e.printStackTrace();
			}
			getResponse().getWriter().write(JSONSerializer.serialize(true).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public String myDevices(){
		query.setUid(getUserId());
		if(OwnUtil.intIsZero(query.getHomeId())){
			List<Home> list = devicesService.findHomeByUid(query);
			if(OwnUtil.listisNotEmpty(list)){//取出第一个 
				query.setHomeId(list.get(0).getId());
			}
		}
	    page=devicesService.getMyDevices(query,this.pageNum,this.numPerPage);  
        createPage(page);
        if(!OwnUtil.objectIsEmpty(page)&&page.size()>0&&page.getPageNum()>page.getTotalPage())
        	return "direct_myDevices";
		return "myDevices";	
		
	}
	
	/**
	 * 根据当前用户id获取用户所有住所
	 */
	public void getMyHomeList(){
		try {
			if(!OwnUtil.intIsZero(getUserId())){
				query.setUid(getUserId());
				List<Home> list = devicesService.findHomeByUid(query);
				getResponse().getWriter().write(JSONSerializer.serialize(list).toString());
			}else
				getResponse().getWriter().write(JSONSerializer.serialize(false).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户操更新设备
	 */
	public void updateMyDevices(){
		String msg = Constans.RETRUN_FAILED_STATUS;
		query.setUid(getUserId());
		if(!OwnUtil.stringIsEmpty(query.getStatus())&&!OwnUtil.intIsZero(query.getId())){
			model = devicesService.confirmDevices(query);
			if(!OwnUtil.objectIsEmpty(model)){
				model.setStatus(query.getStatus());
				model.setExchangeTime(DateUtil.getCurrDateStr());
				devicesService.update(model);
				msg=Constans.RETRUN_SUCCESS_STATUS;		
			}else
				msg=Constans.RETRUN_EXCEPTION_STATUS;		
		}
		try {
			getResponse().getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 关闭个人当前地区所有所有设备
	 */
	public void closeAllMyDevices(){
		query.setUid(getUserId());
		String msg = devicesService.closeAllMyDevices(query);
		try {
			if(OwnUtil.stringIsEmpty(msg)){
				msg="";
				getResponse().getWriter().write(msg);
			}else
				getResponse().getWriter().write(msg);
				
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}

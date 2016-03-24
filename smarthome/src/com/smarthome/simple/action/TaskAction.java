package com.smarthome.simple.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.smarthome.base.BaseAction;
import com.smarthome.simple.entity.Memoradum;
import com.smarthome.simple.query.MemoradumQuery;
import com.smarthome.simple.services.ServiceException;
import com.smarthome.simple.services.TaskServices;
import com.smarthome.util.DateUtil;
import com.smarthome.util.JSONSerializer;
import com.smarthome.util.OwnUtil;

/**
 * 备忘录 Action
 * 
 * @author Hy
 *
 */
public class TaskAction extends BaseAction<Memoradum, MemoradumQuery> {
	@Resource
	protected TaskServices taskService;

	/**
	 * 添加备忘录
	 */
	public void addTask() {
		try {
			List<String> time = OwnUtil.getStartAndEndtime(getQuery().getStartTimeAndEndtime(), null);
			if (time.size() == 2) {
				model.setStartTime(time.get(0));
				model.setEndTime(time.get(1));
				model.setUserId(getCurrentUser().getId());
				model.setRecordTime(DateUtil.getCurrDateStr());
				taskService.save(model);
				getResponse().getWriter().write("添加成功");
			} else
				getResponse().getWriter().write("添加失败");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询个人的任务
	 */
	public void  findTask(){
		try {			
			getQuery().setUid(getCurrentUser().getId());			
			List<Memoradum> tasks =  taskService.findByStart(getQuery());
			getResponse().getWriter().write(JSONSerializer.serialize(tasks));
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * 更新任务
	 * @throws IOException
	 */
	public void updateTask() throws IOException{
		Memoradum task = taskService.get(getQuery().getId());
		if(OwnUtil.stringIsEmpty(getQuery().getContent()))
			task.setContents(getQuery().getContent());
		
			List<String> time = OwnUtil.getStartAndEndtime(getQuery().getStartTimeAndEndtime(), null);
			if (time.size() == 2) {//非拖动修改
				task.setStartTime(time.get(0));
				task.setEndTime(time.get(1));
			}else{
				task.setStartTime(getQuery().getStartTime());
				task.setEndTime(getQuery().getEndTime());
			}
			
			task.setRecordTime(DateUtil.getCurrDateStr());
			
			taskService.update(task);
			task = taskService.get(getQuery().getId());
			getResponse().getWriter().write("1");
	}
	
	/**
	 * 删除任务
	 */
	public void deleteTask(){
		try {
			taskService.delete(taskService.get(getQuery().getId()));
			getResponse().getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

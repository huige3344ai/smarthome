package com.smarthome.util;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.smarthome.simple.services.UserServices;

@Service
public class TaskJob {
	Logger log = org.slf4j.LoggerFactory.getLogger(TaskJob.class);
	boolean ending = true;
	public void updateTask(){
		if(ending){
			UserServices userService = SpringUtils.getBeanById("userService");
			log.info("start update ...");
			ending = userService.updateAllUserPeriod();
			log.info("start ending ...");
		}else
			log.error("task is not finished ...");
		ending = true;
	}
}

package com.smarthome.util;

import com.smarthome.simple.entity.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

public class OwnUtil {
	/**
	 * 判断对象是否为空
	 * 
	 * @param ob
	 * @return
	 */
	public static boolean objectIsEmpty(Object ob) {
		if (ob == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个对象是否一致
	 * 
	 * @param ob
	 * @param ob2
	 * @return
	 */
	public static boolean objectIsEqual(Object ob, Object ob2) {
		if (ob != null && ob2 != null && ob.equals(ob2)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param
	 * @return
	 */
	public static boolean ListisNotEmpty(Collection<?> list) {
		if ((list != null) && (list.size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为超级管理员
	 * 
	 * @param user
	 * @return
	 */
	public static boolean isAdmin(User user) {
		if ((!objectIsEmpty(user)) && (user.getUserName().equals("admin"))) {
			return true;
		}

		return false;
	}

	public static boolean stringIsEmpty(String s) {
		return (s == null) || (s.length() == 0);
	}

	public static boolean stringIsEqual(String value, String compareString) {
		return value != null ? value.equals(compareString) : false;
	}

	/**
	 * 拆分开始结束时间并返回 简单的位置分割
	 * 
	 * @param value
	 * @param startTime
	 * @param endTime
	 * @return List<String>
	 */
	public static List<String> getStartAndEndtime( String value,String symbol) {
		List<String> time = new ArrayList<String>();
		String startTime =  value.substring(0, 16);
		String endTime = value.substring(19);
		if (startTime != null && endTime != null) {
			time.add(startTime);
			time.add(endTime);			
		}
		return time;
	}

}
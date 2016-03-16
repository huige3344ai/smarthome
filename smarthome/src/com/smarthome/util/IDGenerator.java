package com.smarthome.util;

import java.util.UUID;

/**
 * 
 * 
 * IDGenerator.java Create on 2011-12-1 下午10:53:14
 *  
 * Copyright (c) 2011 by ie580. All rights reserved.
 *  
 * @author zzm
 */
public class IDGenerator {
	/**
	 * 用默认的方法获取UUID
	 * 
	 * @return
	 */
	public static String getDefaultUUID() {
		// 获取uuid
		String uuidStr = UUID.randomUUID().toString();
		uuidStr = uuidStr.replace("-", "");
		return uuidStr;// 默认替换-，再后加8位随机数
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getDefaultUUID());
		}
	}
}

package com.smarthome.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFile {
	
	/*日志实现*/
	private static final Logger log = LoggerFactory.getLogger(UploadFile.class);	
	
	/** 
     * new文件名  = 时间 + 随机数 
     * @param fileName:旧文件名
     * @return new文件名 
     */  
	public static String generateFileName(String fileName) {  
        //时间  
        DateFormat df = new SimpleDateFormat("yyMMddHHmmss");     
        String formatDate = df.format(new Date());  
        //随机数  
        int random = new Random().nextInt(10000);   
        //文件后缀  
        int position = fileName.lastIndexOf(".");     
        String extension = fileName.substring(position);     
        return formatDate + random + extension;     
    }  
	
	/**
	 * 获取文件后缀名
	 * @param fileName:旧文件名
	 * @return
	 */
	public static String getExtension(String fileName){
        //文件后缀  
        int position = fileName.lastIndexOf(".");     
        String extension = fileName.substring(position); 
        System.out.println("extension:"+extension);
		return extension;
	}
	
}

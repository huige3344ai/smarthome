package com.smarthome.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

import com.smarthome.simple.entity.User;

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
	public static boolean listisNotEmpty(Collection<?> list) {
		if ((list != null) && (list.size() > 0)) {
			return true;
		}
		return false;
	}
	
	public static boolean objectsIsEmpty(Object[] objects){
		return (objects==null)||(objects.length==0);
	}
	

	/**
	 * 判断是否为超级管理员
	 * 
	 * @param user
	 * @return
	 */
	public static boolean isAdmin(User user) {
		if ((!objectIsEmpty(user)) && stringIsEqual(user.getUserName(), "admin")) {
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
	
	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(17([6-8]))|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 判断身份证号码是否正确
	 * @param idcard
	 * @return
	 */
	public static boolean checkIdcard(String idcard){
		 //定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）  
       boolean flag = false;
		Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
       //通过Pattern获得Matcher  
       Matcher idNumMatcher = idNumPattern.matcher(idcard);
       if(idNumMatcher.matches()){
       	flag = true;
       }
       return flag;
	}	
	
	/**
	 * 变换文件名称
	 * @param fileName
	 * @return
	 */
	public static String generateFileName(String fileName){
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatdate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String  extendstion  = fileName.substring(position);//后缀名		
		String newfileNmae = formatdate+random+extendstion;
		return newfileNmae;			
	}
	
    /**
     * base64转换成图片文件
     * @param base64
     * @param path
     * @return
     */
    public static boolean base64ToImage(String base64, File path) {// 对字节数组字符串进行Base64解码并生成图片
    	if (base64 == null){ // 图像数据为空
    		return false;
    	}
    	BASE64Decoder decoder = new BASE64Decoder();
    	try {
    		// Base64解码
    		byte[] bytes = decoder.decodeBuffer(base64);
    		for (int i = 0; i < bytes.length; ++i) {
    			if (bytes[i] < 0) {// 调整异常数据
    				bytes[i] += 256;
    			}
    		}
    		// 生成jpeg图片
    		OutputStream out = new FileOutputStream(path);
    		out.write(bytes);
    		out.flush();
    		out.close();
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();	
    		return false;
    	}	
    }
    
    /**
     * int判断是否为空
     * @param id
     * @return
     */
    public static boolean intIsZero(Integer  id){
    	if(id!=null&&id==0)
    		return true;
    	else
    		return false;
    }
	
    /**
     * 判断int 是否为o  不为null并且为大于0  直接返回 数值 否则返回数值
     * @param id
     * @return
     */
    public static Integer returnZero(Integer  id){
    	if(id!=null&&id!=0)
    		return id;
    	else
    		return 0;
    }
    
    

}
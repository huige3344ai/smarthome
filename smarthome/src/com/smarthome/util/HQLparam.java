package com.smarthome.util;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class HQLparam {
	
	/**
	 * 追加 and like 条件
	 * @param param
	 * 变量
	 * @param hql
	 * @return
	 */
	public static StringBuffer appendAndLike(String param,StringBuffer hql){
		return hql.append(" and  "+param+" like ? ");
	}
	
	/**
	 * 追加  and = 条件
	 * @param param
	 * 变量
	 * @param hql
	 * @return
	 */
	public static StringBuffer appendAnd(String param,StringBuffer hql){
		return hql.append(" and  "+param+"=? ");		
	}
	
	
	/**
	 * 动态 生成需要查询的变量集合
	 * @param object
	 * @param num
	 * @param params
	 * @return
	 */
	public static Object[] returnObjects(Object object ,final int num ,String...params){
		if(object==null)
			return null;
		Field[] fields= object.getClass().getDeclaredFields();
		ArrayList<Object> objcets = new ArrayList<Object>();
		List<Field> list = new  ArrayList<Field>();
		for(int j=0;j<fields.length;j++){
			String paramName = fields[j].getName();//属性名
			for(int k=0;k<params.length;k++){
					if(OwnUtil.stringIsEqual(params[k], paramName)){
						fields[j].setAccessible(true);
						try {
							if(fields[j].get(object)!=null&&!fields[j].get(object).equals("")){
								list.add(fields[j]);
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
			}
		}
		
		for(int k=0;k<params.length;k++){
			if(list==null||list.size()==0){
				return null;
			}
			for(Field field:list){
				if(OwnUtil.stringIsEqual(params[k], field.getName())){
					field.setAccessible(true);
					try {
						objcets.add(field.get(object));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		return objcets.toArray();
		
	} 

}

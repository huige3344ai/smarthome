package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class MemoradumQuery extends Query {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String theme;
	private String content;
	private String startTimeAndEndtime;
	private String color;
	/**
	 * 查询   开始时间
	 */
	private String startTime;
	/**
	 * 查询  结束时间
	 */
	private String endTime;
	
	
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartTimeAndEndtime() {
		return startTimeAndEndtime;
	}
	public void setStartTimeAndEndtime(String startTimeAndEndtime) {
		this.startTimeAndEndtime = startTimeAndEndtime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}

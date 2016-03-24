package com.smarthome.simple.entity;

/**
 * Memoradum entity. @author MyEclipse Persistence Tools
 */

public class Memoradum implements java.io.Serializable {

	// Fields

	private Integer id;
	private String theme;
	private String startTime;
	private String endTime;
	private String contents;
	private Integer num;
	private String recordTime;
	private String exchangeTime;
	private Integer userId;
	private String color;

	// Constructors

	/** default constructor */
	public Memoradum() {
	}

	/** minimal constructor */
	public Memoradum(String theme, String startTime, String endTime,
			String recordTime, Integer userId,String contents) {
		this.theme = theme;
		this.startTime = startTime;
		this.endTime = endTime;
		this.recordTime = recordTime;
		this.userId = userId;
		this.contents=contents;
	}

	/** full constructor */
	public Memoradum(String theme, String startTime, String endTime,
			String contents, Integer num, String recordTime,
			String exchangeTime, Integer userId, String color) {
		this.theme = theme;
		this.startTime = startTime;
		this.endTime = endTime;
		this.contents = contents;
		this.num = num;
		this.recordTime = recordTime;
		this.exchangeTime = exchangeTime;
		this.userId = userId;
		this.color = color;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getExchangeTime() {
		return this.exchangeTime;
	}

	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
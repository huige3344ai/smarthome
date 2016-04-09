package com.smarthome.simple.entity;

/**
 * ResetSystem entity. @author MyEclipse Persistence Tools
 */

public class ResetSystem implements java.io.Serializable {

	// Fields

	private Integer id;
	private Short period;
	private String startTime;
	private String endTime;
	private String recommend;
	private Integer userId;
	private String exTime;

	// Constructors

	/** default constructor */
	public ResetSystem() {
	}

	/** full constructor */
	public ResetSystem(Short period, String startTime, String endTime,
			String recommend, Integer userId, String exTime) {
		this.period = period;
		this.startTime = startTime;
		this.endTime = endTime;
		this.recommend = recommend;
		this.userId = userId;
		this.exTime = exTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getPeriod() {
		return this.period;
	}

	public void setPeriod(Short period) {
		this.period = period;
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

	public String getRecommend() {
		return this.recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getExTime() {
		return this.exTime;
	}

	public void setExTime(String exTime) {
		this.exTime = exTime;
	}

}
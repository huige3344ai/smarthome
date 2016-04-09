package com.smarthome.simple.entity;

/**
 * Restrecord entity. @author MyEclipse Persistence Tools
 */

public class Restrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String startTime;
	private String endTime;
	private String recommendRestTime;
	private String recommendWakeTime;
	private Short recordNum;
	private Short num;
	private String recordTime;

	// Constructors

	/** default constructor */
	public Restrecord() {
	}

	/** minimal constructor */
	public Restrecord(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public Restrecord(Integer userId, String startTime, String endTime,
			String recommendRestTime, String recommendWakeTime,
			Short recordNum, Short num, String recordTime) {
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.recommendRestTime = recommendRestTime;
		this.recommendWakeTime = recommendWakeTime;
		this.recordNum = recordNum;
		this.num = num;
		this.recordTime = recordTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getRecommendRestTime() {
		return this.recommendRestTime;
	}

	public void setRecommendRestTime(String recommendRestTime) {
		this.recommendRestTime = recommendRestTime;
	}

	public String getRecommendWakeTime() {
		return this.recommendWakeTime;
	}

	public void setRecommendWakeTime(String recommendWakeTime) {
		this.recommendWakeTime = recommendWakeTime;
	}

	public Short getRecordNum() {
		return this.recordNum;
	}

	public void setRecordNum(Short recordNum) {
		this.recordNum = recordNum;
	}

	public Short getNum() {
		return this.num;
	}

	public void setNum(Short num) {
		this.num = num;
	}

	public String getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

}
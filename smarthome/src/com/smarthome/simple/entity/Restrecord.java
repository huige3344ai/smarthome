package com.smarthome.simple.entity;

import java.sql.Timestamp;

/**
 * Restrecord entity. @author MyEclipse Persistence Tools
 */

public class Restrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp recommendRestTime;
	private Timestamp recommendWakeTime;
	private Short recordNum;
	private Short num;
	private String recordTime;

	// Constructors

	/** default constructor */
	public Restrecord() {
	}

	/** minimal constructor */
	public Restrecord(Integer userId, Timestamp startTime) {
		this.userId = userId;
		this.startTime = startTime;
	}

	/** full constructor */
	public Restrecord(Integer userId, Timestamp startTime, Timestamp endTime,
			Timestamp recommendRestTime, Timestamp recommendWakeTime,
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

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getRecommendRestTime() {
		return this.recommendRestTime;
	}

	public void setRecommendRestTime(Timestamp recommendRestTime) {
		this.recommendRestTime = recommendRestTime;
	}

	public Timestamp getRecommendWakeTime() {
		return this.recommendWakeTime;
	}

	public void setRecommendWakeTime(Timestamp recommendWakeTime) {
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
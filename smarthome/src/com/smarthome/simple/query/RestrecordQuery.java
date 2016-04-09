package com.smarthome.simple.query;

import com.smarthome.base.Query;

public class RestrecordQuery extends Query {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer period;//人生阶段
	

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

}

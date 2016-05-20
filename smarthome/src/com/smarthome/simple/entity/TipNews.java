package com.smarthome.simple.entity;

/**
 * TipNews entity. @author MyEclipse Persistence Tools
 */

public class TipNews implements java.io.Serializable {

	// Fields

	private TipNewsId id;
	
	


	// Constructors

	/** default constructor */
	public TipNews() {
	}

	/** full constructor */
	public TipNews(TipNewsId id) {
		this.id = id;
	}

	// Property accessors

	public TipNewsId getId() {
		return this.id;
	}

	public void setId(TipNewsId id) {
		this.id = id;
	}



}
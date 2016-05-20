package com.smarthome.simple.entity;

/**
 * TipNewsId entity. @author MyEclipse Persistence Tools
 */

public class TipNewsId implements java.io.Serializable {

	// Fields

	private Long num;
	private String type;
	
	private Long unum;
	private Long hnum;
	private Long dnum;	

	// Constructors

	/** default constructor */
	public TipNewsId() {
	}

	/** full constructor */
	public TipNewsId(Long num, String type) {
		this.num = num;
		this.type = type;
	}

	// Property accessors

	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TipNewsId))
			return false;
		TipNewsId castOther = (TipNewsId) other;

		return ((this.getNum() == castOther.getNum()) || (this.getNum() != null
				&& castOther.getNum() != null && this.getNum().equals(
				castOther.getNum())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		return result;
	}

	public Long getUnum() {
		return unum;
	}

	public Long getHnum() {
		return hnum;
	}

	public Long getDnum() {
		return dnum;
	}

	public void setUnum(Long unum) {
		this.unum = unum;
	}

	public void setHnum(Long hnum) {
		this.hnum = hnum;
	}

	public void setDnum(Long dnum) {
		this.dnum = dnum;
	}



}
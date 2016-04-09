package com.smarthome.simple.entity;

/**
 * TipNewsId entity. @author MyEclipse Persistence Tools
 */

public class TipNews implements java.io.Serializable {

	// Fields

	private Long unum;
	private Long hnum;
	private Long dnum;

	// Constructors

	/** default constructor */
	public TipNews() {
	}

	/** full constructor */
	public TipNews(Long unum, Long hnum, Long dnum) {
		this.unum = unum;
		this.hnum = hnum;
		this.dnum = dnum;
	}

	// Property accessors

	public Long getUnum() {
		return this.unum;
	}

	public void setUnum(Long unum) {
		this.unum = unum;
	}

	public Long getHnum() {
		return this.hnum;
	}

	public void setHnum(Long hnum) {
		this.hnum = hnum;
	}

	public Long getDnum() {
		return this.dnum;
	}

	public void setDnum(Long dnum) {
		this.dnum = dnum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TipNews))
			return false;
		TipNews castOther = (TipNews) other;

		return ((this.getUnum() == castOther.getUnum()) || (this.getUnum() != null
				&& castOther.getUnum() != null && this.getUnum().equals(
				castOther.getUnum())))
				&& ((this.getHnum() == castOther.getHnum()) || (this.getHnum() != null
						&& castOther.getHnum() != null && this.getHnum()
						.equals(castOther.getHnum())))
				&& ((this.getDnum() == castOther.getDnum()) || (this.getDnum() != null
						&& castOther.getDnum() != null && this.getDnum()
						.equals(castOther.getDnum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUnum() == null ? 0 : this.getUnum().hashCode());
		result = 37 * result
				+ (getHnum() == null ? 0 : this.getHnum().hashCode());
		result = 37 * result
				+ (getDnum() == null ? 0 : this.getDnum().hashCode());
		return result;
	}

}
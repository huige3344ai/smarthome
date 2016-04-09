package com.smarthome.simple.entity;

/**
 * Home entity. @author MyEclipse Persistence Tools
 */

public class Home implements java.io.Serializable {

	// Fields

	private Integer id;
	private String country;
	private String address;
	private Integer ownerId;
	private Integer deviceNum;
	private String temperature;
	private String humidity;
	private String recordTime;
	private String exchangeTime;
	private Integer usreId;
	private String logoImage;

	private String userName;
	// Constructors

	/** default constructor */
	public Home() {
	}

	//自定义虚构函数Home(id,country,address,ownerId)
	public Home(Integer id,String country,String address,int ownerId) {
		this.id = id;
		this.country = country;
		this.address = address;
		this.ownerId = ownerId;
		
	}	
	
	//自定义虚构函数 Home( id, country, address, userName, temperature, humidity, recordTime)
	public Home(Integer id,String country,String address,String userName,String temperature,String humidity,String recordTime) {
		this.id = id;
		this.country = country;
		this.address = address;
		this.userName = userName;
		this.temperature = temperature;
		this.humidity = humidity;
		this.recordTime = recordTime;
		
	}
	
	public Home(Integer id,String country,String address,String userName) {
		this.id = id;
		this.country = country;
		this.address = address;
		
	}
	
	/** minimal constructor */
	public Home(Integer ownerId) {
		this.ownerId = ownerId;
	}

	/** full constructor */
	public Home(String country, String address, Integer ownerId,
			Integer deviceNum, String temperature, String humidity,
			String recordTime, String exchangeTime, Integer usreId,
			String logoImage) {
		this.country = country;
		this.address = address;
		this.ownerId = ownerId;
		this.deviceNum = deviceNum;
		this.temperature = temperature;
		this.humidity = humidity;
		this.recordTime = recordTime;
		this.exchangeTime = exchangeTime;
		this.usreId = usreId;
		this.logoImage = logoImage;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(Integer deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return this.humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
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

	public Integer getUsreId() {
		return this.usreId;
	}

	public void setUsreId(Integer usreId) {
		this.usreId = usreId;
	}

	public String getLogoImage() {
		return this.logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
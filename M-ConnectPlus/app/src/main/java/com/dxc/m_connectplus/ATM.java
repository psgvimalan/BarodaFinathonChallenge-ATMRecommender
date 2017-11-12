package com.dxc.m_connectplus;

public class ATM {
	private String id;
	private String Name;
	private String Branch_code;
	private String address;
	private String latitude;
	private String longitude;
	private String Area;
	private String city;
	private String district;
	private String state;
	private String distance;
	private String time;
	private String isCashAvailable;
	private String isAtmUp;
	private String atmId;

	public String getAtmId() {
		return atmId;
	}

	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}

	public String getIsCashAvailable() {
		return isCashAvailable;
	}
	public void setIsCashAvailable(String isCashAvailable) {
		this.isCashAvailable = isCashAvailable;
	}
	public String getIsAtmUp() {
		return isAtmUp;
	}
	public void setIsAtmUp(String isAtmUp) {
		this.isAtmUp = isAtmUp;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBranch_code() {
		return Branch_code;
	}
	public void setBranch_code(String branch_code) {
		Branch_code = branch_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ATM [id=" + id + ", Name=" + Name + ", Branch_code=" + Branch_code + ", address=" + address
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", Area=" + Area + ", city=" + city
				+ ", district=" + district + ", state=" + state + ", distance=" + distance + ", time=" + time + "]";
	}

	
	
}

package com.gyumaru.regionManage.pojo;

public class City {//市级
	private Integer id;
	private County county;//县级
	private String name;//市级名字
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public County getCounty() {
		return county;
	}
	public void setCounty(County county) {
		this.county = county;
	}
	

}

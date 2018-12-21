package com.gyumaru.regionManage.pojo;

public class County {//县级
	private Integer id;
	private Region region;//地区
	private String name;//县级名字
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
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	

}

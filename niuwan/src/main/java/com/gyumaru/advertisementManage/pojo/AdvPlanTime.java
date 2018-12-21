package com.gyumaru.advertisementManage.pojo;

import java.io.Serializable;

public class AdvPlanTime implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5653430251611700064L;
	private Integer id;//广告时间Id 
	private String tdate;//日期
	private String thour;//时间 才用2个字格式 比如02
	private Integer type;//投放页面类型：1、开屏页 2、banner 3、插屏页
	private Integer advPlanId;//广告规划id
	private Integer state;//状态 1未审核 2审核通过 3审核不通过
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	public String getThour() {
		return thour;
	}
	public void setThour(String thour) {
		this.thour = thour;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAdvPlanId() {
		return advPlanId;
	}
	public void setAdvPlanId(Integer advPlanId) {
		this.advPlanId = advPlanId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
}

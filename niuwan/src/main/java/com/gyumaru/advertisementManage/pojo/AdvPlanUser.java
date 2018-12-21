package com.gyumaru.advertisementManage.pojo;

import java.io.Serializable;

import com.gyumaru.userManage.pojo.User;

public class AdvPlanUser implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5653430251611700064L;
	private Integer id;//用户广告Id 
	private AdvPlan advPlan;//广告
	private User user;//用户Id 
	private Integer displayNum;//曝光次数
	private Integer clickNum;//点击次数
	private Long totaldisNum;//*总曝光数
	private Long totalclickNum;//*总点击数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDisplayNum() {
		return displayNum;
	}
	public void setDisplayNum(Integer displayNum) {
		this.displayNum = displayNum;
	}
	public Integer getClickNum() {
		return clickNum;
	}
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	public AdvPlan getAdvPlan() {
		return advPlan;
	}
	public void setAdvPlan(AdvPlan advPlan) {
		this.advPlan = advPlan;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getTotaldisNum() {
		return totaldisNum;
	}
	public void setTotaldisNum(Long totaldisNum) {
		this.totaldisNum = totaldisNum;
	}
	public Long getTotalclickNum() {
		return totalclickNum;
	}
	public void setTotalclickNum(Long totalclickNum) {
		this.totalclickNum = totalclickNum;
	}
	
}

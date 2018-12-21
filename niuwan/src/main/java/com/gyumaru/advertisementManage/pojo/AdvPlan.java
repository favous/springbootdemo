package com.gyumaru.advertisementManage.pojo;

import java.io.Serializable;


public class AdvPlan implements  Serializable {
	private static final long serialVersionUID = -5653430251611700064L;
	private Integer id;//广告Id 
	private String name;//广告计划名称
	private Integer type;//投放页面类型：1、开屏页 2、banner 3、插屏页
	private String startdate;//投放开始日期
	private String enddate;//投放结束日期
	private String link;//跳转链接
	private String introduction;//简单介绍
	private Long expectedNum;//期望展示数
	private Long dispalyNum;//个人展示数
	private Long clickNum;//个人点击数
	private Long realclickNum;//*真实个人点击数
	private Long realdispalyNum;//*真实个人展示数
	private Long totalNum;//总点击数
	private Long totaldisNum;//总展示数
	private String clickrate;//*真实点击率
	private Integer state;//状态：1待审核、2审核通过、3审核不通过
	private String subtime;//提交时间
	private String img;//广告图片  不从广告表查询
	private String typeStr;//*投放页面字符串
	private String stateStr;//*审核状态字符串
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTypeStr() {
		switch (type) {
		case 1:
			return "开屏页";
		case 2:
			return "banner";
		case 3:
			return "插屏页";
		default:
			return "未知";
		}
	}
	public String getClickrate() {
		return clickrate;
	}
	public void setClickrate(String clickrate) {
		this.clickrate = clickrate;
	}
	public String getStateStr() {
		switch (state) {
		case 1:
			return "待审核";
		case 2:
			return "通过";
		case 3:
			return "拒绝";
		default:
			return "未知";
		}
	}
	public Long getExpectedNum() {
		return expectedNum;
	}
	public void setExpectedNum(Long expectedNum) {
		this.expectedNum = expectedNum;
	}
	public Long getDispalyNum() {
		return dispalyNum;
	}
	public void setDispalyNum(Long dispalyNum) {
		this.dispalyNum = dispalyNum;
	}
	public Long getClickNum() {
		return clickNum;
	}
	public void setClickNum(Long clickNum) {
		this.clickNum = clickNum;
	}
	public Long getRealclickNum() {
		return realclickNum;
	}
	public void setRealclickNum(Long realclickNum) {
		this.realclickNum = realclickNum;
	}
	public Long getRealdispalyNum() {
		return realdispalyNum;
	}
	public void setRealdispalyNum(Long realdispalyNum) {
		this.realdispalyNum = realdispalyNum;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public Long getTotaldisNum() {
		return totaldisNum;
	}
	public void setTotaldisNum(Long totaldisNum) {
		this.totaldisNum = totaldisNum;
	}
}

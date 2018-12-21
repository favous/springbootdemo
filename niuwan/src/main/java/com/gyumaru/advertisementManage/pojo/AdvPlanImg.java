package com.gyumaru.advertisementManage.pojo;


public class AdvPlanImg {//广告图片
	private Integer id;
	private Integer type;//机型：1不分类 2iphonex
	private String url;//图片链接
	private Integer advPlanId;//广告id
	private String typeStr;//素材类型字符串
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeStr() {
		switch (type) {
		case 1:
			return "不分类";
		case 2:
			return "iphone6";
		case 3:
			return "iphonex";
		default:
			return "未知";
		}
		
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getAdvPlanId() {
		return advPlanId;
	}
	public void setAdvPlanId(Integer advPlanId) {
		this.advPlanId = advPlanId;
	}

}

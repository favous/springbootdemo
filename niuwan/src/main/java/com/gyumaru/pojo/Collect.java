package com.gyumaru.pojo;

import java.io.Serializable;

public class Collect implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5653430251611700064L;
	private Integer id;//收藏ID
	private Integer type;//类型区分 1：资讯  2：游戏
	private Integer modeId;//类型区分下小类Id
	private Integer userId;//用户Id
	private String collectTime;//用户收藏时间
	//喜欢跳转所需要的资源
		private String  imgUrl;//图片
		private String  title;//标题
		private String  content;//内容
		private Integer  modetypeId;//区分Id
		private String   time;//时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getModeId() {
		return modeId;
	}
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getModetypeId() {
		return modetypeId;
	}
	public void setModetypeId(Integer modetypeId) {
		this.modetypeId = modetypeId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
	
}

package com.demo.order.entity;

import java.io.Serializable;

public class Game implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5653430251611700064L;
	private Integer id;//游戏ID
	private String title;//游戏名称
	private String type;//游戏类型
	private String size;//文件大小
	private Integer times;//下载次数
	private String version;//游戏版本
	private Integer xj;//星级1~5表示1~5星
	private String file;//游戏文件；游戏url
	private String picture;//封面
	private String icon;//ICON图
	private String jdesc;//简单描述
	private String jdjs;//简单介绍
	private Integer isshow;//1显示0不显示
	private Integer dj;//1 H5 2 客服端
	private Integer hb;//1首发0不首发 推测为 是否 发现好游戏
	private Integer rm;//1热门0不热门
	private Integer rsort;//热门顺序
	private Integer wy;//1.新上架 推测为小编推荐
	private String kfname;//新开服名称
	private String kftimes;//新开服时间·	
	private Integer wsort;//推荐排序
	private Integer bw;//新开服
	private Integer bsort;//	新开服排序（未用）
	private Integer sort;//显示排序
	private String add_time;//增加时间
	private Integer collertMode;//用户是否点赞1是0不是
	private String jdjs1;//简单介绍30字符
	private String developer;//开发商

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getXj() {
		return xj;
	}
	public void setXj(Integer xj) {
		this.xj = xj;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getJdesc() {
		return jdesc;
	}
	public void setJdesc(String jdesc) {
		this.jdesc = jdesc;
	}
	public Integer getIsshow() {
		return isshow;
	}
	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}
	public Integer getDj() {
		return dj;
	}
	public void setDj(Integer dj) {
		this.dj = dj;
	}
	public Integer getHb() {
		return hb;
	}
	public void setHb(Integer hb) {
		this.hb = hb;
	}
	public Integer getRm() {
		return rm;
	}
	public void setRm(Integer rm) {
		this.rm = rm;
	}
	public Integer getRsort() {
		return rsort;
	}
	public void setRsort(Integer rsort) {
		this.rsort = rsort;
	}
	public Integer getWy() {
		return wy;
	}
	public void setWy(Integer wy) {
		this.wy = wy;
	}
	public String getKfname() {
		return kfname;
	}
	public void setKfname(String kfname) {
		this.kfname = kfname;
	}
	public String getKftimes() {
		return kftimes;
	}
	public void setKftimes(String kftimes) {
		this.kftimes = kftimes;
	}
	public Integer getWsort() {
		return wsort;
	}
	public void setWsort(Integer wsort) {
		this.wsort = wsort;
	}
	public Integer getBw() {
		return bw;
	}
	public void setBw(Integer bw) {
		this.bw = bw;
	}
	public Integer getBsort() {
		return bsort;
	}
	public void setBsort(Integer bsort) {
		this.bsort = bsort;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getJdjs() {
		return jdjs;
	}
	public void setJdjs(String jdjs) {
		this.jdjs = jdjs;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Integer getCollertMode() {
		return collertMode;
	}
	public void setCollertMode(Integer collertMode) {
		this.collertMode = collertMode;
	}
	public String getJdjs1() {
		return jdjs1;
	}
	public void setJdjs1(String jdjs1) {
		this.jdjs1 = jdjs1;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
}

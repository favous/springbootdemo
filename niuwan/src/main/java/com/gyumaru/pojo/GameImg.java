package com.gyumaru.pojo;

import java.io.Serializable;

public class GameImg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//游戏ID
	
	/**  */
	private Integer game_id;
	
	/**  */
	private String url;
	
	/** 缩略图url */
	private String surl;
	
	/**  */
	private Integer type;
	
	/**  */
	private Integer enable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGame_id() {
		return game_id;
	}

	public void setGame_id(Integer game_id) {
		this.game_id = game_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	
}

package com.fzc.common.enums;

public enum UrlTypeEnum {
	
	IMG(1, "图片"),
	VIDEO(2, "视频"),
	voice(3, "声音"),
	WEB(4, "网页"),
	;
	
	private Integer code;
	private String desc;
	
	private UrlTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
}

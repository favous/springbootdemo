package com.fzc.common.enums;

public enum SexEnum {
	
	MALE(1, "男"),
	FEMALE(2, "女"),
	;
	
	private Integer code;
	private String desc;
	
	private SexEnum(Integer code, String desc) {
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
